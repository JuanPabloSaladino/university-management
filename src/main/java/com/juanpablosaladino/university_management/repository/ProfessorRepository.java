package com.juanpablosaladino.university_management.repository;

import com.juanpablosaladino.university_management.model.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
