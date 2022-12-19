package sda.project.auction.web.form;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BidForm {

    public BidForm(Long auction_id, Long user_id) {
        this.auction_id = auction_id;
        this.user_id = user_id;
    }

    private Long auction_id;
    private Long user_id;

    private Long amount;

}
