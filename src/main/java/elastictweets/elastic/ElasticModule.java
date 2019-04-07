package elastictweets.elastic;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import elastictweets.domain.Tweet;
import elastictweets.services.Indexer;
import elastictweets.services.Searcher;
import elastictweets.util.Closer;
import elastictweets.util.GsonModule;
import elastictweets.util.RequestScope;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

@Module(includes = {
    GsonModule.class,
})
public abstract class ElasticModule {
    @Provides @RequestScope
    static public RestHighLevelClient provideElasticClient(Closer closer) {
        RestClientBuilder restBuilder =
            RestClient.builder(new HttpHost("localhost", 9200));
        return closer.register(new RestHighLevelClient(restBuilder));
    }

    @Binds public abstract Searcher<Tweet> bindTweeSearcher(ElasticTweetSearcher impl);
    @Binds public abstract Indexer<Tweet> bindTweetIndexer(ElasticTweetIndexer impl);
}
