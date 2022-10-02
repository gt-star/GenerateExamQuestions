package com.example.generateexamquestions.Controller;

import com.example.generateexamquestions.Model.Question;
import com.example.generateexamquestions.Service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer){
        return questionService.add(question,answer);
    }
    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,
                                   @RequestParam String answer){
        return questionService.remove(new Question(question,answer));
    }
    @GetMapping()
    public Collection<Question> getAllQuestion(){
        return questionService.getAll();
    }
}
