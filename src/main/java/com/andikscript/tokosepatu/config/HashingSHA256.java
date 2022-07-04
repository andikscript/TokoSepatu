package com.andikscript.tokosepatu.config;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashingSHA256 {

    public String hashing(String password) {
        String sha256 = Hashing
                .sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return sha256;
    }
}
