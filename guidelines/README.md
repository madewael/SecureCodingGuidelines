# Secure Coding Guidelines



## Categories

 - 0 [Fundamentals](g0_Fundamentals)
 - 1 [Denial of Service](g1_DoS)
 - 2 [Confidential Information](g2_ConfidentialInformation)
 - 3 [Injection and Inclusion](g3_InjectionInclusion)
 - 4 [Accessibility and Extensibility](g4_AccessibilityExtensibility)
 - 5 [Input Validation](g5_InputValidation)
 - 6 [Mutability](g6_Mutability)
 - 7 [Object Construction](g7_ObjectConstruction)
 - 8 [Serialization and Deserialization](g8_SerializationDeserialization)
 - 9 [Access Control](g9_AccessControl)

 
## Introduction ![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)

One of the main design considerations for the Java platform is to provide a secure environment for executing mobile code. Java comes with its own unique set of security challenges. While the Java security architecture [1] can protect users and systems from hostile programs downloaded over a network, it cannot defend against implementation bugs that occur in trusted code. Such bugs can inadvertently open the very holes that the security architecture was designed to contain, including access to files, printers, webcams, microphones, and the network from behind firewalls. In severe cases local programs may be executed or Java security disabled. These bugs can potentially be used to turn the machine into a zombie computer, steal confidential data from machine and intranet, spy through attached devices, prevent useful operation of the machine, assist further attacks, and many other malicious activities.

The choice of language system impacts the robustness of any software program. The Java language [2] and virtual machine [3] provide many features to mitigate common programming mistakes. The language is type-safe, and the runtime provides automatic memory management and bounds-checking on arrays. Java programs and libraries check for illegal state at the earliest opportunity. These features also make Java programs highly resistant to the stack-smashing [4] and buffer overflow attacks possible in the C and to a lesser extent C++ programming languages. These attacks have been described as the single most pernicious problem in computer security today [5]. The explicit static typing of Java makes code easy to understand (and facilitates static analysis), and the dynamic checks ensure unexpected conditions result in predictable behaviour -- which makes Java a joy to use.

To minimize the likelihood of security vulnerabilities caused by programmer error, Java developers should adhere to recommended coding guidelines. Existing publications, such as Effective Java [6], provide excellent guidelines related to Java software design. Others, such as Software Security: Building Security In [7], outline guiding principles for software security. This paper bridges such publications together and includes coverage of additional topics. This document provides a more complete set of security-specific coding guidelines targeted at the Java programming language. These guidelines are of interest to all Java developers, whether they create trusted end-user applications and applets, implement the internals of a security component, or develop shared Java class libraries that perform common programming tasks. Any implementation bug can have serious security ramifications and could appear in any layer of the software stack.

While sections 0 through 3 are generally applicable across different types of software, most of the guidelines in sections 4 through 9 focus on applications that interact with untrusted code (though some guidelines in these sections are still relevant for other situations). Developers should analyze the interactions that occur across an application's trust boundaries and identify the types of data involved to determine which guidelines are relevant. Performing threat modeling and establishing trust boundaries can help to accomplish this (see Guideline 0-4).

These guidelines are intended to help developers build secure software, but they do not focus specifically on software that implements security features. Therefore, topics such as cryptography are not covered in this document (see [9] and [10] for information on using cryptography with Java). While adding features to software can solve some security-related problems, it should not be relied upon to eliminate security defects.

This document has been updated to cover some of the new features included in Java SE 8. However, these guidelines are also applicable to software written for previous versions of Java.