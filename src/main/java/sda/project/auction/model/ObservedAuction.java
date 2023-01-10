package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="observed_auctions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ObservedAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private User user;

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

}