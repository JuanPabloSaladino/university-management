package com.juanpablosaladino.university_management.repository;

import com.juanpablosaladino.university_management.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByIdentificationDocument(String identificationDocument);

}
