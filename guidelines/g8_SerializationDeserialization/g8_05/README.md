# SERIAL-5: Understand the security permissions given to serialization and deserialization
Permissions appropriate for deserialization should be carefully checked. Additionally, deserialization of untrusted data should generally be avoided whenever possible.

Serialization with full permissions allows permission checks in writeObject methods to be circumvented. For instance, java.security.GuardedObject checks the guard before serializing the target object. With full permissions, this guard can be circumvented and the data from the object (although not the object itself) made available to the attacker.

Deserialization is more significant. A number of readObject implementations attempt to make security checks, which will pass if full permissions are granted. Further, some non-serializable security-sensitive, subclassable classes have no-argument constructors, for instance ClassLoader. Consider a malicious serializable class that subclasses ClassLoader. During deserialization the serialization method calls the constructor itself and then runs any readObject in the subclass. When the ClassLoader constructor is called no unprivileged code is on the stack, hence security checks will pass. Thus, don't deserialize with permissions unsuitable for the data. Instead, data should be deserialized with the least necessary privileges.

## Others

 - SERIAL-1: [Avoid serialization for security-sensitive classes](../g81)
 - SERIAL-2: [Guard sensitive data during serialization](../g82)
 - SERIAL-3: [View deserialization the same as object construction](../g83)
 - SERIAL-4: [Duplicate the SecurityManager checks enforced in a class during serialization and deserialization](../g84)
 - SERIAL-5: Understand the security permissions given to serialization and deserialization
