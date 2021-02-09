package com.juanpablosaladino.university_management.repository;

import com.juanpablosaladino.university_management.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
