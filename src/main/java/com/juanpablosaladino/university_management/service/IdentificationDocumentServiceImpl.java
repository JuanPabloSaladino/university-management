package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.controller.TypeOfIdentificationDocument;
import com.juanpablosaladino.university_management.repository.IdentificationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationDocumentServiceImpl implements IdentificationDocumentService {

    private IdentificationDocumentRepository identificationDocumentRepository;

    @Autowired
    public IdentificationDocumentServiceImpl(IdentificationDocumentRepository identificationDocumentRepository) {
        this.identificationDocumentRepository = identificationDocumentRepository;
    }

    @Override
    public Iterable<TypeOfIdentificationDocument> getTypesOfIdentificationDocument() {
        return identificationDocumentRepository.findAll();
    }
}
