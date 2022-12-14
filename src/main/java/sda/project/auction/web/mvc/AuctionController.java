package sda.project.auction.web.mvc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.project.auction.model.*;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.CategoryService;
import sda.project.auction.service.FileStorageService;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateAuctionForm;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.AuctionMapper;
import sda.project.auction.web.mappers.UserMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(
        path="/auctions",
        method={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE, RequestMethod.PUT}
)
public class AuctionController {
        private final AuctionService auctionService;
        private final CategoryService categoryService;

        private final FileStorageService fileStorageService;

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

    @GetMapping("/create/{id}")
    public String displayCreateAuctionForm(@PathVariable("id") Long id, ModelMap map) {
        map.addAttribute("update", false);
       //Auction auction = auctionService.findById(1L);
       Auction auction = new Auction();
       map.addAttribute("auction", auction);

       User user = userService.findById(id);
       map.addAttribute("user", user);

       List<File> files = fileStorageService.getFilesByAuctionId(auction.getID());
       map.addAttribute("files", files);

        List<CategoryTree> categories = categoryService.findAllCategoryTree();
        map.addAttribute("categories", categories);

        Map<String, String> promotedOptions = new HashMap<String, String>();
        promotedOptions.put("false", "nie");
        promotedOptions.put("true", "tak");
        map.addAttribute("promotedOptions", promotedOptions);

       // map.addAttribute("p_date_of_issue", auction.getDate_of_issue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
       // map.addAttribute("p_end_date", auction.getEnd_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

       return "create-auction";
    }

    @PostMapping("/add/{id}")
    public String handleCreateAndUpdateAuctionForm(@PathVariable("id") Long id, @ModelAttribute("auction") @Valid CreateAuctionForm form, Errors errors, final RedirectAttributes redirectAttributes, ModelMap map) {
        map.addAttribute("update", false);
            User user = userService.findById(id);
            map.addAttribute("user", user);
            Auction auction = AuctionMapper.toEntity(form, user);
            //map.addAttribute("auction", auction);

        List<File> files = fileStorageService.getFilesByAuctionId(auction.getID());
            map.addAttribute("files", files);

            Map<String, String> promotedOptions = new HashMap<String, String>();
            promotedOptions.put("false", "nie");
            promotedOptions.put("true", "tak");
            map.addAttribute("promotedOptions", promotedOptions);


        List<CategoryTree> categories = categoryService.findAllCategoryTree();
        map.addAttribute("categories", categories);

            log.info("Create auction from form: {}", form);
            if (errors.hasErrors()){
                return "create-auction";
            }
        auctionService.save(auction);

        redirectAttributes.addAttribute("message", form.getTitle() + " auction was created!");
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String displayUpdateAuctionForm(@PathVariable("id") Long id, ModelMap map) {
            map.addAttribute("update", true);
            Auction auction = auctionService.findById(id);
             map.addAttribute("auction", auction);

            User user = userService.findById(auction.getUser().getID());
            map.addAttribute("user", user);

            List<File> files = fileStorageService.getFilesByAuctionId(auction.getID());
            map.addAttribute("files", files);

            List<CategoryTree> categories = categoryService.findAllCategoryTree();
            map.addAttribute("categories", categories);

            Map<String, String> promotedOptions = new HashMap<String, String>();
            promotedOptions.put("false", "nie");
            promotedOptions.put("true", "tak");
            map.addAttribute("promotedOptions", promotedOptions);

            return "create-auction";
    }


    @GetMapping("/{id}")
    public String displayAuctionById(@PathVariable("id") Long id, ModelMap map) {
        Auction auction = auctionService.findById(id);
        map.addAttribute("auction", auction);

        return "get-auction";
    }



}
