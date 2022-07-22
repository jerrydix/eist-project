package server.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP_GetRequest {

    /**
     * Send a GET request to the passed URL. Each parameter in the parameters array
     * gets appended as is to the host. Note that no trailing slash gets added to the host
     * before appending the parameters!
     * Return the server's response as a {@link String}.
     *
     * @param baseUrl    The host subcomponent of the complete URL (i.e. http://omdb.com)
     * @param parameters A path, quuery and/or fragment component following the host
     * @return The server's response as a {@link String}
     */
    public static String httpRequest(String baseUrl, String[] parameters) {

        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        for (String parameter : parameters) {
            urlBuilder.append(parameter);
        }
        String url = urlBuilder.toString();
        try {
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
            //System.out.println(response.toString());

            return response.toString();
        } catch (IOException exception) {
            System.out.println("Get request to " + url + " failed");
        }
        return null;
    }
}
