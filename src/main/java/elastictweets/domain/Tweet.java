package elastictweets.domain;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
@Gson.TypeAdapters
public abstract class Tweet {
    public abstract String author();
    public abstract Date date();
    public abstract String message();
    public abstract String polarity();
    public abstract String subjectivity();
    public abstract String sentiment();
    public abstract String filter();

    @Override
    public String toString() {
        return String.format("%s %s: %s", date().toString(), author(), message());
    }
}
