# EXTEND-4: Limit exposure of ClassLoader instances
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Access to ClassLoader instances allows certain operations that may be undesirable:

Access to classes that client code would not normally be able to access.
Retrieve information in the URLs of resources (actually opening the URL is limited with the usual restrictions).
Assertion status may be turned on and off.
The instance may be cast to a subclass. ClassLoader subclasses frequently have undesirable methods.
Guideline 9-8 explains access checks made on acquiring ClassLoader instances through various Java library methods. Care should be taken when exposing a class loader through the thread context class loader.
