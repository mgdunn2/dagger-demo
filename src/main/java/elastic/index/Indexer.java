package elastic.index;

import com.google.common.collect.ImmutableMap;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class Indexer {
    private final RestHighLevelClient client;

    public Indexer(RestHighLevelClient client) {
        this.client = client;
    }

    public void indexTweet(TweetModel tweet) {
        try {
            client.index(new IndexRequest("tweets", "_doc")
                .source(ImmutableMap.<String, Object>builder()
                    .put("author", tweet.getAuthor())
                    .put("date", tweet.getDate())
                    .put("message", tweet.getMessage())
                    .put("polarity", tweet.getPolarity())
                    .put("subjectivity", tweet.getSubjectivity())
                    .put("sentiment", tweet.getSentiment())
                    .put("filter", tweet.getFilter())
                    .build()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
