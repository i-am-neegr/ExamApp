package com.example.demo.service;

import com.example.demo.models.Question;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            Question question = questionService.getRandomQuestion();
            questions.add(question);
        }
        return questions.stream().toList();
    }

}

