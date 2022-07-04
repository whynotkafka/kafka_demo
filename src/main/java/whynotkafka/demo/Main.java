package whynotkafka.demo;

import org.json.JSONArray;
import whynotkafka.demo.JSONProducer;
import whynotkafka.demo.TweetRetriever;

/**
 * Created by Matthew on 03/07/2022.
 */
public class Main {

    public static void main (String[] args) throws Exception {

        TweetRetriever tweetRetriever = new TweetRetriever();
        JSONArray jsonArray = tweetRetriever.getTweets();
        JSONProducer jsonProducer = new JSONProducer();
        boolean tweetsSent = jsonProducer.produceMessage("tweets2", jsonArray);

        if (!tweetsSent){
            System.out.println("failed to produce msg");
        }
    }

}
