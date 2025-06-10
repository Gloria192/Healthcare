package org.example.healthcare.user;


import lombok.Setter;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private  final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(User user) {

        return userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User updateUser(User user) {

        return userRepository.save(user);
    }
}

