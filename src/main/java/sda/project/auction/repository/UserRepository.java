package sda.project.auction.repository;

import org.springframework.data.repository.CrudRepository;
import sda.project.auction.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}
