package sda.project.auction.web.mvc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.Category;
import sda.project.auction.model.User;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.BiddingService;
import sda.project.auction.service.CategoryService;
import sda.project.auction.service.UserService;
import sda.project.auction.service.auth.CustomUserDetails;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.form.NewBidForm;
import sda.project.auction.web.mappers.UserMapper;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auctions")
public class AuctionController {
        private final AuctionService auctionService;
        private final CategoryService categoryService;
        private final BiddingService biddingService;

        private final UserService userService;

        @GetMapping("/user/{id}")
        public String displayAuctionByUser(@PathVariable("id") Long id, ModelMap map) {
            List<Auction> auctions = auctionService.findAllAuctionsByDateOfIssueAndUser(id);
            map.addAttribute("auctions", auctions);

//            map.addAttribute("user", new CreateUserForm());
//            map.addAttribute("roles", User.Roles.values());


            // map.addAttribute("user", new CreateUserForm());

            return "get-auctions";
        }

    @GetMapping("/cat/{id}")
    public String displayAuctionByCategory(@PathVariable("id") Long id, ModelMap map) {
        Category category = categoryService.findById(id);
        map.addAttribute("category", category);
        List<Auction> auctions = auctionService.findAllCurrentAuctionsByCategory(id);
        map.addAttribute("auctions", auctions);
        return "get-auctions-by-category";
    }


    @GetMapping("/{id}")
    public String displayAuctionById(@PathVariable("id") Long id, ModelMap map) {
        Auction auction = auctionService.findById(id);
        map.addAttribute("auction", auction);
        Bidding bidding = biddingService.findBiddingByAuctionId(id);
        map.addAttribute("bidding", bidding);
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User loggedUser = userService.findByEmail(principal.getUsername());
            map.addAttribute("loggedUser", loggedUser);}
        return "get-auction";
    }

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




}
