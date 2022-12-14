package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Category;
import sda.project.auction.model.CategoryTree;
import sda.project.auction.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryTree> findAllCategoryTree(){
        List<CategoryTree> list = new ArrayList<>();
        // ile kategorii glownych
        int mainCategoriesAmount = repository.findAllOrderedByParentCategory().size();
        for (int i = 0; i < mainCategoriesAmount; i++) {
            // kategoria glowna
            CategoryTree cT = new CategoryTree();
            cT.setCategoryName(repository.findAllOrderedByParentCategory().get(i).getName());
            cT.setCatID(repository.findAllOrderedByParentCategory().get(i).getID());
            list.add(cT);

            List<CategoryTree> listOfSubcategories = new ArrayList<>();
            // ile podkategorii
            int subcategoriesAmount = repository.findAllSelectedByParentCategory(cT.getCatID()).size();
            for (int j = 0; j < subcategoriesAmount; j++) {
                CategoryTree subcT = new CategoryTree();
                subcT.setCategoryName(repository.findAllSelectedByParentCategory(cT.getCatID()).get(j).getName());
                subcT.setCatID(repository.findAllSelectedByParentCategory(cT.getCatID()).get(j).getID());
                listOfSubcategories.add(subcT);
            }
            cT.setSubcategories(listOfSubcategories);
        }
        return list;
    }

    public List<Category> findAllOrderedByParentCategory() {
        return repository.findAllOrderedByParentCategory();
    }

    public List<Category> findAllSelectedByParentCategory(Long Id) {
        return repository.findAllSelectedByParentCategory(Id);
    }
    public Category findById(Long Id) {
        return repository.findById(Id).orElseThrow(() -> new RuntimeException("Category with id " + Id + " not found."));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

}
