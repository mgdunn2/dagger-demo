package elastictweets.services;

public interface Indexer<T> {
    void index(T tweet);
}
