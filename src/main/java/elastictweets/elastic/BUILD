package(default_visibility = ["//visibility:public"])

java_library(
    name = "elastic",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/elastictweets/domain:domain",
        "//src/main/java/elastictweets/services:services",
        "//src/main/java/elastictweets/util:util",
        "//thirdparty:dagger",
        "//thirdparty:elasticsearch",
        "//thirdparty:google-guava",
        "//thirdparty:gson",
        "//thirdparty:javax-inject",
    ],
)