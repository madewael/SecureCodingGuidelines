# OBJECT-2: Prevent the unauthorized construction of sensitive classes
Where an existing API exposes a security-sensitive constructor, limit the ability to create instances. A security-sensitive class enables callers to modify or circumvent SecurityManager access controls. Any instance of ClassLoader, for example, has the power to define classes with arbitrary security permissions.

To restrict untrusted code from instantiating a class, enforce a SecurityManager check at all points where that class can be instantiated. In particular, enforce a check at the beginning of each public and protected constructor. In classes that declare public static factory methods in place of constructors, enforce checks at the beginning of each factory method. Also enforce checks at points where an instance of a class can be created without the use of a constructor. Specifically, enforce a check inside the readObject or readObjectNoData method of a serializable class, and inside the clone method of a cloneable class.

If the security-sensitive class is non-final, this guideline not only blocks the direct instantiation of that class, it blocks malicious subclassing as well.

## Others
 - OBJECT-1: [Avoid exposing constructors of sensitive classes](../g71)
 - OBJECT-2: Prevent the unauthorized construction of sensitive classes
 - OBJECT-3: [Defend against partially initialized instances of non-final classes](../g73)
 - OBJECT-4: [Prevent constructors from calling methods that can be overridden](../g74) 
 - OBJECT-5: [Defend against cloning of non-final classes](../g75)
