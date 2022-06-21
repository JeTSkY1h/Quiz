package com.example.Quiz;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuestionService questionService;

    @GetMapping("")
    ResponseEntity<List<Question>> getQuestions(@RequestParam(required = false, defaultValue = "0") Boolean showAll){
        return showAll ?
            ResponseEntity.of(Optional.of(questionService.getQuestions())) 
        : 
            ResponseEntity.of(Optional.of(questionService.getQuestionsByState(true)));
    }

    @PostMapping("")
    ResponseEntity<Question> createQuestion(@RequestBody Question question){
       return  ResponseEntity.of(Optional.of(questionService.saveQuestion(question)));
    }

    @GetMapping("/{id}")
    ResponseEntity<Question> getQuestion(@PathVariable String id){
        System.out.println(id);
        return ResponseEntity.of(questionService.getQuestion(id));
    }

    @PutMapping("") 
    ResponseEntity<Question> saveQuestion(@RequestBody Question updatedQuestion){
        return ResponseEntity.of(Optional.of(questionService.saveQuestion(updatedQuestion)));
    }

    @GetMapping("/approved")
    ResponseEntity<List<Question>> getApprovedQuestions(){
        return ResponseEntity.of(Optional.of(questionService.getQuestionsByState(true)));
    }
}