package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name="auctions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Auction {
    public Auction(String title, String description, Long category, Long min_price, Long BUY_NOW_price, boolean promoted, String localization, LocalDateTime date_of_issue, LocalDateTime end_date, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.min_price = min_price;
        this.BUY_NOW_price = BUY_NOW_price;
        this.promoted = promoted;
        this.localization = localization;
        this.date_of_issue = date_of_issue;
        this.end_date = end_date;
        this.user = user;
    }

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

    @ManyToOne
    private User user;

    public Auction(String title, String description, Long category, Long min_price, Long buy_now_price, boolean promoted, String localization, LocalDateTime date_of_issue, LocalDateTime end_date) {
    }

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

    public boolean isEmpty() {
        if(this.ID == null || this.title == null || this.title.isBlank()) {
            return true;
        }
        else {
            return false;
        }
    }

    //String str = "2016-03-04 11:30";
    public void setDate_of_issue(String date_of_issue) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date_of_issue, formatter);

        this.date_of_issue = dateTime;
    }

    public void setEnd_date(String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(end_date, formatter);

        this.end_date = dateTime;
    }

}
