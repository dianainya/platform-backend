package sadiva.mpi.platformbackend.repo;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.SelectLimitPercentAfterOffsetStep;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Pageable;
import sadiva.mpi.platformbackend.service.exception.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

public interface BasePaginatedRepository {

    default <T extends Record> SelectLimitPercentAfterOffsetStep<T> applyPagination(SelectConditionStep<T> records, Pageable pageable, String orderByTableName) {

        if (!records.isExecutable()) {
            throw new ValidationException("Unable to execute request " + records);
        }
        List<SortField<?>> ordered = generateOrderByStatement(pageable, orderByTableName);
        var result = records
                .orderBy(ordered);
        if (!result.isExecutable()) {
            throw new ValidationException("Unable to execute request with ordered fields " + ordered);
        }
        return result
                .offset((pageable.getPageNumber() - 1) * pageable.getPageSize())
                .limit(pageable.getPageSize());
    }


    private List<SortField<?>> generateOrderByStatement(Pageable pageable, String orderByTableName) {
        return pageable.getSort().stream().map(
                        order -> {
                            Field<?> field;
                            if (orderByTableName != null) {
                                field = DSL.field(orderByTableName + "." + order.getProperty());
                            } else {
                                field = DSL.field(order.getProperty());
                            }
                            if (order.isAscending()) {
                                return field.asc();
                            } else {
                                return field.desc();
                            }
                        })
                .collect(Collectors.toList());
    }


    default <T extends Record> SelectLimitPercentAfterOffsetStep<T> applyPagination(SelectConditionStep<T> records, Pageable pageable) {
        return applyPagination(records, pageable, null);
    }

}