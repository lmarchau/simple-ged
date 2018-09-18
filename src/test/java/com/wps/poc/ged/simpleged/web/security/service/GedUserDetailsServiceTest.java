package com.wps.poc.ged.simpleged.web.security.service;

import com.wps.poc.ged.simpleged.builder.GedUserBuilder;
import com.wps.poc.ged.simpleged.model.GedUser;
import com.wps.poc.ged.simpleged.repository.GedUserRepository;
import com.wps.poc.ged.simpleged.web.security.domain.GedUserDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GedUserDetailsServiceTest {

    private static final String PLUE = "plue";
    private static final String PASSWORD = "password";

    @Mock
    private GedUserRepository gedUserRepository;

    @InjectMocks
    private GedUserDetailsServiceImpl gedUserDetailsService;

    @Test
    public void findByUsernameShouldSuccess() {
        when(gedUserRepository.findByUsername(PLUE))
                .thenReturn(Optional.of(new GedUserBuilder()
                        .username(PLUE)
                        .password(PASSWORD)
                        .build()));

        GedUserDetails result = (GedUserDetails) gedUserDetailsService.loadUserByUsername(PLUE);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(PLUE);
        assertThat(result.getPassword()).isEqualTo(PASSWORD);

        verify(gedUserRepository).findByUsername(PLUE);
    }

    @Test
    public void findByUsernameShoulThrowUserNameNotFoundException() {
        when(gedUserRepository.findByUsername(PLUE))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> gedUserDetailsService.loadUserByUsername(PLUE))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(PLUE);

        verify(gedUserRepository).findByUsername(PLUE);
    }
}
