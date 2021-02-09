package com.juanpablosaladino.university_management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Student extends User {

    @Column
    private String file;

    @ManyToOne
    private TypeOfIdentificationDocument typeOfIdentificationDocument;

    @Column
    @NotBlank(message = "Enter identification document")
    private String identificationDocument;

    public Student() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public TypeOfIdentificationDocument getTypeOfIdentificationDocument() {
        return typeOfIdentificationDocument;
    }

    public void setTypeOfIdentificationDocument(TypeOfIdentificationDocument typeOfIdentificationDocument) {
        this.typeOfIdentificationDocument = typeOfIdentificationDocument;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

}
