package com.example.Quiz;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    private String id;
    private String question;
    private String answer;
    private Boolean approved;

    Question(String question, String answer){
        this.answer = answer;
        this.question = question;
    }
}
