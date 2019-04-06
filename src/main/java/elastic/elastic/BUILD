package(default_visibility = ["//visibility:public"])

java_library(
    name = "elastic",
    srcs = glob(["*.java"]),
    deps = [
        "//src/main/java/elastic/domain",
        "//src/main/java/elastic/services",
        "//src/main/java/elastic/util",
        "//thirdparty:dagger",
        "//thirdparty:elasticsearch",
        "//thirdparty:google-guava",
        "//thirdparty:gson",
        "//thirdparty:javax-inject",
    ],
)