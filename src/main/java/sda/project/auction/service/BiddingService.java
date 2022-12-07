package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Bidding;
import sda.project.auction.repository.AuctionRepository;
import sda.project.auction.repository.BiddingRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BiddingService {

    private final BiddingRepository repository;


    public List<Bidding> findAllBiddingsByUserId(Long id) {
        return StreamSupport.stream(repository.findAllBiddingsByUserId(id).spliterator(), false)
                .collect(toList());
    }

}
