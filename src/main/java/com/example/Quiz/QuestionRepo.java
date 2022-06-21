package com.example.Quiz;

import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface QuestionRepo extends MongoRepository<Question, String> {

    List<Question> findByApproved(Boolean approved);
    

}

