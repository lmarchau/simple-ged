package com.wps.poc.ged.simpleged.web.rest;

import com.wps.poc.ged.simpleged.model.SGDocument;
import com.wps.poc.ged.simpleged.service.SGDocumentService;
import com.wps.poc.ged.simpleged.web.dto.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/documents")
public class FileUploadController {

    private SGDocumentService sgDocumentService;

    public FileUploadController(SGDocumentService sgDocumentService) {
        this.sgDocumentService = sgDocumentService;
    }

    @GetMapping
    public ResponseEntity<Page<SGDocument>> find(Pageable pageable) {
        return ResponseEntity.ok(sgDocumentService.find(pageable));
    }

    @PostMapping
    public ResponseEntity<SGDocument> uploadFile(Document document) throws URISyntaxException, IOException {
        SGDocument doc = new SGDocument();
        doc.setName(document.getName());
        doc.setId(document.getId());
        SGDocument sgDocument = sgDocumentService.create(doc, document.getFile());
        return ResponseEntity.created(new URI("/documents/" + sgDocument.getId())).body(sgDocument);
    }


}
