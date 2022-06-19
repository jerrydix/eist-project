package server.model.flights;

import java.util.ArrayList;
import java.util.List;

public class FlightTrip {

    private List<Flight> flights;
    private List<Location> locations;

    public FlightTrip() {
        this.flights = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Location> getDestinations() {
        return locations;
    }

    public void setDestinations(List<Location> locations) {
        this.locations = locations;
    }

    public boolean addDestination(Location location) {
        return locations.add(location);
    }

    public boolean removeDestination(Location location) {
        return locations.remove(location);
    }

    public int findToDestinationIndex(Flight flight) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).equals(flight.getEndLocation())) {
                return i;
            }
        }
        return -1;
    }

    public int findFromDestinationIndex(Flight flight) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).equals(flight.getStartLocation())) {
                return i;
            }
        }
        return -1;
    }

    public boolean addFlight(Flight flight) {
        return this.flights.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        return flights.remove(flight);
    }

    public List<Flight> list10AvailableFlights(Location from, Location to) {
        List<Flight> availableFlights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //todo get request find available flights
            Flight flight = new Flight("1", null, null, "1", null, 1, null, from, to);
            availableFlights.add(flight);
        }
        return flights;
    }

    public boolean pickFlight(Flight flight) {
        if (flight == null) {
            return false;
        }
        flights.add(flight);
        if (flights.size() >= 2 && flights.get(flights.size() - 2).getEndLocation().equals(flights.get(flights.size() - 1).getStartLocation())) {
            locations.add(flight.getEndLocation());
        } else {
            locations.add(flight.getStartLocation());
            locations.add(flight.getEndLocation());
        }
        return true;
    }
}
