package elastic.twitterstream;

import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;
import elastic.domain.ImmutableTweet;
import elastic.domain.Tweet;
import elastic.elastic.ElasticModule;
import elastic.services.Indexer;
import elastic.util.Closer;
import elastic.util.RequestScope;
import elastic.util.TweetFilter;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class TweetStreamer {
    private final ConfigurationSupplier configurationSupplier;
    private final String filter;
    private final Provider<Request.Builder> requestProvider;

    @Inject
    public TweetStreamer(
        ConfigurationSupplier configurationSupplier,
        @TweetFilter String filter,
        Provider<Request.Builder> requestProvider)
    {
        this.configurationSupplier = configurationSupplier;
        this.filter = filter;
        this.requestProvider = requestProvider;
    }

    public void listen() {
        TwitterStream stream =
            new TwitterStreamFactory(configurationSupplier.get().build()).getInstance();
        stream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                try (Closer closer = new Closer()) {
                    requestProvider.get()
                        .closer(closer)
                        .build()
                        .indexer()
                        .index(from(status));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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

    private Tweet from(Status status) {
        return ImmutableTweet.builder()
            .author(status.getUser().getName())
            .date(status.getCreatedAt())
            .filter(filter)
            .message(status.getText())
            .subjectivity("")
            .polarity("")
            .sentiment("neutral")
            .build();
    }

    @RequestScope
    @Subcomponent(modules = {
        ElasticModule.class,
    })
    public interface Request {
        Indexer<Tweet> indexer();

        @Subcomponent.Builder
        interface Builder {
            @BindsInstance
            Builder closer(Closer closer);
            Request build();
        }
    }

    @Module(subcomponents = Request.class)
    public static class RequestModule { }
}
