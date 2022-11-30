package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import sda.project.auction.model.User;
import sda.project.auction.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserRepositoryTestService implements CommandLineRunner {

    private final UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new User("pko@wp.pl","test1", "Pawel", "lodzkie", "Lodz", "blablabla"));
    }
}
