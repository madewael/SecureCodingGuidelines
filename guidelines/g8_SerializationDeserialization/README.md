# 8 Serialization and Deserialization ![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Java Serialization provides an interface to classes that sidesteps the field access control mechanisms of the Java language. As a result, care must be taken when performing serialization and deserialization. Furthermore, deserialization of untrusted data should be avoided whenever possible, and should be performed safely when it cannot be avoided.

 - SERIAL-1: [Avoid serialization for security-sensitive classes](g8_01)
 - SERIAL-2: [Guard sensitive data during serialization](g8_02)
 - SERIAL-3: [View deserialization the same as object construction](g8_03)
 - SERIAL-4: [Duplicate the SecurityManager checks enforced in a class during serialization and deserialization](g8_04)
 - SERIAL-5: [Understand the security permissions given to serialization and deserialization](g8_05)
