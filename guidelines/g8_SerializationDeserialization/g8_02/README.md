# SERIAL-2: Guard sensitive data during serialization
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Once an object has been serialized the Java language's access controls can no longer be enforced and attackers can access private fields in an object by analyzing its serialized byte stream. Therefore, do not serialize sensitive data in a serializable class.

Approaches for handling sensitive fields in serializable classes are:

Declare sensitive fields transient
Define the serialPersistentFields array field appropriately
Implement writeObject and use ObjectOutputStream.putField selectively
Implement writeReplace to replace the instance with a serial proxy
Implement the Externalizable interface