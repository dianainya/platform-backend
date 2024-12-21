package sadiva.mpi.platformbackend.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Aspect
@Configuration
@Slf4j
public class PageableModifyerAspect {

    private static final Pattern SORT_REGEXP = Pattern.compile("^[a-zA-Z0-9_.]*$");

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Around("controller()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        int index = 0;
        Object[] modifiedArgs = joinPoint.getArgs();
        if (joinPoint.getArgs().length > 0) {
            for (Object arg : joinPoint.getArgs()) {
                if (arg instanceof Pageable) {
                    Pageable oldPagable = (Pageable) arg;
                    Sort sort = oldPagable
                            .getSort();
                    checkSort(sort);
                    Map<String, String> newValueOldValueMapping = sort
                            .stream()
                            .collect(Collectors.toMap(order -> order.getProperty(), order -> convertToUnderscoreCase(order.getProperty())));
                    modifiedArgs[index] = new PageableWrapper((Pageable) arg, newValueOldValueMapping);
                }
                index++;
            }
        }
        return joinPoint.proceed(modifiedArgs);
    }

    private void checkSort(Sort sort) {
        var notMatchedSorts = sort
                .stream()
                .map(e -> e.getProperty())
                .filter(e -> !SORT_REGEXP.matcher(e).matches())
                .collect(Collectors.toSet());
        if (notMatchedSorts != null && !notMatchedSorts.isEmpty()) {
            throw new ValidationException("Not valid sort properties " + notMatchedSorts);
        }
    }

    private String convertToUnderscoreCase(String input) {
        String tableName = null;
        String columnName = input;
        if(input == null) return input;
        if(input.indexOf(".") > 0){
            final String[] parts = input.split("\\.");
            tableName = parts[0];
            columnName = parts[1];
        }

        return (tableName != null ? tableName+"." : "") + columnName.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }
}

