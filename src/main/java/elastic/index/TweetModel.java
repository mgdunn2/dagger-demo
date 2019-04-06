package elastic.index;

public class TweetModel {
    private final String author;
    private final String date;
    private final String message;
    private final String polarity;
    private final String subjectivity;
    private final String sentiment;
    private final String filter;

    private TweetModel(
        String author,
        String date,
        String message,
        String polarity,
        String subjectivity,
        String sentiment,
        String filter)
    {
        this.author = author;
        this.date = date;
        this.message = message;
        this.polarity = polarity;
        this.subjectivity = subjectivity;
        this.sentiment = sentiment;
        this.filter = filter;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getPolarity() {
        return polarity;
    }

    public String getSubjectivity() {
        return subjectivity;
    }

    public String getSentiment() {
        return sentiment;
    }

    public String getFilter() {
        return filter;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "TweetModel{" +
            "author='" + author + '\'' +
            ", date='" + date + '\'' +
            ", message='" + message + '\'' +
            ", sentiment='" + sentiment + '\'' +
            '}';
    }

    public static final class Builder {
        private String author;
        private String date;
        private String message;
        private String polarity;
        private String subjectivity;
        private String sentiment;
        private String filter;

        private Builder() {
        }

        public static Builder aTweetModel() {
            return new Builder();
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withPolarity(String polarity) {
            this.polarity = polarity;
            return this;
        }

        public Builder withSubjectivity(String subjectivity) {
            this.subjectivity = subjectivity;
            return this;
        }

        public Builder withSentiment(String sentiment) {
            this.sentiment = sentiment;
            return this;
        }

        public Builder withFilter(String filter) {
            this.filter = filter;
            return this;
        }

        public TweetModel build() {
            return new TweetModel(author, date, message, polarity, subjectivity, sentiment, filter);
        }
    }
}
