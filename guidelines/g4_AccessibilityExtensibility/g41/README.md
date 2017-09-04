# EXTEND-1: Limit the accessibility of classes, interfaces, methods, and fields
A Java package comprises a grouping of related Java classes and interfaces. Declare any class or interface public if it is specified as part of a published API, otherwise, declare it package-private. Similarly, declare class members and constructors (nested classes, methods, or fields) public or protected as appropriate, if they are also part of the API. Otherwise, declare them private or package-private to avoid exposing the implementation. Note that members of interfaces are implicitly public.

Classes loaded by different loaders do not have package-private access to one another even if they have the same package name. Classes in the same package loaded by the same class loader must either share the same code signing certificate or not have a certificate at all. In the Java virtual machine class loaders are responsible for defining packages. It is recommended that, as a matter of course, packages are marked as sealed in the jar file manifest.


# Others
 - EXTEND-1: Limit the accessibility of classes, interfaces, methods, and fields
 - EXTEND-2: [Limit the accessibility of packages](../g42)
 - EXTEND-3: [Isolate unrelated code](../g43)
 - EXTEND-4: [Limit exposure of ClassLoader instances](../g44)
 - EXTEND-5: [Limit the extensibility of classes and methods](../g45)
 - EXTEND-6: [Understand how a superclass can affect subclass behavior](../g46)
