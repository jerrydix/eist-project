package server.service;

import org.springframework.stereotype.Service;
import server.model.flights.Flight;

import java.util.List;

@Service
public class FlightService {
    private List<Flight> flights;

    public Flight getFlight(String number) {
        for (Flight flight : flights) {
            if (flight.getNumber().equals(number)) {
                return flight;
            }
        }
        //TODO: look up in web to find this flight and add to the service, if still dont exist, die or smth
        return null;
    }
}
