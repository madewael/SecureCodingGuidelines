# Denial of Service
Input into a system should be checked so that it will not cause excessive resource consumption disproportionate to that used to request the service. Common affected resources are CPU cycles, memory, disk space, and file descriptors.

In rare cases it may not be practical to ensure that the input is reasonable. It may be necessary to carefully combine the resource checking with the logic of processing the data. For client systems it is generally left to the user to close the application if it is using excessive resources. Therefore, only attacks resulting in persistent DoS, such as wasting significant disk space, need be defended against. Server systems should be robust against external attacks.

 - DOS-1: [Beware of activities that may use disproportionate resources](g11)
 - DOS-2: [Release resources in all cases](g12)
 - DOS-3: [Resource limit checks should not suffer from integer overflow](g13)




## Other Guidelines
 - 0 [Fundamentals](../g0_Fundamentals)
 - 1 Denial of Service
 - 2 [Confidential Information](../g2_ConfidentialInformation)
 - 3 [Injection and Inclusion](../g3_InjectionInclusion)
 - 4 [Accessibility and Extensibility](../g4_AccessibilityExtensibility)
 - 5 [Input Validation](../g5_InputValidation)
 - 6 [Mutability](../g6_Mutability)
 - 7 [Object Construction](../g7_ObjectConstruction)
 - 8 [Serialization and Deserialization](../g8_SerializationDeserialization)
 - 9 [Access Control](../g9_AccessControl)