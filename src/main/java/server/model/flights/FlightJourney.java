package server.model.flights;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightJourney {

    private List<Flight> flights;
    private List<Location> locations;
    private Location origin;

    public FlightJourney() {
        this.flights = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
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

    public boolean pickFlight(Flight flight) {
        if (flight == null) {
            return false;
        }
        flights.add(flight);
        if (flights.size() >= 2 && flights.get(flights.size() - 2).getEndLocation().equals(flights.get(flights.size() - 1).getStartLocation())) {
            locations.add(flight.getEndLocation());
        } else {
            locations.add(flight.getStartLocation());
            this.origin = flight.getStartLocation();
            locations.add(flight.getEndLocation());
        }
        return true;
    }

    public Flight removeLastFlight() {
        this.locations.remove(locations.size() - 1);
        return this.flights.remove(flights.size() - 1);
    }

    public Flight cancelRandomFlight() {
        Random r = new Random();
        int index = r.nextInt(this.flights.size());
        this.flights.get(index).setCancelled(true);
        return this.flights.remove(index);
    }

    public List<Flight> fetchReturningFlights(String date) {
        return Flight.fetchFlightsFromToAt(this.getFlights().get(this.getFlights().size() - 1).getEndLocation().getName(), origin.getName(), date);
    }
}
