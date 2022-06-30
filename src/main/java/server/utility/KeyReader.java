package server.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KeyReader {
    public static String getAPIkey() {
        String key = "";
        try {
            File myObj = new File("src/main/ui/.env");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                key = data.split("=")[1];
                System.out.println(key);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
        return key;
    }
}
