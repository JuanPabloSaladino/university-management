package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Student;

public interface StudentService {

    Student createStudent(Student student) throws Exception;

    Iterable<Student> getStudents();

    Student getStudentByIdentificationDocument(String identificationDocument) throws Exception;

}
