package sda.project.auction.web.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.project.auction.model.Auction;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateUserForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/get-auctions")
public class AuctionController {
        private final AuctionService auctionService;

        @GetMapping("/{id}")
        public String displayAuction(@PathVariable("id") Long id, ModelMap map) {
            List<Auction> auctions = auctionService.findAllAuctionsByDateOfIssueAndUser(id);
            map.addAttribute("auctions", auctions);

//            map.addAttribute("user", new CreateUserForm());
//            map.addAttribute("roles", User.Roles.values());


            // map.addAttribute("user", new CreateUserForm());

            return "get-auctions";
        }


}
