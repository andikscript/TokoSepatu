package com.andikscript.tokosepatu.repository;

import com.andikscript.tokosepatu.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRole, Integer> {
}
