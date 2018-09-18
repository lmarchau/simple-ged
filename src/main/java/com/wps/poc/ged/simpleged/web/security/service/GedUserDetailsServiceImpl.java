package com.wps.poc.ged.simpleged.web.security.service;

import com.wps.poc.ged.simpleged.model.GedUser;
import com.wps.poc.ged.simpleged.repository.GedUserRepository;
import com.wps.poc.ged.simpleged.web.security.domain.GedUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GedUserDetailsServiceImpl implements UserDetailsService {

    private GedUserRepository gedUserRepository;

    public GedUserDetailsServiceImpl(GedUserRepository gedUserRepository) {
        this.gedUserRepository = gedUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GedUser gedUser = gedUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new GedUserDetails(gedUser.getUsername(), gedUser.getPassword());
    }
}
