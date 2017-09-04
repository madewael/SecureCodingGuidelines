# OBJECT-4: Prevent constructors from calling methods that can be overridden
Constructors that call overridable methods give attackers a reference to this (the object being constructed) before the object has been fully initialized. Likewise, clone, readObject, or readObjectNoData methods that call overridable methods may do the same. The readObject methods will usually call java.io.ObjectInputStream.defaultReadObject, which is an overridable method.


## Others
 - OBJECT-1: [Avoid exposing constructors of sensitive classes](../g71)
 - OBJECT-2: [Prevent the unauthorized construction of sensitive classes](../g72)
 - OBJECT-3: [Defend against partially initialized instances of non-final classes](../g73)
 - OBJECT-4: Prevent constructors from calling methods that can be overridden
 - OBJECT-5: [Defend against cloning of non-final classes](../g75)
