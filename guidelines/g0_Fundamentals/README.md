# Secure Coding Guidelines
Secure Coding Guidelines for Java SE 

## Links

 - 0 Fundamentals
 - 1 [Denial of Service](../g1_DoS)
 - 2 [Confidential Information](../g2_ConfidentialInformation)
 - 3 [Injection and Inclusion](../g3_InjectionInclusion)
 - 4 [Accessibility and Extensibility](../g4_AccessibilityExtensibility)
 - 5 [Input Validation](../g5_InputValidation)
 - 6 [Mutability](../g6_Mutability)
 - 7 [Object Construction](../g7_ObjectConstruction)
 - 8 [Serialization and Deserialization](../g8_SerializationDeserialization)
 - 9 [Access Control](../g9_AccessControl)

## Fundamentals
The following general principles apply throughout Java security.

- Guideline 0-0 [Prefer to have obviously no flaws rather than no obvious flaws](g00)
- Guideline 0-1 [Design APIs to avoid security concerns](g01)
- Guideline 0-2 [Avoid duplication](g02)
- Guideline 0-3 [Restrict privileges](g03)
- Guideline 0-4 [Establish trust boundaries](g04)
- Guideline 0-5 [Minimise the number of permission checks](g05)
- Guideline 0-6 [Encapsulate](g06)
- Guideline 0-7 [Document security-related information](g07)

# X

Guideline 0-0 / FUNDAMENTALS-0: Prefer to have obviously no flaws rather than no obvious flaws [8]
Creating secure code is not necessarily easy. Despite the unusually robust nature of Java, flaws can slip past with surprising ease. Design and write code that does not require clever logic to see that it is safe. Specifically, follow the guidelines in this document unless there is a very strong reason not to.

Guideline 0-1 / FUNDAMENTALS-1: Design APIs to avoid security concerns
It is better to design APIs with security in mind. Trying to retrofit security into an existing API is more difficult and error prone. For example, making a class final prevents a malicious subclass from adding finalizers, cloning, and overriding random methods (Guideline 4-5). Any use of the SecurityManager highlights an area that should be scrutinized.

Guideline 0-2 / FUNDAMENTALS-2: Avoid duplication
Duplication of code and data causes many problems. Both code and data tend not to be treated consistently when duplicated, e.g., changes may not be applied to all copies.

Guideline 0-3 / FUNDAMENTALS-3: Restrict privileges
Despite best efforts, not all coding flaws will be eliminated even in well reviewed code. However, if the code is operating with reduced privileges, then exploitation of any flaws is likely to be thwarted. The most extreme form of this is known as the principle of least privilege. Using the Java security mechanism this can be implemented statically by restricting permissions through policy files and dynamically with the use of the java.security.AccessController.doPrivileged mechanism (see section 9).

Rich Internet Applications (RIA) can specify their requested permissions via an applet parameter or in the JNLP. A signed jar can also include a manifest attribute that specifies whether it must run in a sandbox or with all permissions (see [11]). If a sandboxed applet or application attempts to execute security-sensitive code, the JRE will throw a security exception. RIAs should follow the principle of least privilege, and should be configured to run with the least amount of necessary permissions. Running a RIA with all permissions should be avoided whenever possible.

Guideline 0-4 / FUNDAMENTALS-4: Establish trust boundaries
In order to ensure that a system is protected, it is necessary to establish trust boundaries. Data that crosses these boundaries should be sanitized and validated before use. Trust boundaries are also necessary to allow security audits to be performed efficiently. Code that ensures integrity of trust boundaries must itself be loaded in such a way that its own integrity is assured.

For instance, a web browser is outside of the system for a web server. Equally, a web server is outside of the system for a web browser. Therefore, web browser and server software should not rely upon the behavior of the other for security.

When auditing trust boundaries, there are some questions that should be kept in mind. Are the code and data used sufficiently trusted? Could a library be replaced with a malicious implementation? Is untrusted configuration data being used? Is code calling with lower privileges adequately protected against?

Guideline 0-5 / FUNDAMENTALS-5: Minimise the number of permission checks
Java is primarily an object-capability language. SecurityManager checks should be considered a last resort. Perform security checks at a few defined points and return an object (a capability) that client code retains so that no further permission checks are required.

Guideline 0-6 / FUNDAMENTALS-6: Encapsulate
Allocate behaviors and provide succinct interfaces. Fields of objects should be private and accessors avoided. The interface of a method, class, package, and module should form a coherent set of behaviors, and no more.

Guideline 0-7 / FUNDAMENTALS-7: Document security-related information
API documentation should cover security-related information such as required permissions, security-related exceptions, caller sensitivity (see Guidelines 9-8 through 9-11 for additional on this topic), and any preconditions or postconditions that are relevant to security. Documenting this information in comments for a tool such as JavaDoc can also help to ensure that it is kept up to date.