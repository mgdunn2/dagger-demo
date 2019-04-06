package elastic.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;

public class TweetStreamer {
    private final ConfigurationBuilder configurationBuilder;
    private final String filter;

    public TweetStreamer(ConfigurationBuilder configurationBuilder, String filter) {
        this.configurationBuilder = configurationBuilder;
        this.filter = filter;
    }

    public void listen() {
        TwitterStream stream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
        stream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                TweetModel tweet = TweetModel.builder()
                    .withAuthor(status.getUser().getName())
                    .withDate(status.getCreatedAt().toString())
                    .withFilter(filter)
                    .withMessage(status.getText())
                    .withSubjectivity("")
                    .withPolarity("")
                    .withSentiment("neutral")
                    .build();
                RestClientBuilder restBuilder =
                    RestClient.builder(new HttpHost("localhost", 9200));
                try (RestHighLevelClient client = new RestHighLevelClient(restBuilder)) {
                    new Indexer(client).indexTweet(tweet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(tweet);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onException(Exception ex) {

            }
        });
        stream.filter(filter);
    }
}
