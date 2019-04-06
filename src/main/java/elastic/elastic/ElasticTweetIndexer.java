package elastic.elastic;

import com.google.gson.Gson;
import elastic.domain.Tweet;
import elastic.services.Indexer;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import javax.inject.Inject;

import java.io.IOException;

public class ElasticTweetIndexer implements Indexer<Tweet> {
    private final RestHighLevelClient client;
    private final Gson gson;

    @Inject
    public ElasticTweetIndexer(RestHighLevelClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public void index(Tweet tweet) {
        try {
            client.index(new IndexRequest("tweets", "_doc")
                .source(gson.toJson(tweet, Tweet.class), XContentType.JSON));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(tweet);
    }
}
