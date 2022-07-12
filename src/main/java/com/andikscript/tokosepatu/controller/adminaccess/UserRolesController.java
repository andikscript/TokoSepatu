package com.andikscript.tokosepatu.controller.adminaccess;

import com.andikscript.tokosepatu.model.UserRole;
import com.andikscript.tokosepatu.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRolesController {

    @Autowired
    private UserRolesRepository userRolesRepository;

    @GetMapping("/userroles")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRole> userRoleList() {
        return userRolesRepository.findAll();
    }
}
