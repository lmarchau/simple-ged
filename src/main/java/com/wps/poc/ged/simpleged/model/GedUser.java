package com.wps.poc.ged.simpleged.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class GedUser {

    @Id
    private String id;

    private String username;

    private String password;

}
