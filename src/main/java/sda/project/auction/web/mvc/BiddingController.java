package sda.project.auction.web.mvc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.project.auction.model.Bidding;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.BiddingService;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.BidForm;
import sda.project.auction.web.form.NewBidForm;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bidding")
public class BiddingController {

    private final BiddingService biddingService;
    private final AuctionService auctionService;
    private final UserService userService;



    @GetMapping("/newbid/auction/{auction_id}/user/{user_id}")
    public String createNewBid(@PathVariable("auction_id") Long auction_id, @PathVariable("user_id") Long user_id, ModelMap map){
        map.addAttribute("newbid", new NewBidForm(auction_id, user_id));
        return "create-bid";
    }

    @PostMapping("/newbid")
    public String handleNewBid(@ModelAttribute("newbid") @Valid NewBidForm newBidForm, Errors errors, RedirectAttributes redirectAttributes, ModelMap map) {
        if(errors.hasErrors()){
            return "create-bid";
        }
        biddingService.save(new Bidding(auctionService.findById(newBidForm.getAuction_id()), userService.findById(newBidForm.getUser_id()), newBidForm.getAmount()));
        redirectAttributes.addAttribute("message", "New bidding was created!");
        return "redirect:/";
    }


    @GetMapping("/bid/auction/{auction_id}/user/{user_id}")
    public String newBid(@PathVariable("auction_id") Long auction_id, @PathVariable("user_id") Long user_id, ModelMap map){
        //gdzie walidacja ceny nowego podbicia ?!
        Long currentPrice = biddingService.findBiddingByAuction(auctionService.findById(auction_id)).getAmount();
        map.addAttribute("bid", new BidForm(auction_id, user_id, currentPrice));
        return "bid-form";
    }

    @PostMapping("/newBid")
    public String handleNewBid(@ModelAttribute("bid") @Valid BidForm bidForm){
        biddingService.update(bidForm);
        return "redirect:/";
    }

}
