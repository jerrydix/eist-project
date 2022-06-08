package REST;

import Server.Controllers.MovieController;
import Server.Model.Entertainment.Movies.Catalogue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes =  {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class MovieREST {
    private final MovieController movieController;
    public MovieREST(MovieController movieController) {
        this.movieController = movieController;
    }

    @GetMapping("movies")
    public ResponseEntity<Catalogue> getCatalogue() {
        return ResponseEntity.ok(movieController.getCatalogue());
    }
}
