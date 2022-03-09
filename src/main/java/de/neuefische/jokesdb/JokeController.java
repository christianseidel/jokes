package de.neuefische.jokesdb;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jokes")
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @PostMapping
    public Joke createNewJoke(@RequestBody Joke joke) {
        return jokeService.createNewJoke(joke);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joke> findJokeById(@PathVariable String id) {
        return ResponseEntity.of(jokeService.findById(id));
    }

    @GetMapping
    public List<Joke> findJokesByRating(@RequestParam String rating) {
        return jokeService.findByRating(rating);
    }

}
