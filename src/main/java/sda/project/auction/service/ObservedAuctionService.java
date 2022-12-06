package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.ObservedAuction;
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
        return StreamSupport.stream(repository.findAllObservedAuctionsByUserId(id).spliterator(), false)
                .collect(toList());
    }
}
