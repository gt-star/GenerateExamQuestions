package com.example.generateexamquestions.Service;

import com.example.generateexamquestions.Model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
