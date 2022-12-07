package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.repository.AuctionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionRepository repository;

    public Auction save(Auction auction){
        return repository.save(auction);
    }

    public Auction findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Auction with id " + id + " not found."));
    }


    public List<Auction> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(toList());
    }

    public List<Auction> findFirst10ByDateOfIssue() {
        return StreamSupport.stream(repository.findFirst10ByDateOfIssue().spliterator(), false)
                .collect(toList());
    }

    public List<Auction> findLast10ByDateOfIssue() {
        return StreamSupport.stream(repository.findLast10ByDateOfIssue().spliterator(), false)
                .collect(toList());
    }

    public List<Auction> findAllAuctionsByDateOfIssueAndUser(Long id) {
        return StreamSupport.stream(repository.findAllAuctionsByDateOfIssueAndUser(id).spliterator(), false)
                .collect(toList());
    }

    public List<Auction> finishedAuctionsByUser(Long id) {
        return StreamSupport.stream(repository.finishedAuctionsByUser(id).spliterator(), false)
                .collect(toList());
    }
}
