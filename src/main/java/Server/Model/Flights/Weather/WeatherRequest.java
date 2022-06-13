package Server.Model.Flights.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequest {

    public static Weather httpWeatherRequest(String baseUrl, String[] parameters) throws Exception {

        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        for (String parameter : parameters) {
            urlBuilder.append(parameter);
        }
        String url = urlBuilder.toString();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

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

        System.out.println(response.toString());

        return parseWeatherJson(response.toString());
    }

    public static Weather parseWeatherJson(String jsonText) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonText.toString());
        JSONArray weatherArray = jsonObject.getJSONArray("weather");

        String weatherType = weatherArray.getJSONObject(0).getString("main");
        double tempInKelvin = jsonObject.getJSONObject("main").getDouble("temp");
        double temp = WeatherRequest.kelvinToCelsius(tempInKelvin);
        double longitude = jsonObject.getJSONObject("coord").getDouble("lon");
        double latidude = jsonObject.getJSONObject("coord").getDouble("lat");

        return new Weather(weatherType, temp, latidude, longitude);
    }

    private static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        try {
            System.out.println(WeatherRequest.httpWeatherRequest("http://api.openweathermap.org/data/2.5/weather", new String[]{"?q=London", "&appid=9ea515dfae2ad349f12da21f050ede90"}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
