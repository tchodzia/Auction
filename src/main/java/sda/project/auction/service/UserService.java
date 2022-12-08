package sda.project.auction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sda.project.auction.model.User;
import sda.project.auction.repository.UserRepository;
import sda.project.auction.web.form.CreateUserForm;
import sda.project.auction.web.mappers.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder encoder;

    private final UserRepository repository;

    public User save(User user) {
        user.setPassword(encoder.encode((CharSequence) user.getPassword()));
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found."));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User with email " + email + " not found."));
    }

    public User update(CreateUserForm form) {
        User user = UserMapper.toUpdateEntity(findById(form.getID()), form);
        return repository.save(user);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

}
