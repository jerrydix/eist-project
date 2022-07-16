package server.model.flights;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightJourney {

    private List<Flight> flights;
    private List<Location> locations;
    private Location origin;
    private String originName;
    private String startDate;

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

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    /**
     * Used to add a flight (+its locations) to the current flight journey.
     *
     * @param flight The flight that is to be added to the flight journey
     * @return true, if the flight was successfully added and false if it hasn't been added
     */
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
            this.originName = origin.getName();
            this.startDate = flight.getDepartureDate();
            locations.add(flight.getEndLocation());
        }
        return true;
    }

    /**
     * Creates a flight journey out of a list of flights.
     *
     * @param flights A list of flights
     * @return true, if the journey was built successfully, false if it hasn't
     */

    public boolean buildJourney(List<Flight> flights) {
        for (Flight flight : flights) {
            if (!pickFlight(flight)) {
                flights.clear();
                return false;
            }
        }
        return true;
    }

    /**
     * Method to automatically replace a cancelled flight in a flight journey with a new subsidiary flight.
     *
     * @param number The flight number of the flight that is to be replaced
     * @return The subsidiary flight that has been added to the journey
     */
    public Flight replaceCancelledFlight(String number) {
        for (int i = 0; i < this.flights.size(); i++) {
            if (flights.get(i).isCancelled() && flights.get(i).getNumber().equals(number)) {
                Flight cancelledFlight = flights.get(i);
                String cancelledflightTime = cancelledFlight.getStartTime().toString();


               // LocalDateTime.of()

                Flight newFlight = FlightFactory.generateFlight(cancelledFlight.getStartLocation().getName(), cancelledFlight.getEndLocation().getName(), "12/12/1001");
                newFlight.setStartTime(cancelledFlight.getStartTime());
                newFlight.setEndTime(cancelledFlight.getEndTime());

                flights.set(i, newFlight);
                return newFlight;
            }
        }
        return null;
    }

    public Flight removeLastFlight() {
        this.locations.remove(locations.size() - 1);
        return this.flights.remove(flights.size() - 1);
    }

    /**
     * Cancels a random flight in the flight journey
     *
     * @return The cancelled flight
     */
    public Flight cancelRandomFlight() {
        Random r = new Random();
        int index = r.nextInt(this.flights.size());
        this.flights.get(index).setCancelled(true);
        return this.flights.get(index);
    }

    //todo delete?
    public List<Flight> fetchReturningFlights(String date) {
        return Flight.fetchFlightsFromToAt(this.getFlights().get(this.getFlights().size() - 1).getEndLocation().getName(), origin.getName(), date);
    }
}
