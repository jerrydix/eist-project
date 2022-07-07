package server.model.flights.weather;

import server.model.networking.HTTP_GetRequest;
import server.model.parsing.WeatherParser;

public class Weather {

    private String weatherType;
    private double degrees;

    public Weather(String weatherType, double degrees) {
        this.weatherType = weatherType;
        this.degrees = degrees;
    }

    /**
     * A wrapper method to retrieve the current weather data at specific coordinates (Locations).
     *
     * @param latitude The latitude of the Location of which the weather is fetched
     * @param longitude The longitude of the Location of which the weather is fetched
     * @return A weather object containing data about the current weather at the specified coordinates
     */
    public static Weather fetchWeather(double latitude, double longitude) {
        if (latitude == -1 || longitude == -1) {
            return new Weather("cloudy", 20);
        }
        return WeatherParser.parseWeatherJson(HTTP_GetRequest.httpRequest("http://api.openweathermap.org/data/2.5/weather", new String[]{"?lat=" + latitude + "&lon=" + longitude, "&appid=9ea515dfae2ad349f12da21f050ede90"}));
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public String toString() {
        return "Weather type: " + weatherType + "\nTemperature: " + degrees;
    }
}
