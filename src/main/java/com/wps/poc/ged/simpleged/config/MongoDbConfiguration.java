package com.wps.poc.ged.simpleged.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import com.wps.poc.ged.simpleged.converter.DateToZonedDateTimeConverter;
import com.wps.poc.ged.simpleged.converter.ZonedDateTimeToDateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;

@Configuration
@EnableMongoRepositories("com.wps.poc.ged.simpleged.repository")
@EnableMongoAuditing
@Import(MongoAutoConfiguration.class)
@Slf4j
public class MongoDbConfiguration {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean localValidatorFactoryBean) {
        return new ValidatingMongoEventListener(localValidatorFactoryBean);
    }

    @Bean
    public CustomConversions customConversions() {
        return new CustomConversions(CustomConversions.StoreConversions.NONE, Arrays.asList(DateToZonedDateTimeConverter.INSTANCE, ZonedDateTimeToDateConverter.INSTANCE));
    }

    @Bean
    public Mongobee mongobee(MongoClient mongoClient, MongoTemplate mongoTemplate, MongoProperties mongoProperties) {
        log.info("Starting Mongobee configuration ...");
        Mongobee mongobee = new Mongobee(mongoClient);
        mongobee.setDbName(mongoProperties.getDatabase());
        mongobee.setMongoTemplate(mongoTemplate);
        mongobee.setChangeLogsScanPackage("com.wps.poc.ged.simpleged.config.dbmigration");
        mongobee.setEnabled(true);
        return mongobee;

    }

}
