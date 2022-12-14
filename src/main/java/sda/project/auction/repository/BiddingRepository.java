package sda.project.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.auction.model.Bidding;

import java.util.List;

public interface BiddingRepository  extends CrudRepository<Bidding, Long> {

     @Query(value = "SELECT * FROM biddings WHERE user_id=:id", nativeQuery = true)
     public List<Bidding> findAllBiddingsByUserId(@Param("id") Long id);

     Bidding findBiddingByAuctionID(Long id);

}
