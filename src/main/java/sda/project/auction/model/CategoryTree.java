package sda.project.auction.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryTree {
    String categoryName;
    List<CategoryTree> subcategories;
    Long catID;
}
