package Server.Controllers;

import java.util.ArrayList;
import java.util.List;

import Server.Model.Flights.Destination;

public class DestinationController {

    private List<Destination> destinations;

    public DestinationController(){
        this.destinations = new ArrayList<Destination>();
    }

    private Destination getDestinationWithID(int destinationID){
        for(Destination destination : destinations){
            if(destination.getID() == destinationID){
                return destination;
            }
        }
        return null;
    }

    public Destination saveDestination(Destination destination) {
        //TODO: //used in updating an existing and adding new surveys
        return null;
    }

    public Destination getDestination(Integer destinationID) {
        return getDestinationWithID(destinationID);
    }

    public void removeDestination(Integer destinationID) {
        this.destinations.removeIf(destination -> destination.getID() == destinationID);
    }
    
}
