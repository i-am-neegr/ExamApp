package com.example.demo.controller;

import com.example.demo.models.Question;
import com.example.demo.service.ExaminerService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/exam/get")
@RestController
public class ExamController {
    private final ExaminerService examinerService;

    @Autowired
    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("{amount}")
    public List<Question> getExam(@PathVariable Integer amount) throws BadRequestException {
        return examinerService.getQuestions(amount);
    }
}
