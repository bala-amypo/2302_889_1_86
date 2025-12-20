
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

// package com.example.demo.service.impl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;

// import static org.springframework.http.HttpStatus.BAD_REQUEST;

// @Service
// @RequiredArgsConstructor
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;

//     @Override
//     public User register(User user) {
//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new ResponseStatusException(BAD_REQUEST, "Email");
//         }
//         user.setPassword(encoder.encode(user.getPassword()));
//         return userRepository.save(user);
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "User not found"));
//     }

//     @Override
//     public User findById(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));
//     }
// }
