# SERIAL-2: Guard sensitive data during serialization
Once an object has been serialized the Java language's access controls can no longer be enforced and attackers can access private fields in an object by analyzing its serialized byte stream. Therefore, do not serialize sensitive data in a serializable class.

Approaches for handling sensitive fields in serializable classes are:

Declare sensitive fields transient
Define the serialPersistentFields array field appropriately
Implement writeObject and use ObjectOutputStream.putField selectively
Implement writeReplace to replace the instance with a serial proxy
Implement the Externalizable interface

## Others

 - SERIAL-1: [Avoid serialization for security-sensitive classes](../g81)
 - SERIAL-2: Guard sensitive data during serialization
 - SERIAL-3: [View deserialization the same as object construction](../g83)
 - SERIAL-4: [Duplicate the SecurityManager checks enforced in a class during serialization and deserialization](../g84)
 - SERIAL-5: [Understand the security permissions given to serialization and deserialization](../g85)
