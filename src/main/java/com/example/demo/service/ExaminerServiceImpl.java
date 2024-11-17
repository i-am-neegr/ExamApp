package com.example.demo.service;

import com.example.demo.models.Question;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(Integer amount) throws BadRequestException {
        if (amount > questionService.getNumberOfQuestions()) {
            throw new BadRequestException();
        }
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            while (questions.size() < i + 1) {
                Question question = questionService.getRandomQuestion();
                if (!questions.contains(question)) {
                    questions.add(question);
                }
            }
        }
        return questions;
    }

}

