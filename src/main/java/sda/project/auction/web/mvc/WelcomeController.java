package sda.project.auction.web.mvc;

import sda.project.auction.model.*;
import sda.project.auction.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class WelcomeController {
    private final UserService userService;
    private final AuctionService auctionService;
    private final CategoryService categoryService;
    private final BiddingService biddingService;

    private final ObservedAuctionService observedAuctionService;

    @GetMapping("/")
    public String welcomePage(ModelMap map) {
        User user = userService.findById(2L);
        map.addAttribute("user", user);

        List<Auction> auctionsNew10 = auctionService.findFirst10ByDateOfIssue();
        map.addAttribute("auctionsNew10", auctionsNew10);

        List<Auction> auctionsLast10 = auctionService.findLast10ByDateOfIssue();
        map.addAttribute("auctionsLast10", auctionsLast10);

        List<Auction> auctionsByUser = auctionService.findAllAuctionsByDateOfIssueAndUser(user.getID());
        map.addAttribute("auctionsByUser", auctionsByUser);

      //  List<Auction> auctionsAll = auctionService.findAll();
      //  map.addAttribute("auctionsAll", auctionsAll);

        List<Bidding> auctionsBiddingByUser = biddingService.findAllBiddingsByUserId(user.getID());
        map.addAttribute("auctionsBiddingByUser", auctionsBiddingByUser);

        List<ObservedAuction> observedAuctions = observedAuctionService.findAllObservedAuctionsByUserId(user.getID());
        map.addAttribute("observedAuctionsByUser", observedAuctions);

        List<Auction> finishedAuctionsByUser = auctionService.finishedAuctionsByUser(user.getID());
        map.addAttribute("finishedAuctionsByUser", finishedAuctionsByUser);


        // CATEGORIES
/*
        List<Category> categories = categoryService.findAllOrderedByParentCategory();

        List<Category> sortedCategories = new ArrayList<>();
        for (Category category : categories) {
            sortedCategories.add(category);
            List<Category> downCategories = categoryService.findAllSelectedByParentCategory(category.getID());
            for (Category downCategory : downCategories) {
                sortedCategories.add(downCategory);
            }
        }

        map.addAttribute("sortedCategories", sortedCategories);
*/
        List<CategoryTree> categoryTrees = categoryService.findAllCategoryTree();
        map.addAttribute("categoryTrees", categoryTrees);

        return "index";
    }
}