import org.junit.Test;
import static org.junit.Assert.*;

import whynotkafka.demo.TweetRetriever;

import java.io.IOException;

public class TestRetrieveFromTwitter {
        @Test
        public void testAssertions() throws IOException {
            assertNotNull(new TweetRetriever().getTweets());
        }
}

