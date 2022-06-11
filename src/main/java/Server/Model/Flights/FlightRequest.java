package Server.Model.Flights;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Server.Model.Flights.Weather.Weather;
import Server.Model.Flights.Weather.WeatherType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//TODO DO NOT EXECUTE MAIN METHOD, WE GOTTA SAFE THEM API REQUESTS
public class FlightRequest {

    public static List<Flight> httpFlightRequest(String baseUrl, String[] parameters) throws Exception {

        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        for (String parameter : parameters) {
            urlBuilder.append(parameter);
        }
        String url = urlBuilder.toString();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //request type
        con.setRequestMethod("GET");

        //header request
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String
        System.out.println(response.toString());

        return parseFlightJson(response.toString());
        //Read JSON response and print
    }

    public static List<Flight> parseFlightJson(String jsonText) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonText.toString());
        JSONArray dataArray = jsonObject.getJSONArray("data");
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < dataArray.length(); i++) {

            String number = dataArray.getJSONObject(i).getJSONObject("flight").getString("iata");
            String startDate = dataArray.getJSONObject(i).getString("flight_date");
            int startYear = Integer.parseInt(startDate.substring(0,4));
            int startMonth = Integer.parseInt(startDate.substring(5,7));
            int startDay = Integer.parseInt(startDate.substring(8));
            int startHour = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").substring(12,14));
            int startMinute = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").substring(15,17));
            Month startM = Month.JANUARY;
            switch (startMonth) {
                case 1 -> startM = Month.JANUARY;
                case 2 -> startM = Month.FEBRUARY;
                case 3 -> startM = Month.MARCH;
                case 4 -> startM = Month.APRIL;
                case 5 -> startM = Month.MAY;
                case 6 -> startM = Month.JUNE;
                case 7 -> startM = Month.JULY;
                case 8 -> startM = Month.AUGUST;
                case 9 -> startM = Month.SEPTEMBER;
                case 10 -> startM = Month.OCTOBER;
                case 11 -> startM = Month.NOVEMBER;
                case 12 -> startM = Month.DECEMBER;
                default -> System.out.println("No such month");
            }
            LocalDateTime startTime = LocalDateTime.of(startYear, startM, startDay, startHour, startMinute);

            String endDate = dataArray.getJSONObject(i).getJSONObject("arrival").getString("scheduled");
            int endYear = Integer.parseInt(endDate.substring(0,4));
            int endMonth = Integer.parseInt(endDate.substring(5,7));
            int endDay = Integer.parseInt(endDate.substring(8,11));
            int endHour = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("arrival").getString("scheduled").substring(12,14));
            int endMinute = Integer.parseInt(dataArray.getJSONObject(i).getJSONObject("arrival").getString("scheduled").substring(15,17));
            Month endM = Month.DECEMBER;
            switch (endMonth) {
                case 1 -> endM = Month.JANUARY;
                case 2 -> endM = Month.FEBRUARY;
                case 3 -> endM = Month.MARCH;
                case 4 -> endM = Month.APRIL;
                case 5 -> endM = Month.MAY;
                case 6 -> endM = Month.JUNE;
                case 7 -> endM = Month.JULY;
                case 8 -> endM = Month.AUGUST;
                case 9 -> endM = Month.SEPTEMBER;
                case 10 -> endM = Month.OCTOBER;
                case 11 -> endM = Month.NOVEMBER;
                case 12 -> endM = Month.DECEMBER;
                default -> System.out.println("No such month");
            }
            LocalDateTime endTime = LocalDateTime.of(endYear, endM, endDay, endHour, endMinute);

            String gate = dataArray.getJSONObject(i).getJSONObject("departure").getString("gate");
            String terminal = dataArray.getJSONObject(i).getJSONObject("departure").getString("terminal");
            int seat = ThreadLocalRandom.current().nextInt(1, 288);
            String airline = dataArray.getJSONObject(i).getJSONObject("airline").getString("name");
            boolean cancelled = true;
            boolean delayed;
            if (dataArray.getJSONObject(i).getString("flight_status").equals("scheduled")) {
                cancelled = false;
            }
            if (!dataArray.getJSONObject(i).getJSONObject("departure").getString("scheduled").equals(dataArray.getJSONObject(i).getJSONObject("departure").getString("estimated"))) {
                delayed = true;
            }
            //todo delayTime
            int delayTime = 0;

            //todo weather, poilist, latitude, longitude
            Location startLocation = new Location(dataArray.getJSONObject(i).getJSONObject("departure").getString("timezone").substring(dataArray.getJSONObject(i).getJSONObject("departure").getString("timezone").indexOf("/") + 1), new Weather(WeatherType.CLOUDY, 12),1,1, new ArrayList<>());

            Location endLocation = new Location(dataArray.getJSONObject(i).getJSONObject("arrival").getString("timezone").substring(dataArray.getJSONObject(i).getJSONObject("arrival").getString("timezone").indexOf("/") + 1), new Weather(WeatherType.CLOUDY, 12),1,1, new ArrayList<>());

            flights.add(new Flight(number, startTime, endTime, gate, terminal, seat, airline, startLocation, endLocation));
        }
        return flights;
    }

    public String toString(List<Flight> flights) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < flights.size(); i++) {
            builder.append(flights.get(i).toString());
            builder.append(", ");
        }
        return builder.toString();
    }

    public static void main(String[] args) throws JSONException {
        List<Flight> flightList = parseFlightJson("{\"pagination\":{\"limit\":100,\"offset\":0,\"count\":100,\"total\":311702},\"data\":[{\"flight_date\":\"2022-06-12\",\"flight_status\":\"scheduled\",\"departure\":{\"airport\":\"Tontouta\",\"timezone\":\"Pacific\\/Noumea\",\"iata\":\"NOU\",\"icao\":\"NWWW\",\"terminal\":null,\"gate\":null,\"delay\":null,\"scheduled\":\"2022-06-12T00:05:00+00:00\",\"estimated\":\"2022-06-12T00:05:00+00:00\",\"actual\":null,\"estimated_runway\":null,\"actual_runway\":null},\"arrival\":{\"airport\":\"NaritaInternationalAirport\",\"timezone\":\"Asia\\/Tokyo\",\"iata\":\"NRT\",\"icao\":\"RJAA\",\"terminal\":\"1N\",\"gate\":null,\"baggage\":null,\"delay\":null,\"scheduled\":\"2022-06-12T07:00:00+00:00\",\"estimated\":\"2022-06-12T07:00:00+00:00\",\"actual\":null,\"estimated_runway\":null,\"actual_runway\":null},\"airline\":{\"name\":\"JetLinxAviation\",\"iata\":\"JL\",\"icao\":\"JTL\"},\"flight\":{\"number\":\"5370\",\"iata\":\"JL5370\",\"icao\":\"JTL5370\",\"codeshared\":{\"airline_name\":\"aircalin\",\"airline_iata\":\"sb\",\"airline_icao\":\"aci\",\"flight_number\":\"800\",\"flight_iata\":\"sb800\",\"flight_icao\":\"aci800\"}},\"aircraft\":null,\"live\":null}]}");
        System.out.println(flightList.toString());
        /*try {
            FlightRequest.httpFlightRequest("http://api.aviationstack.com/v1/flights", new String[]{"?access_key=8df0ff3c6cd266e3219eed88b44cc2ee"});
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
