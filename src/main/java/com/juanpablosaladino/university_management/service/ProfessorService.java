package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Professor;

import java.util.Optional;

public interface ProfessorService {

    Professor createProfessor(Professor professor) throws Exception;

    Professor getProfessorById(Long id) throws Exception;

    Iterable<Professor> getProfessors();


}
