package com.juanpablosaladino.university_management.service;

import com.juanpablosaladino.university_management.model.Subject;
import com.juanpablosaladino.university_management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Iterable<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject createSubject(Subject subject) throws Exception {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) throws Exception {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new Exception("Subject does not exist"));
        return subject;
    }

    @Override
    public Subject updateSubject(Subject subject) throws Exception {
        return null;
    }

    @Override
    public void deleteSubject(Long id) throws Exception {
        Subject subjectFound = getSubjectById(id);
        subjectRepository.delete(subjectFound);

    }
}
