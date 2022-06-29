package server.service;

import org.springframework.stereotype.Service;
import server.model.flights.Flight;

import java.util.List;

@Service
public class FlightService {

    public String[] getSuggestions(String city) {
        return Flight.getSuggestions(city);
    }

    public List<Flight> getFlights(String from, String to, String date) {
        return Flight.fetchFlightsFromToAt(from, to, date);
    }
}
