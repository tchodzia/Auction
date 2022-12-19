package sda.project.auction.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sda.project.auction.model.Auction;
import sda.project.auction.model.Bidding;
import sda.project.auction.model.File;

import java.util.List;

public interface FileDBRepository extends CrudRepository<File, Long> {

    @Query(value = "SELECT * FROM files", nativeQuery = true)
    public List<File> findAll();

    @Query(value = "SELECT * FROM files WHERE auction_id=:id", nativeQuery = true)
    public List<File> getFilesByAuctionId(@Param("id") Long id);
    //@Query(value = "DELETE FROM files WHERE id=:id", nativeQuery = true)

}
