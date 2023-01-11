package sda.project.auction.web.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sda.project.auction.service.AuctionService;

@Controller
@RequiredArgsConstructor
@RequestMapping(
        path = "/buy",
        method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT}
)
public class PurchaseController {

    private final AuctionService auctionService;

    @GetMapping("/{auction_id}/user/{user_id}")
    public String createPurchase(@PathVariable("auction_id") Long auction_id, @PathVariable("user_id") Long user_id) {

        auctionService.alterAuctionToInactive(auction_id);
        auctionService.createNewPurchase(auction_id, user_id);
        return "redirect:/";
    }
}
