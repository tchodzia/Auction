package sda.project.auction.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="purchases")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private User user;

    @OneToOne
    private Auction auction;

    @Column
    private Long price;

    public Purchase(User user, Auction auction, Long price) {
        this.user = user;
        this.auction = auction;
        this.price = price;
    }

    public void setID(Long id) {
        this.ID = id;
    }

    public Long getID() {
        return ID;
    }

}
