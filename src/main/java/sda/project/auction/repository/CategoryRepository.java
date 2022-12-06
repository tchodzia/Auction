package sda.project.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.auction.model.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories WHERE parent_category IS NULL ORDER BY parent_category ASC, name ASC", nativeQuery = true)
    public List<Category> findAllOrderedByParentCategory();

    @Query(value = "SELECT * FROM categories WHERE parent_category = :id ORDER BY name ASC", nativeQuery = true)
    public List<Category> findAllSelectedByParentCategory(@Param("id") Long id);
}
