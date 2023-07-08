# JDWP Protocol Lib

Simple Kotlin (Java) library for reading and writing JDWP packets. This is not a JDWP client or server implementation,
instead it is a library that can be used to write one of those (or something more complex such as a JDWP proxy that
somehow modifies the view of a remote JVM).

## Status

This library is currently working, however:

### Implementation Coverage

Currently, does not implement the Event Command Set and the Composite Event Command. This one is just too complex for my
code generation, and will need to be manually done, and I want to finalize the primitives before that.

### Test Coverage

I could not find a library of sample packets that i can test my implementation against. But at least from manual testing
I could not find any issues.

## Documentation

A lot of the packets just mirror the original Oracle documentation, which can be found at:

 - https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-spec.html
 - https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html

Some of the documentation has also been inlined into [JavaDoc](https://romangraef.github.io/jdwp-protocol-lib/).

The classes you will most likely start to work with is `JDWPConnection`. Even other use cases such as servers,
can most likely be built on top of `JDWPConnection` (and if not, then the source of that should at least give you an
idea on how to do it).

## Publishing and Usage

This is currently not published to any maven repository, but probably soon will be. Until then either use
`./gradlew publishToMavenLocal` or [https://jitpack.io]

## License

The code I have ownership over is licensed under the [MIT LICENSE](LICENSE.txt). Some of the Documentation may
additionally be subject to Oracles Documentation License.

The gradlew, gradlew.bat and gradle-wrapper.jar files are licensed under
[Apache 2.0](https://github.com/gradle/gradle/blob/master/LICENSE)