package com.example.demo;

import com.example.demo.models.Question;
import com.example.demo.service.ExaminerServiceImpl;
import com.example.demo.service.QuestionService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ExaminerServiceImplTest {

    private QuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setUp() {
        questionService = Mockito.mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    public void testGetQuestions_Success() throws BadRequestException {
        // Подготовка данных
        int amount = 3;
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question("Question 1", "1"));
        mockQuestions.add(new Question("Question 2", "1"));
        mockQuestions.add(new Question("Question 3", "1"));

        when(questionService.getNumberOfQuestions()).thenReturn(mockQuestions.size());
        when(questionService.getRandomQuestion())
                .thenReturn(mockQuestions.get(0))
                .thenReturn(mockQuestions.get(1))
                .thenReturn(mockQuestions.get(2));

        // Вызов метода
        List<Question> questions = examinerService.getQuestions(amount);

        // Проверка результата
        assertEquals(amount, questions.size());
        assertTrue(questions.contains(mockQuestions.get(0)));
        assertTrue(questions.contains(mockQuestions.get(1)));
        assertTrue(questions.contains(mockQuestions.get(2)));
    }

    @Test
    public void testGetQuestions_ThrowsBadRequestException_WhenAmountIsGreaterThanAvailable() {
        // Подготовка данных
        int amount = 10;

        when(questionService.getNumberOfQuestions()).thenReturn(5);

        // Проверка исключения
        assertThrows(BadRequestException.class, () -> examinerService.getQuestions(amount));
    }

    @Test
    public void testGetQuestions_HandlesDuplicateQuestions() throws BadRequestException {
        // Подготовка данных
        int amount = 2; // Установим количество запрашиваемых вопросов на 2
        Set<Question> mockQuestions = new HashSet<>(); // Используем Set для хранения уникальных вопросов
        mockQuestions.add(new Question("Question 1", "1"));
        mockQuestions.add(new Question("Question 1", "1")); // Дубликат
        mockQuestions.add(new Question("Question 2", "1"));

        when(questionService.getNumberOfQuestions()).thenReturn(mockQuestions.size());

        // Настраиваем поведение метода getRandomQuestion()
        when(questionService.getRandomQuestion())
                .thenReturn(new ArrayList<>(mockQuestions).get(0))
                .thenReturn(new ArrayList<>(mockQuestions).get(1));

        // Вызов метода
        List<Question> questions = examinerService.getQuestions(amount);

        // Проверка результата
        assertEquals(2, questions.size()); // Должен вернуть только уникальные вопросы
        assertTrue(questions.contains(new Question("Question 1", "1")));
        assertTrue(questions.contains(new Question("Question 2", "1")));
    }

}
