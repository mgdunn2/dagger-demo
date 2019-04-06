package elastic.services;

import java.util.List;

public interface Searcher<T> {
    List<T> search(String query);
}
