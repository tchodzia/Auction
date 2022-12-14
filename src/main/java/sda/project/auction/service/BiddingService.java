package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.User;
import sda.project.auction.repository.AuctionRepository;
import sda.project.auction.repository.BiddingRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BiddingService {

    private final BiddingRepository repository;

    public Bidding save(Bidding bidding) {
        return repository.save(bidding);
    }

    public List<Bidding> findAllBiddingsByUserId(Long id) {
        return repository.findAllBiddingsByUserId(id);
    }

    public Bidding findBiddingByAuctionId(Long id){ return repository.findBiddingByAuctionID(id);
    }

}
