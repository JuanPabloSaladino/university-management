package com.juanpablosaladino.university_management.repository;

import com.juanpablosaladino.university_management.model.TypeOfIdentificationDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationDocumentRepository extends CrudRepository<TypeOfIdentificationDocument, Long> {
}
