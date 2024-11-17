package com.example.demo.controller;

import com.example.demo.models.Question;
import com.example.demo.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/exam/java")
@RestController
public class JavaQuestionController {
    private final JavaQuestionService service;

    public JavaQuestionController(JavaQuestionService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String add(@RequestParam String question, @RequestParam String answer) {
        service.addQuestion(question, answer);
        return "success";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String question, @RequestParam String answer) {
        service.removeQuestion(question, answer);
        return "success";
    }

    @GetMapping()
    public List<Question> getQuestions() {
        return service.getAllQuestions();
    }


}
