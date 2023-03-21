package sda.project.auction.web.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BidForm {


    public BidForm(Long auction_id, Long user_id, Long currentPrice) {
        this.auction_id = auction_id;
        this.user_id = user_id;
        this.currentPrice = currentPrice;
    }


    private Long auction_id;
    private Long user_id;

    private Long currentPrice;

    private Long amount;

    @AssertTrue(message = "Wartość jest nieprawidłowa")
    public boolean isOk(){
        return amount > currentPrice;
    }
}
