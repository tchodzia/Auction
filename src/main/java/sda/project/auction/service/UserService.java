package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sda.project.auction.model.User;
import sda.project.auction.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user){
        return repository.save(user);
    }
}
