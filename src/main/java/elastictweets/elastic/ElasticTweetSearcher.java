package elastictweets.elastic;

import com.google.gson.Gson;
import elastictweets.domain.Tweet;
import elastictweets.services.Searcher;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableList.toImmutableList;

public class ElasticTweetSearcher implements Searcher<Tweet> {
    private final RestHighLevelClient client;
    private final Gson gson;

    @Inject
    public ElasticTweetSearcher(RestHighLevelClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public List<Tweet> search(String query) {
        SearchSourceBuilder source = new SearchSourceBuilder()
            .query(new MatchQueryBuilder("message", query));

        SearchRequest request = new SearchRequest()
            .indices("tweets")
            .types("_doc")
            .source(source);

        SearchResponse response;
        try {
            response = client.search(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseResponse(response);
    }

    private List<Tweet> parseResponse(SearchResponse response) {
        return Stream.of(response.getHits().getHits())
            .map(this::parseHit)
            .collect(toImmutableList());
    }

    private Tweet parseHit(SearchHit hit) {
        return gson.fromJson(hit.getSourceAsString(), Tweet.class);
    }
}
