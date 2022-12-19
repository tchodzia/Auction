package sda.project.auction.web.form;

import jakarta.validation.constraints.Min;
import lombok.*;
import sda.project.auction.model.Auction;
import sda.project.auction.model.User;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewBidForm {

    public NewBidForm(Long auction_id, Long user_id) {
        this.auction_id = auction_id;
        this.user_id = user_id;
    }

    private Long auction_id;

    private Long user_id;

    @Min(value = 0, message = "The amount must not be below 0!")
    private Long amount;
}
