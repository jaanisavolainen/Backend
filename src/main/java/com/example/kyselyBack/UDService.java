package com.example.kyselyBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.kyselyBack.domain.EndUser;
import com.example.kyselyBack.domain.EndUserRepository;

@Component
public class UDService implements UserDetailsService {

    @Autowired
    EndUserRepository uRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	EndUser user = uRepo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Käyttäjää " + username + " ei löytynyt.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRoles())
        );
    }
}
