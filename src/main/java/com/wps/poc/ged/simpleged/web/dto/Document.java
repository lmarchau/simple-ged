package com.wps.poc.ged.simpleged.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Document {

    private String id;
    private String name;
    private MultipartFile file;
}
