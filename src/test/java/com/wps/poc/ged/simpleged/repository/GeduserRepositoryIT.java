package com.wps.poc.ged.simpleged.repository;

import com.wps.poc.ged.simpleged.builder.GedUserBuilder;
import com.wps.poc.ged.simpleged.model.GedUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GeduserRepositoryIT {


    private static final String GON = "Gon";
    private static final String KIRUA = "Kirua";
    private static final String HISOKA = "hisoka";
    private static final String UNKNOWN = "unknown";

    @Autowired
    private GedUserRepository gedUserRepository;

    @Before
    public void before() {
        GedUser gon = new GedUserBuilder().username(GON).build();
        GedUser kirua = new GedUserBuilder().username(KIRUA).build();
        GedUser hisoka = new GedUserBuilder().username(HISOKA).build();
        gedUserRepository.saveAll(Arrays.asList(gon, kirua, hisoka));
    }

    @After
    public void after() {
        gedUserRepository.deleteAll();
    }

    @Test
    public void findByUsernameShouldSuccess() {
        Optional<GedUser> gon = gedUserRepository.findByUsername(GON);
        assertThat(gon).isPresent();
        assertThat(gon.get().getUsername()).isEqualTo(GON);
    }

    @Test
    public void findByUsernameShouldReturnEmptyOptional() {
        assertThat(gedUserRepository.findByUsername(UNKNOWN)).isNotPresent();
    }

}
