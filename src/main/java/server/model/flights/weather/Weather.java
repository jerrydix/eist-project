package server.model.flights.weather;

import server.networking.HTTP_GetRequest;
import server.parsing.WeatherParser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long weatherId;
    private String weatherType;
    private double degrees;

    private String description;

    public Weather(String weatherType, double degrees) {
        this.weatherType = weatherType;
        this.degrees = degrees;
        this.description = this.toString();
    }

    protected Weather() {

    }

    /**
     * A wrapper method to retrieve the current weather data at specific coordinates (Locations).
     *
     * @param latitude  The latitude of the Location of which the weather is fetched
     * @param longitude The longitude of the Location of which the weather is fetched
     * @return A weather object containing data about the current weather at the specified coordinates
     */
    public static Weather fetchWeather(double latitude, double longitude) {
        if (latitude == -1 || longitude == -1) {
            return new Weather("cloudy", 20);
        }
        return WeatherParser.parseWeatherJson(HTTP_GetRequest.httpRequest("http://api.openweathermap.org/data/2.5/weather", new String[]{"?lat=" + latitude + "&lon=" + longitude, "&appid=2f571a32ccf43a9c3942dd6cf6540392"}));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return degrees + "°C | " + weatherType;
    }

    public long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }
}
