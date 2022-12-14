package sda.project.auction.web.mvc;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import sda.project.auction.model.*;
import sda.project.auction.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.project.auction.service.auth.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class WelcomeController{
    private final UserService userService;
    private final AuctionService auctionService;
    private final CategoryService categoryService;
    private final BiddingService biddingService;

    private final ObservedAuctionService observedAuctionService;

    private final FileStorageService fileStorageService;
    @GetMapping("/")
    public String welcomePage(ModelMap map, @ModelAttribute("message") String message) {

        auctionService.findAllAuctionsToDisactivate();


        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User loggedUser = userService.findByEmail(principal.getUsername());
            map.addAttribute("loggedUser", loggedUser);

            List<Auction> auctionsByUser = auctionService.findAllAuctionsByDateOfIssueAndUser(loggedUser.getID());
            map.addAttribute("auctionsByUser", auctionsByUser);

            List<Bidding> auctionsBiddingByUser = biddingService.findAllBiddingsByUserId(loggedUser.getID());
            map.addAttribute("auctionsBiddingByUser", auctionsBiddingByUser);


            List<ObservedAuction> observedAuctions = observedAuctionService.findAllObservedAuctionsByUserId(loggedUser.getID());
            map.addAttribute("observedAuctionsByUser", observedAuctions);

            List<Auction> finishedAuctionsByUser = auctionService.finishedAuctionsByUser(loggedUser.getID());
            map.addAttribute("finishedAuctionsByUser", finishedAuctionsByUser);

        }

        List<Auction> auctionsNew10 = auctionService.findFirst10ByDateOfIssue();
        map.addAttribute("auctionsNew10", auctionsNew10);

        List<Auction> auctionsLast10 = auctionService.findLast10ByDateOfIssue();
        map.addAttribute("auctionsLast10", auctionsLast10);

        Auction currentRandomAuction = auctionService.getCurrentRandomAuction();
        map.addAttribute("currentRandomAuction", currentRandomAuction);



        //  List<Auction> auctionsAll = auctionService.findAll();
        //  map.addAttribute("auctionsAll", auctionsAll);

        List<File> files = null;
        if (currentRandomAuction != null) {
            files = fileStorageService.getFilesByAuctionId(currentRandomAuction.getID());
        }
        if (files == null || files.size() == 0) {
            List<File> storedFiles = new ArrayList<>();
            map.addAttribute("storedFiles", storedFiles);
        } else {
            map.addAttribute("storedFiles", files);
        }

        // CATEGORIES

        List<CategoryTree> categoryTrees = categoryService.findAllCategoryTree();
        map.addAttribute("categoryTrees", categoryTrees);


        List<Category> categories = categoryService.findAll();
        map.addAttribute("categories", categories);


        if (!message.isEmpty()) {
            map.addAttribute("message", message);
        }

        return "index";
    }

    @GetMapping("/categories/")
    public String categoriesPage(ModelMap map, @ModelAttribute("message") String message) {

        List<CategoryTree> categoryTrees = categoryService.findAllCategoryTree();
        map.addAttribute("categoryTrees", categoryTrees);

        return "categories";
    }
}