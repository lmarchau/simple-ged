package com.wps.poc.ged.simpleged.service;

import com.wps.poc.ged.simpleged.model.SGDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SGDocumentService {

    Page<SGDocument> find(Pageable pageable);

    SGDocument create(SGDocument document, MultipartFile file) throws IOException;
}
