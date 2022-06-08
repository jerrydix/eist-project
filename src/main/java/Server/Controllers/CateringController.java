package Server.Controllers;

import Server.Model.Entertainment.Catering.Catering;
import Server.Model.Entertainment.Catering.CateringFactory;

public class CateringController {

    private Catering catering;

    public CateringController() {
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
