package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.controller.TypeOfIdentificationDocument;

public interface IdentificationDocumentService {

    Iterable<TypeOfIdentificationDocument> getTypesOfIdentificationDocument();

}
