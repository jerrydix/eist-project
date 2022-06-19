package server.service;

import server.model.entertainment.catering.Catering;
import server.model.entertainment.catering.CateringFactory;
import org.springframework.stereotype.Service;

@Service
public class CateringService {

    private Catering catering;

    public CateringService() {
        catering = CateringFactory.createCatering(50, 50, 50);
    }

    public Catering getCatering() {
        return catering;
    }

    public Catering saveCatering(Catering catering) {
        //TODO: used in updating caterings
        return null;
    }
}
