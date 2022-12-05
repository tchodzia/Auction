package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="categories")
@Getter
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String icon;

    @Column
    private Long parent_category;
}
