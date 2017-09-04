# EXTEND-6: Understand how a superclass can affect subclass behavior
Subclasses do not have the ability to maintain absolute control over their own behavior. A superclass can affect subclass behavior by changing the implementation of an inherited method that is not overridden. If a subclass overrides all inherited methods, a superclass can still affect subclass behavior by introducing new methods. Such changes to a superclass can unintentionally break assumptions made in a subclass and lead to subtle security vulnerabilities. Consider the following example that occurred in JDK 1.2:

            Class Hierarchy                  Inherited Methods
        -----------------------          --------------------------
          java.util.Hashtable            put(key, val)
                    ^                    remove(key)
                    | extends
                    |
          java.util.Properties
                    ^
                    | extends
                    |
         java.security.Provider          put(key, val) // SecurityManager
                                         remove(key)   // checks for these
                                                       // methods
The class java.security.Provider extends from java.util.Properties, and Properties extends from java.util.Hashtable. In this hierarchy, the Provider class inherits certain methods from Hashtable, including put and remove. Provider.put maps a cryptographic algorithm name, like RSA, to a class that implements that algorithm. To prevent malicious code from affecting its internal mappings, Provider overrides put and remove to enforce the necessary SecurityManager checks.

The Hashtable class was enhanced in JDK 1.2 to include a new method, entrySet, which supports the removal of entries from the Hashtable. The Provider class was not updated to override this new method. This oversight allowed an attacker to bypass the SecurityManager check enforced in Provider.remove, and to delete Provider mappings by simply invoking the Hashtable.entrySet method.

The primary flaw is that the data belonging to Provider (its mappings) is stored in the Hashtable class, whereas the checks that guard the data are enforced in the Provider class. This separation of data from its corresponding SecurityManager checks only exists because Provider extends from Hashtable. Because a Provider is not inherently a Hashtable, it should not extend from Hashtable. Instead, the Provider class should encapsulate a Hashtable instance allowing the data and the checks that guard that data to reside in the same class. The original decision to subclass Hashtable likely resulted from an attempt to achieve code reuse, but it unfortunately led to an awkward relationship between a superclass and its subclasses, and eventually to a security vulnerability.

Malicious subclasses may implement java.lang.Cloneable. Implementing this interface affects the behaviour of the subclass. A clone of a victim object may be made. The clone will be a shallow copy. The intrinsic lock and fields of the two objects will be different, but referenced objects will be the same. This allows an adversary to confuse the state of instances of the attacked class.

JDK 8 introduced default methods on interfaces.  These default methods are another path for new and unexpected methods to show up in a class.  If a class implements an interface with default methods, those are now part of the class and may allow unexpected access to internal data.  For a security sensitive class, all interfaces implemented by the class (and all superclasses) would need to be monitored as previously discussed.