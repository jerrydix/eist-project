package server.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KeyReader {

    /**
     * getAPIkey() reads the Google API key from the .env file in src/main/ui/
     * 
     * @return The first key in the .env file         
     */
    public static String getAPIkey() {
        String key = "";
        try {
            File file = new File("src/main/ui/.env");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                key = data.split("=")[1];
                System.out.println(key);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return key;
    }
}
