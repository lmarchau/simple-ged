package com.wps.poc.ged.simpleged.repository;

import com.wps.poc.ged.simpleged.model.GedUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GedUserRepository extends MongoRepository<GedUser, String> {

    Optional<GedUser> findByUsername(String username);

}
