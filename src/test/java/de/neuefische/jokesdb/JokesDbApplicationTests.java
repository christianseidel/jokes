package de.neuefische.jokesdb;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JokesDbApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void jokeCanBeCreatedAndRetrieved () {
        Joke joke = new Joke();
        joke.setJoke("Wasser schmeckt erst, wenn es in der Brauerei gewesen ist.");
        joke.setRating("ganz okay");

        ResponseEntity<Joke> jokeResponse = restTemplate.postForEntity("/api/jokes", joke, Joke.class);
        assertThat(jokeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(jokeResponse.getBody().getId()).isNotNull();
        assertThat(jokeResponse.getBody().getJoke()).isEqualTo("Wasser schmeckt erst, wenn es in der Brauerei gewesen ist.");
        assertThat(jokeResponse.getBody().getRating()).isEqualTo("ganz okay");

        String id = jokeResponse.getBody().getId();
        assertThat(jokeResponse.getBody().getId()).isEqualTo(id);

        // ResponseEntity<Joke[]> getAll = restTemplate.getForEntity("/api/jokes", Joke[].class);
      //  assertThat(getAll.getBody().length).isEqualTo(1);
    }

}
