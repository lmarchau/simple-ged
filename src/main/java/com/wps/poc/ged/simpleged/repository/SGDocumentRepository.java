package com.wps.poc.ged.simpleged.repository;

import com.wps.poc.ged.simpleged.model.SGDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SGDocumentRepository extends MongoRepository<SGDocument, String> {
}
