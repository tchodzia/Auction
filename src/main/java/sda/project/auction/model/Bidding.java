package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity(name="biddings")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;

    @Column
    private BigDecimal amount;


    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }
}
