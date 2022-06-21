package com.example.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;
    
    List<Question> getQuestions(){
        
        return questionRepo.findAll();
    }

    public Question saveQuestion(Question newQuestion) {
        return questionRepo.save(newQuestion);
    }

    public Optional<Question> getQuestion(String id) {
        return questionRepo.findById(id);
    }

    public List<Question> getQuestionsByState(Boolean state){
        return questionRepo.findByApproved(state);
    }

    public Optional<Question> deleteQuestion(String id){
        Optional<Question> questionToDel = getQuestion(id);
        
        if (questionToDel.isPresent()){
            questionRepo.delete(questionToDel.get());
            return Optional.of(questionToDel.get());
        } 
        return null;
    }


}
