package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.User;
import sda.project.auction.repository.AuctionRepository;
import sda.project.auction.repository.BiddingRepository;
import sda.project.auction.repository.UserRepository;
import sda.project.auction.web.form.BidForm;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BiddingService {

    private final BiddingRepository repository;

    private final UserRepository userRepository;

    public Bidding save(Bidding bidding) {
        return repository.save(bidding);
    }

    public List<Bidding> findAllBiddingsByUserId(Long id) {
        return repository.findAllBiddingsByUserId(id);
    }

    public Bidding findBiddingByAuctionId(Long id){ return repository.findBiddingByAuctionID(id);
    }

    public Bidding findBiddingByAction(Auction auction){
        return repository.findBiddingByAuction(auction);
    }

    public void update(BidForm bidForm) {
        Bidding bidding = findBiddingByAuctionId(bidForm.getAuction_id());
        Long currentPrice = bidding.getAmount();
        if (bidForm.getAmount() > currentPrice){
            bidding.setAmount(bidForm.getAmount());
            User biddingUser = userRepository.findById(bidForm.getUser_id()).get();
            bidding.setUser(biddingUser);
            repository.save(bidding);
        }
        else {
            throw new RuntimeException("New amount must be higher than current amount!");
        }
    }
}
