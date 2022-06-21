package server.model.parsing;

import server.model.flights.weather.Weather;
import server.model.networking.HTTP_GetRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {

    public static Weather parseWeatherJson(String jsonText) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText.toString());
            JSONArray weatherArray = jsonObject.getJSONArray("weather");

            String weatherType = weatherArray.getJSONObject(0).getString("main");
            double tempInKelvin = jsonObject.getJSONObject("main").getDouble("temp");

            double temp = Math.round((WeatherParser.kelvinToCelsius(tempInKelvin)) * 100.0) / 100.0;
            //double longitude = jsonObject.getJSONObject("coord").getDouble("lon");
            //double latidude = jsonObject.getJSONObject("coord").getDouble("lat");

            return new Weather(weatherType, temp);
        } catch (JSONException exception) {
            System.out.println("Weather parsing failed");
        }
        return null;
    }

    private static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        try {
            //@48.1683383,11.5300085 munich
            System.out.println(WeatherParser.parseWeatherJson(HTTP_GetRequest.httpRequest("http://api.openweathermap.org/data/2.5/weather", new String[]{"?lat=48.264211&lon=11.667018", "&appid=9ea515dfae2ad349f12da21f050ede90"})));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
