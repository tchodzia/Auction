package sda.project.auction.web.mvc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sda.project.auction.model.Auction;
import sda.project.auction.model.UserRole;
import sda.project.auction.service.AuctionService;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.UserMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(method={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE, RequestMethod.PUT})
@Slf4j
public class SearchController {
    private final AuctionService auctionService;

    @PostMapping("/search")
    public String handleSearch(@RequestParam(value = "search", required = false) String search, RedirectAttributes redirectAttributes, ModelMap map) {

        List<Auction> auctions = auctionService.findAllAuctionsBySearch(search);
        map.addAttribute("auctions", auctions);
        map.addAttribute("search_value", search);
/*
        log.info("Search from form: {}", search);
        if(errors.hasErrors()){
            map.addAttribute("roles", UserRole.values());
            return "redirect:/";
        }
*/
        //userService.save(UserMapper.toEntity(form));
        //redirectAttributes.addAttribute("message", form.getAccount_name() + " your account was created!");
        return "get-auctions-by-search";
    }


}
