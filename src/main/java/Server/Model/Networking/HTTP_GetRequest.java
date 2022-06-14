package Server.Model.Networking;

import Server.Model.Flights.Flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HTTP_GetRequest {

    public static String httpRequest(String baseUrl, String[] parameters) {

        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        for (String parameter : parameters) {
            urlBuilder.append(parameter);
        }
        String url = urlBuilder.toString();
        try {
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

            return response.toString();
            //Read JSON response and print
        } catch (IOException exception) {
            System.out.println("Get request to " + url + " failed");
        }
        return null;
    }
}
