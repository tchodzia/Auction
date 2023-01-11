package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name="auctions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
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
        this.isActive = true;
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

    @Column
    private Boolean isActive;

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

    public void setStringToDate_of_issue(String date_of_issue) {
        //log.info("String date of issue: " + date_of_issue);
        LocalDateTime dateTime = LocalDateTime.now();
        if (date_of_issue != null && !date_of_issue.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            dateTime = LocalDateTime.parse(date_of_issue, formatter);
        }
        this.date_of_issue = dateTime;
    }

    public void setStringToEnd_date(String end_date) {
        //log.info("String end date: " + end_date);
        LocalDateTime dateTime = LocalDateTime.now();
        if (end_date != null && !end_date.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            dateTime = LocalDateTime.parse(end_date, formatter);
        }
        this.end_date = dateTime;
    }



    public String getStringDateOfIssue() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        if (this.date_of_issue != null) {
             return this.date_of_issue.format(customFormat);
        } else {
            return LocalDateTime.now().format(customFormat);
        }
    }

    public String getStringEndDate() {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        if (this.end_date != null) {
            return this.end_date.format(customFormat);
        } else {
            return LocalDateTime.now().format(customFormat);
        }
    }
}
