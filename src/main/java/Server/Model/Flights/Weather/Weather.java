package Server.Model.Flights.Weather;

public class Weather {

    private String weatherType;
    private double degrees;
    private double latitude;
    private double longitude;

    public Weather(String weatherType, double degrees, double latitude, double longitude) {
        this.weatherType = weatherType;
        this.degrees = degrees;
        this.latitude = latitude;
        this.longitude = longitude;
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
        return "Weather type: " + weatherType + "\nTemperature: " + degrees + "\nLatitude: " + latitude + "\nLongitude: " + longitude;
    }
}
