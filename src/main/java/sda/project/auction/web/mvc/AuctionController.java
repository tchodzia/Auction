package sda.project.auction.web.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.UserService;
import sda.project.auction.web.form.CreateUserForm;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping()
public class AuctionController {
        private final AuctionService auctionService;

        @GetMapping("/get-auctions")
        public String getAuctions(ModelMap map) {

            // map.addAttribute("user", new CreateUserForm());

            return "get-auctions";
        }
}
