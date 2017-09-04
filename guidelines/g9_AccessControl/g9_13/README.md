# ACCESS-13: Avoid returning the results of privileged operations
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Care should be taken when designing lambdas which are to be returned to untrusted code; especially ones that include security-related operations. Without proper precautions, e.g., input and output validation, untrusted code may be able to leverage the privileges of a lambda inappropriately.

Similarly, care should be taken before returning Method objects, MethodHandle objects, and MethodHandles.Lookup objects to untrusted code. These objects have checks for language access and/or privileges inherent in their creation and incautious distribution may allow untrusted code to bypass private / protected access restrictions as well as restricted package access. If one returns a Method or MethodHandle object that an untrusted user would not normally have access to, then a careful analysis is required to ensure that the object does not convey undesirable capabilities. Similarly, MethodHandles.Lookup objects have different capabilities depending on who created them.  It is important to understand the access granted by any such object before it is returned to untrusted code.
