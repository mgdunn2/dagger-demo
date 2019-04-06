package elastic.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Searcher {
    private final RestHighLevelClient client;

    public Searcher(RestHighLevelClient client) {
        this.client = client;
    }

    public List<String> search(String query) {
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

    private List<String> parseResponse(SearchResponse response) {
        return Stream.of(response.getHits().getHits())
            .map(this::parseHit)
            .collect(toList());
    }

    private String parseHit(SearchHit hit) {
        Map<String, Object> source = hit.getSourceAsMap();
        return String.format("%s %s: %s\n%s",
            source.get("date"),
            source.get("author"),
            source.get("message"),
            source.get("sentiment"));
    }
}
