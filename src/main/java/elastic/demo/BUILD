java_binary(
    name = "index",
    srcs = glob(["Index.java"]),
    main_class = 'elastic.demo.Index',
    deps = [
        "//src/main/java/elastic/elastic",
        "//src/main/java/elastic/services",
        "//src/main/java/elastic/twitterstream",
        "//src/main/java/elastic/util",
        "//thirdparty:dagger",
    ],
)

java_binary(
    name = "search",
    srcs = glob(["Search.java"]),
    main_class = 'elastic.demo.Search',
    deps = [
        "//src/main/java/elastic/domain",
        "//src/main/java/elastic/elastic",
        "//src/main/java/elastic/services",
        "//src/main/java/elastic/util",
        "//thirdparty:dagger",
    ],
)