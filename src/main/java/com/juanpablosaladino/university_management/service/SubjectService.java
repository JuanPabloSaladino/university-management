package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Subject;

public interface SubjectService {
    Iterable<Subject> getSubjects();

    Subject createSubject(Subject subject) throws Exception;

    Subject getSubjectById(Long id) throws Exception;

    Subject updateSubject(Subject subject) throws Exception;

    void deleteSubject(Long id) throws Exception;
}
