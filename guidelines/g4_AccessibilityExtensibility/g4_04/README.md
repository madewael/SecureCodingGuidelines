# EXTEND-4: Limit exposure of ClassLoader instances
Access to ClassLoader instances allows certain operations that may be undesirable:

Access to classes that client code would not normally be able to access.
Retrieve information in the URLs of resources (actually opening the URL is limited with the usual restrictions).
Assertion status may be turned on and off.
The instance may be cast to a subclass. ClassLoader subclasses frequently have undesirable methods.
Guideline 9-8 explains access checks made on acquiring ClassLoader instances through various Java library methods. Care should be taken when exposing a class loader through the thread context class loader.


# Others
 - EXTEND-1: [Limit the accessibility of classes, interfaces, methods, and fields](../g41)
 - EXTEND-2: [Limit the accessibility of packages](../g42)
 - EXTEND-3: [Isolate unrelated code](../g43)
 - EXTEND-4: Limit exposure of ClassLoader instances
 - EXTEND-5: [Limit the extensibility of classes and methods](../g45)
 - EXTEND-6: [Understand how a superclass can affect subclass behavior](../g46)
