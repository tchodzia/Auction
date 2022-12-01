package sda.project.auction.repository;

import org.springframework.data.repository.CrudRepository;
import sda.project.auction.model.Auction;

public interface AuctionRepository extends CrudRepository<Auction, Long> {


}
