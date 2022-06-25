package server.rest;

import server.service.MovieService;
import server.model.entertainment.movies.MovieCatalogue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes =  {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class MovieRest {
    private final MovieService movieService;
    public MovieRest(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movies")
    public ResponseEntity<MovieCatalogue> getCatalogue() {
        return ResponseEntity.ok(movieService.getCatalogue());
    }
}
