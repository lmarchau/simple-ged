package com.wps.poc.ged.simpleged.config;

import com.wps.poc.ged.simpleged.config.property.SGProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SGProperties.class)
public class SimpleGedConfiguration {
}
