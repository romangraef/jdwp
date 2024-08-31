# JDWP Protocol Lib

Simple Kotlin (Java) library for reading and writing JDWP packets. This is not a JDWP client or server implementation,
instead it is a library that can be used to write one of those (or something more complex such as a JDWP proxy that
somehow modifies the view of a remote JVM).

## Status

This library is currently working, however:

### Implementation Coverage

All commands and their replies are generated, but only for one java version (currently Java 21). While the code generator in 
[`domparser.py`](domparser.py) should work for other versions, I don't have a way to compile for multiple versions.

### Test Coverage

I could not find a library of sample packets that i can test my implementation against. But at least from manual testing
I could not find any issues.

## Documentation

A lot of the packets just mirror the original Oracle documentation, which can be found at:

 - https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-spec.html
 - https://docs.oracle.com/en/java/javase/21/docs/specs/jdwp/jdwp-protocol.html

Some of the documentation has also been inlined into [JavaDoc](https://nea89o.github.io/jdwp-protocol-lib/).

The classes you will most likely start to work with is `JDWPConnection`. Even other use cases such as servers,
can most likely be built on top of `JDWPConnection` (and if not, then the source of that should at least give you an
idea on how to do it).

## Publishing and Usage

This is currently not published to any maven repository, and probably will not ever be. Due to only working with one
Java version, it probably makes more sense for consumers of this library to generate their own versions. And to use them
via `./gradlew publishToMavenLocal`.

## License

The code I have ownership over is licensed under the [MIT LICENSE](LICENSE.txt). Some of the Documentation may
additionally be subject to Oracles Documentation License.

The gradlew, gradlew.bat and gradle-wrapper.jar files are licensed under
[Apache 2.0](https://github.com/gradle/gradle/blob/master/LICENSE)