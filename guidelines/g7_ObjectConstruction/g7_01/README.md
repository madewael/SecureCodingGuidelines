# OBJECT-1: Avoid exposing constructors of sensitive classes
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Construction of classes can be more carefully controlled if constructors are not exposed. Define static factory methods instead of public constructors. Support extensibility through delegation rather than inheritance. Implicit constructors through serialization and clone should also be avoided.

