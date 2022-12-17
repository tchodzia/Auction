package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity(name="biddings")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bidding {

    public Bidding(Auction auction, User user, Long amount) {
        this.auction = auction;
        this.user = user;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;

    @Column
    private Long amount;


    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }
}
