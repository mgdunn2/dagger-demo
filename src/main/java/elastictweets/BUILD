java_binary(
    name = "index",
    srcs = glob(["Index.java"]),
    main_class = 'elastic.demo.Index',
    deps = [
        "//src/main/java/elastictweets/elastic:elastic",
        "//src/main/java/elastictweets/services:services",
        "//src/main/java/elastictweets/twitterstream:twitterstream",
        "//src/main/java/elastictweets/util:util",
        "//thirdparty:dagger",
    ],
)

java_binary(
    name = "search",
    srcs = glob(["Search.java"]),
    main_class = 'elastic.demo.Search',
    deps = [
        "//src/main/java/elastictweets/domain:domain",
        "//src/main/java/elastictweets/elastic:elastic",
        "//src/main/java/elastictweets/services:services",
        "//src/main/java/elastictweets/util:util",
        "//thirdparty:dagger",
    ],
)