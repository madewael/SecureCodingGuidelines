# SERIAL-1: Avoid serialization for security-sensitive classes
Security-sensitive classes that are not serializable will not have the problems detailed in this section. Making a class serializable effectively creates a public interface to all fields of that class. Serialization also effectively adds a hidden public constructor to a class, which needs to be considered when trying to restrict object construction.

Similarly, lambdas should be scrutinized before being made serializable. Functional interfaces should not be made serializable without due consideration for what could be exposed.

## Others

 - SERIAL-1: Avoid serialization for security-sensitive classes
 - SERIAL-2: [Guard sensitive data during serialization](../g82)
 - SERIAL-3: [View deserialization the same as object construction](../g83)
 - SERIAL-4: [Duplicate the SecurityManager checks enforced in a class during serialization and deserialization](../g84)
 - SERIAL-5: [Understand the security permissions given to serialization and deserialization](../g85)
