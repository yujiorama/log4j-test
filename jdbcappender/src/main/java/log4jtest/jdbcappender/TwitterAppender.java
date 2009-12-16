/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jtest.jdbcappender;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import twitter4j.AsyncTwitter;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author yuji
 */
public class TwitterAppender extends AppenderSkeleton {

    private Twitter twitter;
    private String screenName;
    private String password;

    public TwitterAppender() {
        super();
        twitter = new AsyncTwitter(getScreenName(), getPassword());
    }

    @Override
    protected void append(LoggingEvent event) {
        Status status;
        if (twitter != null) {
            try {
                twitter.sendDirectMessage(getScreenName(), layout.format(event));
            } catch (TwitterException ex) {
                Logger.getLogger(TwitterAppender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    /**
     * @return the screenName
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * @param screenName the screenName to set
     */
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
