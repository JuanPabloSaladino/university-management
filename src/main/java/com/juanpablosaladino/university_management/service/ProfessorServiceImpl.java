package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Professor;
import com.juanpablosaladino.university_management.model.User;
import com.juanpablosaladino.university_management.repository.ProfessorRepository;
import com.juanpablosaladino.university_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;
    private UserRepository userRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository, UserRepository userRepository) {
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Professor createProfessor(Professor professor) throws Exception {
        if (checkUserEmailIsAvaible(professor)) {
            professor = professorRepository.save(professor);
        }
        return professor;
    }

    @Override
    public Professor getProfessorById(Long id) throws Exception {
        return professorRepository.findById(id).orElseThrow(() -> new Exception("Professor does not exists"));
    }

    @Override
    public Iterable<Professor> getProfessors() {
        return professorRepository.findAll();
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
