package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.TypeOfIdentificationDocument;

public interface IdentificationDocumentService {

    Iterable<TypeOfIdentificationDocument> getTypesOfIdentificationDocument();

}
