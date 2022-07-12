package com.andikscript.tokosepatu.repository;

import com.andikscript.tokosepatu.model.EnumRole;
import com.andikscript.tokosepatu.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(EnumRole name);
}
