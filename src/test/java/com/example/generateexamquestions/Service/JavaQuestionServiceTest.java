package com.example.generateexamquestions.Service;

import com.example.generateexamquestions.Exception.QuestionAlreadyExistsException;
import com.example.generateexamquestions.Model.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();
    @AfterEach
    public void afterEach(){
        questionService.getAll().forEach(questionService::remove);
    }
    @Test
    public void addPositiveTest(){
        assertThat(questionService.getAll().isEmpty());
        Question expected = add(new Question("q1","a1"));


        questionService.add("q2","a2");
        assertThat(questionService.getAll()).hasSize(2);
        assertThat(questionService.getAll()).contains(expected,new Question("q2","a2"));

    }
    @Test
    public void addNegativeTest(){
        Question expected =add(new Question("q1","a1"));

        questionService.add("q2","a2");

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(()->questionService.add(expected));

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(()->questionService.add("q2","a2"));
    }

    private Question add(Question question){
        int sizeBefore = questionService.getAll().size();

        questionService.add(question);

        assertThat(questionService.getAll()).hasSize(sizeBefore + 1);
        assertThat(questionService.getAll()).contains(question);

        return question;
    }

    @Test
    public void removeNegativeTest(){
        assertThat(questionService.getAll()).isEmpty();

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(()->questionService.remove(new Question("q","a")));

        Question expected = add(new Question("q1","a1"));

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(()->questionService.remove(new Question("q","a")));
    }
    @Test
    public void removePositiveTest(){
        Question expected = add(new Question("q1","a1"));
        questionService.remove(expected);

        assertThat(questionService.getAll()).isEmpty();
    }
    @Test
    public void getRandomQuestionTest(){
        for (int i = 1; i < 5; i++) {
            add(new Question("q" + i, "a" + i));
        }
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }
}