package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity(name="auctions")
@Getter
@NoArgsConstructor
@ToString
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Long category;

    @Column
    private Long min_price;

    @Column
    private Long BUY_NOW_price;

    @Column
    private boolean promoted;

    @Column
    private String localization;

    @Column
    private LocalDateTime date_of_issue;

    @Column
    private LocalDateTime end_date;

    @Column
    private Long numbers_of_visitors;

    @Column
    private Long user;

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }
}
