package com.example.demo.service;

import com.example.demo.models.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(String question, String answer);

    void removeQuestion(String question, String answer);

    Question getQuestion(String question);

    void addQuestion(Question question);

    void removeQuestion(Question question);

    List<Question> getAllQuestions();

    Question getRandomQuestion();

    int getNumberOfQuestions();

    boolean containsQuestion(Question question);
}