package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sda.project.auction.model.Auction;

import sda.project.auction.model.Bidding;
import sda.project.auction.model.Purchase;
import sda.project.auction.model.User;
import sda.project.auction.repository.AuctionRepository;
import sda.project.auction.repository.BiddingRepository;
import sda.project.auction.repository.PurchaseRepository;
import sda.project.auction.repository.UserRepository;
import sda.project.auction.model.File;
import sda.project.auction.repository.FileDBRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionService {
    private final AuctionRepository repository;
    private final FileDBRepository fileDBRepository;

    private final PurchaseRepository purchaseRepository;

    private final UserRepository userRepository;

    private final BiddingRepository biddingRepository;

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
        if (!auction.isPromoted()) {
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

    public void alterAuctionToInactive(Long auction_id) {

        Auction auction = findById(auction_id);
        auction.setIsActive(false);
        repository.save(auction);
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

    public Auction[] getCurrentRandomAuctions() {
        return repository.getCurrentRandomAuctions();
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


    public void createNewPurchase(Long auction_id, Long user_id) {
        Auction auction = findById(auction_id);
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User with id " + user_id + " not found."));
        Purchase purchase = new Purchase(user, auction, auction.getBUY_NOW_price());
        purchaseRepository.save(purchase);
    }

    public void createNewPurchaseFromBidding(Long auction_id, Long user_id, Bidding bidding) {
        Auction auction = findById(auction_id);
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User with id " + user_id + " not found."));
        Purchase purchase = new Purchase(user, auction, bidding.getAmount());
        purchaseRepository.save(purchase);
    }


    public void findAllAuctionsToDisactivate() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Auction> auctions = StreamSupport.stream(repository.findAll().spliterator(), false).toList();
        for (Auction auction : auctions) {
            if (auction != null && (auction.getIsActive() == true || auction.getIsActive() == null) && currentTime.isAfter(auction.getEnd_date())) {
                auction.setIsActive(false);
                repository.save(auction);
                if (biddingRepository.findBiddingByAuction(auction) != null) {
                    createNewPurchaseFromBidding(auction.getID(), biddingRepository.findBiddingByAuction(auction).getUser().getID(), biddingRepository.findBiddingByAuction(auction));
                }
            }
        }
    }

    public void deleteAuction(Long id) {
        List<File> files = fileDBRepository.getFilesByAuctionId(id);
        for (File file : files) {
            fileDBRepository.deleteById(file.getID());
        }
        repository.deleteById(id);
    }

    public void deactivateAuction(Auction auction) {
        auction.setIsActive(false);
        repository.save(auction);
    }

    public List<Auction> getAuctionByUserAndAuction(Long userID, Long auctionID) {
        List<Auction> auction = repository.findAllByUserId(userID, auctionID);
        return auction;

    }
}
