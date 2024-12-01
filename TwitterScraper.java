import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterScraper {
    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("your_consumer_key")
          .setOAuthConsumerSecret("your_consumer_secret")
          .setOAuthAccessToken("your_access_token")
          .setOAuthAccessTokenSecret("your_access_token_secret");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try {
            Query query = new Query("stock market");
            query.setCount(100); 
            query.setLang("en");
            QueryResult result = twitter.search(query);

            List<Status> tweets = result.getTweets();
            List<String> tweetTexts = new ArrayList<>();
            for (Status tweet : tweets) {
                tweetTexts.add(tweet.getText().replaceAll("\\s+", " ").trim());
            }
            tweetTexts.forEach(System.out::println);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
