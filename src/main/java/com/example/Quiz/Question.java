package com.example.Quiz;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "questions")
@Data
public class Question {

    @Id
    private String id;
    private String question;
    private String answer;
    private Boolean approved;
}
