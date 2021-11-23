package com.mary.school.student;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    final private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> list() {
        return this.studentService.list();
    }

    @PostMapping
    public void save(@RequestBody Student student) {
        this.studentService.save(student);
    }

    @DeleteMapping(path = "{id}")
    public void remvove(@PathVariable Long id) {
        this.studentService.remove(id);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable Long id, @RequestBody JsonNode payload) {
        String name = payload.has("name") ? payload.get("name").asText() : "";
        String email = payload.has("email") ? payload.get("email").asText() : "";

        this.studentService.update(id, name, email);
    }
}
