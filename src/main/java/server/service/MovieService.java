package server.service;

import server.model.entertainment.movies.Catalogue;
import server.model.entertainment.movies.CatalogueFactory;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private Catalogue catalogue;
    public MovieService() {
        catalogue = CatalogueFactory.createMovieCatalogue();
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }
}
