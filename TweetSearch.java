/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stringpatternmatching;

import twitter4j.*;

import java.util.List;
import twitter4j.conf.ConfigurationBuilder;


public class TweetSearch {

    public List<Status> search(String S) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
         .setOAuthConsumerKey("npqFNujUFByRor973eqw")
         .setOAuthConsumerSecret("nlDkxAX5Tm776xS5d8m5CMoMJ5lYod9WgXobWaWsIo")
         .setOAuthAccessToken("2219158148-eoU30JZNaTRgDWDvIix3FPxA2J7N2bT4LJ2NSWO")
         .setOAuthAccessTokenSecret("yf9AQhhipRs94jy9UMZsAZf3OA5g98m1Yo2PyMunwnnyH");

        List<Status> tweets = null;
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        int Count = 0;
        try {
            Query query = new Query(S);
            QueryResult result;
            do {
                
                result = twitter.search(query);
                tweets = result.getTweets();

                Count++;
            } while ((query = result.nextQuery()) != null && Count < 10);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();

        }
        return tweets;
    }
}