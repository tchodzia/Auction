package sda.project.auction.web.mvc;

import sda.project.auction.model.Auction;
import sda.project.auction.model.Category;
import sda.project.auction.model.User;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.CategoryService;
import sda.project.auction.service.UserService;
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
    @GetMapping("/")
    public String welcomePage(ModelMap map) {
        //User user = userService.findById(1L);
        //map.addAttribute("userName", "Jan Kowalski");
        //map.addAttribute("user", user);

        List<Auction> auctionsNew10 = auctionService.findFirst10ByDateOfIssue();
        map.addAttribute("auctionsNew10", auctionsNew10);

        List<Auction> auctionsLast10 = auctionService.findLast10ByDateOfIssue();
        map.addAttribute("auctionsLast10", auctionsLast10);

        List<Category> categories = categoryService.findAllOrderedByParentCategory();
        //map.addAttribute("categories", categories);

        List<Category> sortedCategories = new ArrayList<>();
        for (Category category : categories) {
            sortedCategories.add(category);
            List<Category> downCategories = categoryService.findAllSelectedByParentCategory(category.getID());
            for (Category downCategory : downCategories) {
                sortedCategories.add(downCategory);
            }
        }

        //List<Category> down_categories = categoryService.findAllSelectedByParentCategory();
        map.addAttribute("sortedCategories", sortedCategories);

        return "index";
    }
}