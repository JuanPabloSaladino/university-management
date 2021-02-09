package com.juanpablosaladino.university_management.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Professor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TypeOfIdentificationDocument typeOfIdentificationDocument;

    @Column
    @NotBlank(message = "Enter identification document")
    private String identificationDocument;

    @Column
    private boolean active;

    public Professor() {
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
