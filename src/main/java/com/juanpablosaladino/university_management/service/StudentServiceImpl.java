package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Student;
import com.juanpablosaladino.university_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) throws Exception {

        student = studentRepository.save(student);

        return student;
    }

    @Override
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByIdentificationDocument(String identificationDocument) throws Exception {
        return studentRepository.findByIdentificationDocument(identificationDocument);
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
        return studentRepository.findById(id).orElseThrow(() -> new Exception("Student does not exists"));
    }

}
