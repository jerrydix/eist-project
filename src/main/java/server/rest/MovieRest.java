package server.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.model.entertainment.MovieCatalogue;
import server.service.MovieService;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class MovieRest {
    private final MovieService movieService;

    public MovieRest(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("api/movies")
    public ResponseEntity<MovieCatalogue> getCatalogue() {
        return ResponseEntity.ok(movieService.getCatalogue());
    }
}
