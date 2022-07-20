package server.model.flights;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightJourney {

    private List<Flight> flights;
    private Location origin;
    private Location endLocation;
    private String originName;
    private String endName;
    private String startDate;

    public FlightJourney() {
        this.flights = new ArrayList<>();
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

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }


    /**
     * Used to add a flight to the current flight journey.
     *
     * @param flight The flight that is to be added to the flight journey
     * @return true, if the flight was successfully added and false if it hasn't been added
     */
    public boolean pickFlight(Flight flight) {
        if (flight == null) {
            return false;
        }
        //if (flights.size() > 0 && !flight.getStartLocation().equals(flights.get(flights.size() - 1).getEndLocation())) {
        //    return false;
        //}
        if (flights.isEmpty()) {
            this.origin = flight.getStartLocation();
            this.originName = origin.getName();
            this.startDate = flight.getDepartureDate();
        }
        flights.add(flight);
        this.endLocation = flight.getEndLocation();
        this.endName = flight.getEndName();
        return true;
    }

    /**
     * Creates a flight journey out of a list of flights.
     *
     * @param flights A list of flights
     * @return true, if the journey was built successfully, false if it hasn't
     */

    public boolean buildJourney(List<Flight> flights) {
        if (flights == null || flights.size() == 0) {
            return false;
        }
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

                Flight newFlight = FlightFactory.generateFlight(cancelledFlight.getStartLocation().getName(), cancelledFlight.getEndLocation().getName(), "1001-12-12");
                newFlight.setStartTime(cancelledFlight.getStartTime());
                newFlight.setEndTime(cancelledFlight.getEndTime());

                flights.set(i, newFlight);
                return newFlight;
            }
        }
        return null;
    }

    public Flight removeLastFlight() {
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
}
