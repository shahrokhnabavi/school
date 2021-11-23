package com.mary.school.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> list() {
        return this.repository.findAll();
    }

    public void save(Student student) {
        this.validateEmail(student.getEmail());
        this.repository.save(student);
    }

    public void remove(Long id) {
        if (!this.repository.existsById(id)) {
            throw new EntityExistsException("Student with ID: " + id + " does not exist.");
        }

        this.repository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String name, String email) {
        Optional<Student> entity = this.repository.findById(id);

        if (!entity.isPresent()) {
            throw new EntityExistsException("Student with ID: " + id + " does not exist.");
        }

        Student student = entity.get();
        if (email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            this.validateEmail(email, id);
            student.setEmail(email);
        }

        if (name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
    }

    private void validateEmail(String email) {
        Optional<Student> entity = this.repository.findStudentByEmail(email);

        if (entity.isPresent()) {
            throw new EntityExistsException("Student with Email: " + email + " is already being used.");
        }
    }

    private void validateEmail(String email, Long id) {
        Optional<Student> entity = this.repository.findStudentByEmail(email);

        if (entity.isPresent() && !Objects.equals(entity.get().getId(), id)) {
            throw new EntityExistsException("Student with Email: " + email + " is already being used.");
        }
    }
}
