package com.wps.poc.ged.simpleged.config.dbmigration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.wps.poc.ged.simpleged.builder.GedUserBuilder;
import com.wps.poc.ged.simpleged.model.GedUser;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class AddDefaultUserMigration {

    @ChangeSet(order = "01", author = "system", id = "01-add-default-user")
    public void addDefaultUser(MongoTemplate mongoTemplate) {

        // $2a$10$Jn2LZwV4UMWm0yHoJqJFj.CHEY8iyKFgZr6JEu79TxX6QrRjTEoM. -> password
        GedUser defaultUser = new GedUserBuilder().username("admin").password("$2a$10$Jn2LZwV4UMWm0yHoJqJFj.CHEY8iyKFgZr6JEu79TxX6QrRjTEoM.").build();
        mongoTemplate.save(defaultUser);

    }

}
