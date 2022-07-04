
package whynotkafka.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Matthew on 03/07/2022.
 */
public class GetFromEndpoint {

    // retrieve from endpoint
    public String queryEndPoint ( String apiUrl ) throws IOException {

        if (apiUrl == null) {
            return "";
        }

        URL url = new URL(apiUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer {TOKEN}");
        conn.setRequestProperty("Content-Type", "application/json");
        // conn.addRequestProperty("User-Agent","curl/7.83.1");
        //Getting the response code
        int responseCode = conn.getResponseCode();

        if (responseCode == 403 || responseCode == 401) {
            //System.out.println(conn.getErrorStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            String f = in.readLine();
            in.close();
            System.out.println(f);
        }

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();
        System.out.println(inline);
        return inline;

    }

}
