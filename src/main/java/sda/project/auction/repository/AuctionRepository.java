package sda.project.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.auction.model.Auction;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction, Long> {

    @Query(value = "SELECT * FROM auctions WHERE date_of_issue <= NOW() AND end_date >= NOW() AND is_active = 1 ORDER BY date_of_issue ASC LIMIT 10", nativeQuery = true)
    public List<Auction> findFirst10ByDateOfIssue();

    @Query(value = "SELECT * FROM auctions WHERE date_of_issue <= NOW() AND end_date >= NOW() AND is_active = 1 ORDER BY end_date ASC LIMIT 10", nativeQuery = true)
    public List<Auction> findLast10ByDateOfIssue();

    @Query(value = "SELECT * FROM auctions WHERE ((date_of_issue <= NOW() AND end_date >= NOW()) AND is_active = 1) AND user_id=:id ORDER BY end_date ASC", nativeQuery = true)
    public List<Auction> findAllAuctionsByDateOfIssueAndUser(@Param("id") Long id);

    @Query(value = "SELECT * FROM auctions WHERE (end_date < NOW() OR is_active = 0) AND user_id=:id  ORDER BY end_date ASC", nativeQuery = true)
    public List<Auction> finishedAuctionsByUser(@Param("id") Long id);

    @Query(value = "SELECT * FROM auctions WHERE ((date_of_issue <= NOW() AND end_date >= NOW()) AND is_active = 1) ORDER BY RAND() ASC LIMIT 4", nativeQuery = true)
    public Auction[] getCurrentRandomAuctions();

    @Query(value = "SELECT * FROM auctions WHERE (date_of_issue <= NOW() AND end_date >= NOW()) AND category=:id ORDER BY end_date ASC", nativeQuery = true)
    public List<Auction> findAllCurrentAuctionsByCategory(@Param("id") Long id);


    @Query(value = "SELECT * FROM auctions WHERE (date_of_issue <= NOW() AND end_date >= NOW()) AND (UPPER(title) LIKE :search OR UPPER(description) LIKE :search) ORDER BY promoted DESC, end_date ASC", nativeQuery = true)
    public List<Auction> findAllAuctionsBySearch(@Param("search") String search);

    @Query(value = "SELECT * FROM auctions WHERE user_id = :userID and id = :auctionID", nativeQuery = true)
    public List<Auction> findAllByUserId(@Param("userID") Long userID, @Param("auctionID") Long auctionID);

}
