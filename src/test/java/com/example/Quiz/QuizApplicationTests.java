package com.example.Quiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.assertj.core.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizApplicationTests {

	Question question = new Question("Wie heißt das größte Amphietheater der Welt?", "Kolloseum");

	@Autowired
	public TestRestTemplate restTemplate;

	@Test
	void integrationTest(){
		ResponseEntity<Question[]> emptyRes = restTemplate.getForEntity("/", Question[].class);
		Assertions.assertThat(emptyRes.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(emptyRes.getBody()).isEmpty();

		ResponseEntity<Question> postRes = restTemplate.postForEntity("/", question , Question.class);
		Assertions.assertThat(postRes.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(postRes.getBody().getQuestion()).isEqualTo(question.getQuestion());

		Question createdQuestion = postRes.getBody();

		Question editedQuestion = new Question(createdQuestion.getId(), createdQuestion.getQuestion(),"Das Kolloseum", true);
		restTemplate.put ("/", editedQuestion);
		ResponseEntity<Question[]> editedRes = restTemplate.getForEntity("/", Question[].class);
		Assertions.assertThat(editedRes.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(editedRes.getBody()).contains(editedQuestion);

		ResponseEntity<Question> notApprovedQuestion = restTemplate.postForEntity("/", new Question("Warum Java", "Darum") , Question.class);
		ResponseEntity<Question[]> allQuestions = restTemplate.getForEntity("/?showAll=1", Question[].class);
		ResponseEntity<Question[]> approvedQuestions = restTemplate.getForEntity("/?showAll=0", Question[].class);
		Assertions.assertThat(approvedQuestions.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(allQuestions.getBody().length).isEqualTo(2);
		Assertions.assertThat(approvedQuestions.getBody().length).isEqualTo(1);
		Assertions.assertThat(approvedQuestions.getBody()).contains(editedQuestion);
		restTemplate.delete("/" + createdQuestion.getId());
		ResponseEntity<Question[]> questionsAfterDel = restTemplate.getForEntity("/?showAll=1", Question[].class);
		Assertions.assertThat(questionsAfterDel.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(questionsAfterDel.getBody().length).isEqualTo(1);
		
	}
}
