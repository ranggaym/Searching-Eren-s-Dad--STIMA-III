/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stringpatternmatching;

import java.util.ArrayList;
import twitter4j.*;

import java.util.List;
import twitter4j.conf.ConfigurationBuilder;


public class TweetSearch {

    private final String CONSUMER_KEY = "npqFNujUFByRor973eqw";
    private final String CONSUMER_SECRET = "nlDkxAX5Tm776xS5d8m5CMoMJ5lYod9WgXobWaWsIo";
    private final String ACCESS_TOKEN = "2219158148";
    private final String TOKEN_SECRET = "yf9AQhhipRs94jy9UMZsAZf3OA5g98m1Yo2PyMunwnnyH";
    
    public List<Status> search(String S) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
         .setOAuthConsumerKey(CONSUMER_KEY)
         .setOAuthConsumerSecret(CONSUMER_SECRET)
         .setOAuthAccessToken(ACCESS_TOKEN)
         .setOAuthAccessTokenSecret(TOKEN_SECRET);

        List<Status> tweets = null;
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        int Count = 0;
        String TweetText;
        String TweetURL;
        try {
            Query query = new Query(S);
            QueryResult result;
            do {
                
                result = twitter.search(query);
                tweets = result.getTweets();
                
//                for (Status tweet : tweets) {
//                    TweetText = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
//                    TweetURL = "http://twitter.com/#!/" + tweet.getUser().getName() + "/status/" + tweet.getId();
//                    //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
//                    System.out.println("<a href=\""+TweetURL+"\"> "+TweetText+" </a> ");
//                }
                Count++;
            } while ((query = result.nextQuery()) != null && Count < 10);
            
        } catch (TwitterException te) {
            te.printStackTrace();

        }
        return tweets;
    }
    
    public ArrayList<String> getHTMLTweetURLList(List<Status> tweets){
        ArrayList<String> result = new ArrayList<>();
        String TweetText;
        String TweetURL;
        
        for (Status tweet : tweets) {
            TweetText = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
            TweetURL = "http://twitter.com/#!/" + tweet.getUser().getName() + "/status/" + tweet.getId();
            //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
            //System.out.println("<a href=\""+TweetURL+"\"> "+TweetText+" </a> ");
            result.add("<a href=\""+TweetURL+"\"> "+TweetText+" </a> ");
        }
        return result;
    }
    
    public ArrayList<String> getTweetTextList(List<Status> tweets){
        ArrayList<String> result = new ArrayList<>();
        String TweetText;
        String TweetURL;
        
        for (Status tweet : tweets) {
            TweetText = "@" + tweet.getUser().getScreenName() + " - " + tweet.getText();
            //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
            //System.out.println("<a href=\""+TweetURL+"\"> "+TweetText+" </a> ");
            result.add("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
        }
        return result;
    }
}