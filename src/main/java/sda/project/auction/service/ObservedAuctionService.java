package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.ObservedAuction;
import sda.project.auction.model.User;
import sda.project.auction.repository.BiddingRepository;
import sda.project.auction.repository.ObservedAuctionRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ObservedAuctionService {

    private final ObservedAuctionRepository repository;


    public List<ObservedAuction> findAllObservedAuctionsByUserId(Long id) {
        return repository.findAllObservedAuctionsByUserId(id);
    }

    public ObservedAuction observeAuction(Auction auction, User user) {
        return repository.save(new ObservedAuction(null, auction, user));
    }

    public List<ObservedAuction> findAllObservedAuctionsByUserIdAndAuctionID(Long user_id, Long auction_id) {
        return repository.findAllObservedAuctionsByUserIdAndAuctionID(user_id, auction_id);
    }

    public void stopObserveAuction(ObservedAuction observedAuction) {
        repository.delete(observedAuction);
    }
}
