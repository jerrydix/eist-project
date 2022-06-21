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

    public static Weather fetchWeather(double latitude, double longitude) {
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
