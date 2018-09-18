package com.wps.poc.ged.simpleged.web.rest;

import com.wps.poc.ged.simpleged.model.SGDocument;
import com.wps.poc.ged.simpleged.service.SGDocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/")
    public ResponseEntity<Page<SGDocument>> find(Pageable pageable) {
        return ResponseEntity.ok(sgDocumentService.find(pageable));
    }

    @PostMapping("/")
    public ResponseEntity<SGDocument> uploadFile(@RequestBody SGDocument sgDocument, @RequestParam("file") MultipartFile file) throws URISyntaxException, IOException {
        SGDocument document = sgDocumentService.create(sgDocument, file);
        return ResponseEntity.created(new URI("/document/" + document.getId())).body(document);
    }


}
