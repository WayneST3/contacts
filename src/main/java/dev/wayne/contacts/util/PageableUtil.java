package dev.wayne.contacts.util;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.ASC;

public class PageableUtil {

    public static Pageable generatePageable(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageNum >= 0 && pageSize != null && pageSize > 0) {
            return PageRequest.of(pageNum, pageSize);
        }
        return PageRequest.of(0, 50);
    }

    public static Pageable generatePageable(Integer pageNum, Integer pageSize, String sortField, SortDirection sortDirection) {
        if (pageNum != null && pageNum >= 0 && pageSize != null && pageSize > 0) {
            if (sortField == null || sortField.isEmpty()) {
                return PageRequest.of(pageNum, pageSize);
            }

            if (sortDirection == null) {
                return PageRequest.of(pageNum, pageSize, ASC, sortField);
            }

            return PageRequest.of(pageNum, pageSize, Sort.Direction.valueOf(sortDirection.getValue()), sortField);
        }
        return generatePageable(pageNum, pageSize);
    }

    @Getter
    public enum SortDirection {
        ASC("ASC"),
        DESC("DESC");

        private final String value;

        SortDirection(String value) {
            this.value = value;
        }
    }
}
