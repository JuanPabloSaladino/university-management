package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Professor;
import com.juanpablosaladino.university_management.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor createProfessor(Professor professor) throws Exception {
        return professorRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(Long id) throws Exception {
        return professorRepository.findById(id).orElseThrow(() -> new Exception("Professor does not exist"));
    }

    @Override
    public Iterable<Professor> getProfessors() {
        return professorRepository.findAll();
    }

}
