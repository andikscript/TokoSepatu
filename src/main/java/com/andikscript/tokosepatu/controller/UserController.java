package com.andikscript.tokosepatu.controller;

import com.andikscript.tokosepatu.config.HashingSHA256;
import com.andikscript.tokosepatu.model.User;
import com.andikscript.tokosepatu.model.UserGet;
import com.andikscript.tokosepatu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users", produces = "application/json")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity addUser(@RequestBody User user) {
        HashingSHA256 hash = new HashingSHA256();

        if (user.getUsername().equals(null) || user.getPassword().equals(null) ||
                user.getUsername().equals("") || user.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String password = hash.hashing(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // cek username dan password
    @GetMapping(value = "/userpass", consumes = "application/json")
    public boolean getUser(@RequestBody UserGet userget) {
        HashingSHA256 hash = new HashingSHA256();
        List<User> username = userRepository.findUserByUsername(userget.getUsername());
        if (!username.isEmpty()) {
            String password = hash.hashing(userget.getPassword());
            List<User> pass = userRepository.findUserByPassword(password);
            if (!pass.isEmpty()) {
                return true;
            }
            return false;
        }
        return false;
    }
}
