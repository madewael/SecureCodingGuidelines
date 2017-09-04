# OBJECT-5: Defend against cloning of non-final classes
A non-final class may be subclassed by a class that also implements java.lang.Cloneable. The result is that the base class can be unexpectedly cloned, although only for instances created by an adversary. The clone will be a shallow copy. The twins will share referenced objects but have different fields and separate intrinsic locks. The "pointer to implementation" approach detailed in Guideline 7-3 provides a good defense.

## Others
 - OBJECT-1: [Avoid exposing constructors of sensitive classes](../g71)
 - OBJECT-2: [Prevent the unauthorized construction of sensitive classes](../g72)
 - OBJECT-3: [Defend against partially initialized instances of non-final classes](../g73)
 - OBJECT-4: [Prevent constructors from calling methods that can be overridden](../g74) 
 - OBJECT-5: Defend against cloning of non-final classes
