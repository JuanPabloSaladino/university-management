package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Student;
import com.juanpablosaladino.university_management.model.User;
import com.juanpablosaladino.university_management.repository.StudentRepository;
import com.juanpablosaladino.university_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private UserRepository userRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Student createStudent(Student student) throws Exception {

        if (checkUserEmailIsAvaible(student) && checkPasswordIsValid(student)) {
            student = studentRepository.save(student);
        }

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

    @Override
    public void deleteStudent(Long id) throws Exception {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    private boolean checkUserEmailIsAvaible(User user) throws Exception {
        Optional<User> userFound = userRepository.findByEmail(user.getEmail());
        if (userFound.isPresent()) {
            throw new Exception("Email in use");
        }
        return true;
    }

    public boolean checkPasswordIsValid(User user) throws Exception {
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            throw new Exception("Passwords are diferents");
        }
        return true;
    }

}
