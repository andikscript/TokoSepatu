package com.andikscript.tokosepatu.security.service;

import com.andikscript.tokosepatu.model.User;
import com.andikscript.tokosepatu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// object yang digunakan untuk mendapatkan user detail secara kustom
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // untuk mendapatkan informasi user dari UserRepository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username : " + username));
        // informasi user yang didapatkan dari UserRepository di masukkan ke UserDetailsImpl
        // untuk digunakan sebagai response di signin
        return UserDetailsImpl.build(user);
    }
}
