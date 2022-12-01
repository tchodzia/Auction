package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Category;
import sda.project.auction.repository.CategoryRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAllOrderedByParentCategory() {
        return StreamSupport.stream(repository.findAllOrderedByParentCategory().spliterator(), false)
                .collect(toList());
    }

}
