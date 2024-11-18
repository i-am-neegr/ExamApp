package com.example.demo;

import com.example.demo.models.Question;
import com.example.demo.service.JavaQuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    public void testGetQuestions() {
        Assertions.assertEquals(0, javaQuestionService.getAllQuestions().size());
    }

    @Test
    public void testAddQuestion() {
        javaQuestionService.addQuestion("Q", "A");
        Assertions.assertEquals(1, javaQuestionService.getAllQuestions().size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> javaQuestionService.addQuestion("Q", "A"));
    }

    @Test
    public void testDeleteQuestion() {
        javaQuestionService.addQuestion(new Question("Q", "A"));
        javaQuestionService.removeQuestion(new Question("Q", "A"));
        Assertions.assertEquals(0, javaQuestionService.getAllQuestions().size());
    }

}
