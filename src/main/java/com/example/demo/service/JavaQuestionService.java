package com.example.demo.service;

import com.example.demo.models.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final List<Question> questions;
    Random rand = new Random();

    public JavaQuestionService() {
        questions = new ArrayList<>();
    }


    @Override
    public void addQuestion(String question, String answer) {
        addQuestion(new Question(question, answer));
    }

    @Override
    public void addQuestion(Question question) {
        if (!questions.contains(question)) {
            questions.add(question);
            return;
        }
        throw new IllegalArgumentException("Question already exists");
    }

    @Override
    public void removeQuestion(String question, String answer) {
        removeQuestion(new Question(question, answer));
    }

    @Override
    public void removeQuestion(Question question) {
        for (int i = 0; i < questions.size(); i++) {
            if (question.equals(questions.get(i))) {
                questions.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Question not found");
    }

    @Override
    public Question getQuestion(String question) {
        for (Question value : questions) {
            if (question.equals(value.getQuestion())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Question not found");
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public Question getRandomQuestion() {
        return questions.get(rand.nextInt(questions.size()));
    }

    @Override
    public int getNumberOfQuestions() {
        return questions.size();
    }

    @Override
    public boolean containsQuestion(Question question) {
        return questions.contains(question);
    }

}
