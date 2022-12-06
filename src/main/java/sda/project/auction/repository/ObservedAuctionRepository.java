package sda.project.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.Category;
import sda.project.auction.model.ObservedAuction;

import java.util.List;

public interface ObservedAuctionRepository  extends CrudRepository<ObservedAuction, Long> {

    @Query(value = "SELECT * FROM observed_auctions WHERE user_id=:id", nativeQuery = true)
    public List<ObservedAuction> findAllObservedAuctionsByUserId(@Param("id") Long id);
}
