package com.example.demo.service;

import com.example.demo.models.Question;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(Integer amount) throws BadRequestException;
}
