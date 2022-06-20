package com.example.Quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;
    
    List<Question> getQuestions(){
        return questionRepo.findAll();
    }


}
