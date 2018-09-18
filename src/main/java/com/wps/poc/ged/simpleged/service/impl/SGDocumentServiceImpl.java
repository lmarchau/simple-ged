package com.wps.poc.ged.simpleged.service.impl;

import com.wps.poc.ged.simpleged.config.property.SGProperties;
import com.wps.poc.ged.simpleged.model.SGDocument;
import com.wps.poc.ged.simpleged.repository.SGDocumentRepository;
import com.wps.poc.ged.simpleged.service.SGDocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class SGDocumentServiceImpl implements SGDocumentService {

    private SGDocumentRepository sgDocumentRepository;

    private Path rootLocation;

    public SGDocumentServiceImpl(SGDocumentRepository sgDocumentRepository, SGProperties sgProperties) {
        this.sgDocumentRepository = sgDocumentRepository;
        this.rootLocation = sgProperties.getRootLocation();
    }

    @Override
    public Page<SGDocument> find(Pageable pageable) {
        return sgDocumentRepository.findAll(pageable);
    }

    @Override
    public SGDocument create(SGDocument document, MultipartFile file) throws IOException {
        writeFile(file);
        SGDocument result = sgDocumentRepository.save(document);
        return result;
    }

    private void writeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            // This is a security check
            throw new IllegalArgumentException(
                    "Cannot store file with relative path outside current directory "
                            + filename);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
