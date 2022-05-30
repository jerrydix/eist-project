package Flights.Weather;

public class Weather {

    private WeatherType weatherType;
    private int degrees;

    public Weather(WeatherType weatherType, int degrees) {
        this.weatherType = weatherType;
        this.degrees = degrees;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
