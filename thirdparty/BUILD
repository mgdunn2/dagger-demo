package(default_visibility = ["//visibility:public"])

java_library(
    name = "apache-httpcore",
    exports = [
        "@maven//:org_apache_httpcomponents_httpcore",
    ]
)

java_library(
    name = "dagger-api",
    exports = [
        "@maven//:com_google_dagger_dagger",
    ]
)
java_library(
    name = "dagger-compiler",
    exports = [
        "@maven//:com_google_dagger_dagger_compiler",
    ]
)
java_library(
    name = "dagger-producers",
    exports = [
        "@maven//:com_google_dagger_dagger_producers",
    ]
)

java_library(
    name = "dagger",
    exported_plugins = [":dagger-plugin"],
    exports = [
        ":dagger-api",
        ":javax-inject",
    ],
)

java_plugin(
    name = "dagger-plugin",
    generates_api = True,
    processor_class = "dagger.internal.codegen.ComponentProcessor",
    deps = [
        ":dagger-api",
        ":dagger-compiler",
        ":dagger-producers",
        ":google-guava",
        ":javax-inject",
    ],
)

java_library(
    name = "gson",
    exports = [
        "@maven//:com_google_code_gson_gson",
    ],
)

java_library(
    name = "google-guava",
    exports = [
        "@maven//:com_google_guava_guava",
    ],
)

java_library(
    name = "elasticsearch",
    exports = [
        "@maven//:org_elasticsearch_client_elasticsearch_rest_high_level_client",
        "@maven//:org_elasticsearch_client_elasticsearch_rest_client",
        "@maven//:org_elasticsearch_elasticsearch",
        ":apache-httpcore"
    ]
)

java_library(
    name = "javax-inject",
    exports = [
        "@maven//:javax_inject_javax_inject",
    ],
)

java_library(
    name = "jsr305",
    exports = [
        "@maven//:com_google_code_findbugs_jsr305",
    ]
)

java_library(
    name = "immutables",
    exported_plugins = [":immutables-plugin"],
    exports = [
        ":immutables-value",
    ]
)

java_library(
    name = "immutables-value",
    exports = [
        "@maven//:org_immutables_value",
    ]
)

java_library(
    name = "immutables-gson",
    exports = [
        "@maven//:org_immutables_gson",
        ":gson"
    ]
)

java_plugin(
    name = "immutables-plugin",
    generates_api = True,
    processor_class = "org.immutables.processor.ProxyProcessor",
    deps = [
        ":immutables-value",
        ":google-guava",
        ":jsr305",
    ],
)

java_library(
    name = "twitter",
    exports = [
        "@maven//:org_twitter4j_twitter4j_stream",
        "@maven//:org_twitter4j_twitter4j_core",
    ]
)
