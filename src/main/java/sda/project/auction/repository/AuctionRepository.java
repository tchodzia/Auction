package sda.project.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.auction.model.Auction;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction, Long> {

    @Query(value = "SELECT * FROM auctions WHERE date_of_issue <= CURRENT_DATE AND end_date >= CURRENT_DATE ORDER BY date_of_issue ASC LIMIT 10", nativeQuery = true)
    public List<Auction> findFirst10ByDateOfIssue();

    @Query(value = "SELECT * FROM auctions WHERE date_of_issue <= CURRENT_DATE AND end_date >= CURRENT_DATE ORDER BY end_date ASC LIMIT 10", nativeQuery = true)
    public List<Auction> findLast10ByDateOfIssue();

    @Query(value = "SELECT * FROM auctions WHERE (date_of_issue <= CURRENT_DATE AND end_date >= CURRENT_DATE) AND user_id=:id ORDER BY end_date ASC", nativeQuery = true)
    public List<Auction> findAllAuctionsByDateOfIssueAndUser(@Param("id") Long id);

    @Query(value = "SELECT * FROM auctions WHERE (end_date < CURRENT_DATE) AND user_id=:id ORDER BY end_date ASC", nativeQuery = true)
    public List<Auction> finishedAuctionsByUser(@Param("id") Long id);

}
