package ru.otus.spring.courseproject.yag.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.otus.spring.courseproject.yag.domain.BacklogItem;

import java.util.List;

public interface BacklogItemRepository extends PagingAndSortingRepository<BacklogItem, Long> {
}
