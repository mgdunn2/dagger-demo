package elastic.elastic;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import elastic.domain.Tweet;
import elastic.services.Indexer;
import elastic.services.Searcher;
import elastic.util.Closer;
import elastic.util.GsonModule;
import elastic.util.RequestScope;
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
