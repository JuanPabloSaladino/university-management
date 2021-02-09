package com.juanpablosaladino.university_management.model;

import javax.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String information;

    @Column
    private boolean avaible;

    @Column
    private Integer max_number_of_students;

    @ManyToOne
    private Enrollment enrollment;

    @ManyToOne
    private Professor professor;

    public Subject() {
    }

    public Subject(Long id, String name, String description, String information, boolean avaible, Integer max_number_of_students, Enrollment enrollment, Professor professor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.information = information;
        this.avaible = avaible;
        this.max_number_of_students = max_number_of_students;
        this.enrollment = enrollment;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isAvaible() {
        return avaible;
    }

    public void setAvaible(boolean avaible) {
        this.avaible = avaible;
    }

    public Integer getMax_number_of_students() {
        return max_number_of_students;
    }

    public void setMax_number_of_students(Integer max_number_of_students) {
        this.max_number_of_students = max_number_of_students;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
