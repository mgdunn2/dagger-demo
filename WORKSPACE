load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-1.2",
    sha256 = "e5c68b87f750309a79f59c2b69ead5c3221ffa54ff9496306937bfa1c9c8c86b",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/1.2.zip"
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    name = "maven",
    artifacts = [
        "com.google.code.findbugs:jsr305:1.3.9",
        "com.google.dagger:dagger:2.22",
        "com.google.dagger:dagger-compiler:2.22",
        "com.google.dagger:dagger-producers:2.22",
        "com.google.code.gson:gson:2.8.5",
        "com.google.guava:guava:27.0.1-jre",
        "javax.inject:javax.inject:1",
        "org.apache.httpcomponents:httpcore:4.4.5",
        "org.elasticsearch:elasticsearch:6.2.3",
        "org.elasticsearch.client:elasticsearch-rest-client:6.2.3",
        "org.elasticsearch.client:elasticsearch-rest-high-level-client:6.2.3",
        "org.immutables:gson:2.7.5",
        "org.immutables:value:2.7.5",
        "org.twitter4j:twitter4j-core:4.0.7",
        "org.twitter4j:twitter4j-stream:4.0.7",
    ],
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
    fetch_sources = True,   # Fetch source jars. Defaults to False.
)