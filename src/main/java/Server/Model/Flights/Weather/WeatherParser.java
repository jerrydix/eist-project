package Server.Model.Flights.Weather;

import Server.Model.Networking.HTTP_GetRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {

    public static Weather parseWeatherJson(String jsonText) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonText.toString());
        JSONArray weatherArray = jsonObject.getJSONArray("weather");

        String weatherType = weatherArray.getJSONObject(0).getString("main");
        double tempInKelvin = jsonObject.getJSONObject("main").getDouble("temp");

        double temp = Math.round((WeatherParser.kelvinToCelsius(tempInKelvin)) * 100.0) / 100.0;
        double longitude = jsonObject.getJSONObject("coord").getDouble("lon");
        double latidude = jsonObject.getJSONObject("coord").getDouble("lat");

        return new Weather(weatherType, temp, latidude, longitude);
    }

    private static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        try {
            System.out.println(WeatherParser.parseWeatherJson(HTTP_GetRequest.httpRequest("http://api.openweathermap.org/data/2.5/weather", new String[]{"?q=London", "&appid=9ea515dfae2ad349f12da21f050ede90"})));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
