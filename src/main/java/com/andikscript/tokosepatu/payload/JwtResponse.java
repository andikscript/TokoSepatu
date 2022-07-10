package com.andikscript.tokosepatu.payload;

import com.andikscript.tokosepatu.model.Role;

import java.util.List;

public class JwtResponse {
    private String accessToken;
    private String username;
    private String email;
    private List roles;

    public JwtResponse(String accessToken, String username, String email, List roles) {
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List getRoles() {
        return roles;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
