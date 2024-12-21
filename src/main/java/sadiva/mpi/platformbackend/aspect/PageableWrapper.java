package sadiva.mpi.platformbackend.aspect;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PageableWrapper implements Pageable {

    private final Pageable pageble;
    private final Map<String, String> newFieldsMapping;

    @Override
    public int getPageNumber() {
        return pageble.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return pageble.getPageSize();
    }

    @Override
    public long getOffset() {
        return pageble.getOffset();
    }

    @Override
    public Sort getSort() {
        var newSorted = pageble
                .getSort()
                .get()
                .map(e -> new Sort.Order(e.getDirection(), newFieldsMapping.get(e.getProperty())))
                .collect(Collectors.toList());
        return Sort.by(newSorted);
    }

    @Override
    public Pageable next() {
        return pageble.next();
    }

    @Override
    public Pageable previousOrFirst() {
        return pageble.previousOrFirst();
    }

    @Override
    public Pageable first() {
        return pageble.first();
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return pageble.withPage(pageNumber);
    }

    @Override
    public boolean hasPrevious() {
        return pageble.hasPrevious();
    }
}
