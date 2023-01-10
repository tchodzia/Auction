package sda.project.auction.repository;

import org.springframework.data.repository.CrudRepository;
import sda.project.auction.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
