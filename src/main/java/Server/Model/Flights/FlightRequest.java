package Server.Model.Flights;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class FlightRequest {
    public static void main(String[] args) {
        try {
            FlightRequest.call_me("http://api.aviationstack.com/v1/flights",new String[]{"?access_key=8df0ff3c6cd266e3219eed88b44cc2ee"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call_me(String baseUrl, String[] parameters) throws Exception {

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
        //Read JSON response and print
        /*JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        System.out.println("statusCode- "+myResponse.getString("statusCode"));
        System.out.println("statusMessage- "+myResponse.getString("statusMessage"));
        System.out.println("ipAddress- "+myResponse.getString("ipAddress"));
        System.out.println("countryCode- "+myResponse.getString("countryCode"));
        System.out.println("countryName- "+myResponse.getString("countryName"));
        System.out.println("regionName- "+myResponse.getString("regionName"));
        System.out.println("cityName- "+myResponse.getString("cityName"));
        System.out.println("zipCode- "+myResponse.getString("zipCode"));
        System.out.println("latitude- "+myResponse.getString("latitude"));
        System.out.println("longitude- "+myResponse.getString("longitude"));
        System.out.println("timeZone- "+myResponse.getString("timeZone"));*/
    }
}
