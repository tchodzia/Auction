package sda.project.auction.repository;

import org.springframework.data.repository.CrudRepository;
import sda.project.auction.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
