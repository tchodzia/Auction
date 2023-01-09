package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;
import sda.project.auction.repository.AuctionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionService {
    private final AuctionRepository repository;

    public Auction save(Auction auction) {
        return repository.save(auction);
    }

    public Auction update(Auction auction) {
        Auction current = findById(auction.getID());

        log.info("ID: " + auction.getID());

        if (auction.getTitle() == null) {
            auction.setTitle(current.getTitle());
        }
        if (auction.getDescription() == null) {
            auction.setDescription(current.getDescription());
        }
        if (auction.isPromoted() == false) {
            auction.setPromoted(current.isPromoted());
        }
        if (auction.getCategory() == null) {
            auction.setCategory(current.getCategory());
        }
        if (auction.getLocalization() == null) {
            auction.setLocalization(current.getLocalization());
        }
        if (auction.getDate_of_issue() == null) {
            auction.setDate_of_issue(current.getDate_of_issue());
        }
        if (auction.getEnd_date() == null) {
            auction.setEnd_date(current.getEnd_date());
        }
        if (auction.getMin_price() == null) {
            auction.setMin_price(current.getMin_price());
        }
        if (auction.getBUY_NOW_price() == null) {
            auction.setBUY_NOW_price(current.getBUY_NOW_price());
        }
        if (auction.getNumbers_of_visitors() == null) {
            auction.setNumbers_of_visitors(current.getNumbers_of_visitors());
        }
        if (auction.getUser() == null) {
            auction.setUser(current.getUser());
        }

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
        return repository.findFirst10ByDateOfIssue();
    }

    public List<Auction> findLast10ByDateOfIssue() {
        return repository.findLast10ByDateOfIssue();
    }

    public List<Auction> findAllAuctionsByDateOfIssueAndUser(Long id) {
        return repository.findAllAuctionsByDateOfIssueAndUser(id);
    }

    public List<Auction> finishedAuctionsByUser(Long id) {
        return repository.finishedAuctionsByUser(id);
    }

    public Auction getCurrentRandomAuction() {
        return repository.getCurrentRandomAuction();
    }

    public List<Auction> findAllCurrentAuctionsByCategory(Long id) {
        return repository.findAllCurrentAuctionsByCategory(id);
    }


    public List<Auction> findAllAuctionsBySearch(String search) {
        if (search.matches("[A-Za-z0-9_\s]+")) {
            search = "%" + search.toUpperCase() + "%";
            return repository.findAllAuctionsBySearch(search);
        } else {
            return new ArrayList<>();
        }
    }
}
