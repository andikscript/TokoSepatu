package com.andikscript.tokosepatu.repository;

import com.andikscript.tokosepatu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findUserByUsername(String username);

    List<User> findUserByPassword(String username);
}
