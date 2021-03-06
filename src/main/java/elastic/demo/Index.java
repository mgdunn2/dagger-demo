/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package elastic.demo;

import dagger.BindsInstance;
import dagger.Component;
import elastic.twitterstream.TweetStreamer;
import elastic.twitterstream.TweetStreamer.RequestModule;
import elastic.util.GsonModule;
import elastic.util.TweetFilter;

import javax.inject.Inject;
import javax.inject.Singleton;

public class Index {
    private final TweetStreamer tweetStreamer;

    @Inject
    public Index(TweetStreamer tweetStreamer){
        this.tweetStreamer = tweetStreamer;
    }

    private void start() {
        tweetStreamer.listen();
    }

    public static void main(String[] args) {
        String tweetFilter = "uva";
        if (args.length == 1) {
            tweetFilter = args[0];
        }

        DaggerIndex_App.builder()
            .tweetFilter(tweetFilter)
            .build()
            .index()
            .start();
    }

    @Singleton
    @Component(modules = {
        RequestModule.class,
    })
    interface App {
        Index index();

        @Component.Builder
        interface Builder {
            @BindsInstance
            Builder tweetFilter(@TweetFilter String tweetFilter);
            App build();
        }
    }
}
