# OBJECT-1: Avoid exposing constructors of sensitive classes
Construction of classes can be more carefully controlled if constructors are not exposed. Define static factory methods instead of public constructors. Support extensibility through delegation rather than inheritance. Implicit constructors through serialization and clone should also be avoided.

## Others
 - OBJECT-1: Avoid exposing constructors of sensitive classes
 - OBJECT-2: [Prevent the unauthorized construction of sensitive classes](../g72)
 - OBJECT-3: [Defend against partially initialized instances of non-final classes](../g73)
 - OBJECT-4: [Prevent constructors from calling methods that can be overridden](../g74) 
 - OBJECT-5: [Defend against cloning of non-final classes](../g75)
