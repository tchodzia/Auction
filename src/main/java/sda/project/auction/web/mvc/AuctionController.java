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
import sda.project.auction.model.Auction;
import sda.project.auction.model.Category;
import sda.project.auction.model.File;
import sda.project.auction.model.User;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.CategoryService;
import sda.project.auction.service.FileStorageService;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateAuctionForm;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.AuctionMapper;
import sda.project.auction.web.mappers.UserMapper;

import java.util.List;

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
       //Auction auction = auctionService.findById(1L);
       Auction auction = new Auction();
       map.addAttribute("auction", auction);

       User user = userService.findById(id);
       map.addAttribute("user", user);

       List<File> files = fileStorageService.getFilesByAuctionId(auction.getID());
       map.addAttribute("files", files);
       return "create-auction";
    }

    @PostMapping("/add/{id}")
    public String handleCreateAuctionForm(@PathVariable("id") Long id, @ModelAttribute("auction") @Valid CreateAuctionForm form, Errors errors, final RedirectAttributes redirectAttributes, ModelMap map) {
            User user = userService.findById(id);
            map.addAttribute("user", user);
            Auction auction = AuctionMapper.toEntity(form, user);
            //map.addAttribute("auction", auction);
            List<File> files = fileStorageService.getFilesByAuctionId(auction.getID());
            map.addAttribute("files", files);
            log.info("Create auction from form: {}", form);
            if (errors.hasErrors()){
                return "create-auction";
            }
        auctionService.save(auction);
        redirectAttributes.addAttribute("message", form.getTitle() + " auction was created!");
        return "redirect:/";
    }
/*
    @GetMapping("/update")
    public String displayUpdateAuctionForm(ModelMap map) {
        Auction auction = auctionService.findById(1L);
        map.addAttribute("auction", auction);

        List<File> files = fileStorageService.getFilesByAuctionId(auction.getID());
        map.addAttribute("files", files);
        return "update-auction";
    }
*/

    @GetMapping("/{id}")
    public String displayAuctionById(@PathVariable("id") Long id, ModelMap map) {
        Auction auction = auctionService.findById(id);
        map.addAttribute("auction", auction);

        return "get-auction";
    }



}
