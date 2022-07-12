package com.andikscript.tokosepatu.controller.adminaccess;

import com.andikscript.tokosepatu.config.HashingSHA256;
import com.andikscript.tokosepatu.model.User;
import com.andikscript.tokosepatu.payload.UsernamePasswordRequest;
import com.andikscript.tokosepatu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user-management")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

}
