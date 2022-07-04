package whynotkafka.demo;
import com.twitter.clientlib.*;
import com.twitter.clientlib.model.*;
import com.twitter.clientlib.api.TwitterApi;
import java.io.InputStream;
import com.google.common.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TweetRetriever {
    public JSONArray getTweets() {

        TwitterCredentialsBearer credentials = new TwitterCredentialsBearer(System.getenv("TWITTER_BEARER_TOKEN"));
        TwitterApi apiInstance = new TwitterApi(credentials);

        Set<String> tweetFields = new HashSet<>(Arrays.asList());
        tweetFields.add("author_id");
        try {
            InputStream result = apiInstance.tweets().sampleStream().execute();
            try{
                JSON json = new JSON();
                JSONArray jsonArray = new JSONArray();
                Type localVarReturnType = new TypeToken<StreamingTweetResponse>(){}.getType();
                BufferedReader reader = new BufferedReader(new InputStreamReader(result));
                String line = reader.readLine();

                // Get ISO timestamp
                String datetime = ZonedDateTime.now( ZoneOffset.UTC ).format(DateTimeFormatter.ISO_INSTANT);

                int counter = 0;
                while (line != null) {
                    if( counter > 20){
                        break;
                    }
                    if(line.isEmpty()) {
                        System.out.println("==> Empty line");
                        line = reader.readLine();
                        continue;
                    }
                    JSONObject tweetJSON = new JSONObject(reader.readLine());
                    Date date = new Date();
                    tweetJSON.append("date_retrieved", datetime);
                    jsonArray.put(tweetJSON);
                    counter++;
                }
                return jsonArray;
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } catch (ApiException e) {
            System.err.println("Exception when calling TweetsApi#sampleStream");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
        return null;
    }
}