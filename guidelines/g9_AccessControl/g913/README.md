# ACCESS-13: Avoid returning the results of privileged operations
Care should be taken when designing lambdas which are to be returned to untrusted code; especially ones that include security-related operations. Without proper precautions, e.g., input and output validation, untrusted code may be able to leverage the privileges of a lambda inappropriately.

Similarly, care should be taken before returning Method objects, MethodHandle objects, and MethodHandles.Lookup objects to untrusted code. These objects have checks for language access and/or privileges inherent in their creation and incautious distribution may allow untrusted code to bypass private / protected access restrictions as well as restricted package access. If one returns a Method or MethodHandle object that an untrusted user would not normally have access to, then a careful analysis is required to ensure that the object does not convey undesirable capabilities. Similarly, MethodHandles.Lookup objects have different capabilities depending on who created them.  It is important to understand the access granted by any such object before it is returned to untrusted code.

## Others
 - ACCESS-1: [Understand how permissions are checked](../g91)
 - ACCESS-2: [Beware of callback methods](../g92)
 - ACCESS-3: [Safely invoke java.security.AccessController.doPrivileged](../g93)
 - ACCESS-4: [Know how to restrict privileges through doPrivileged](../g94)
 - ACCESS-5: [Be careful caching results of potentially privileged operations](../g95)
 - ACCESS-6: [Understand how to transfer context](../g96)
 - ACCESS-7: [Understand how thread construction transfers context](../g97)
 - ACCESS-8: [Safely invoke standard APIs that bypass SecurityManager checks depending on the immediate caller's class loader](../g98)
 - ACCESS-9: [Safely invoke standard APIs that perform tasks using the immediate caller's class loader instance](../g99)
 - ACCESS-10: [Be aware of standard APIs that perform Java language access checks against the immediate caller](../g910)
 - ACCESS-11: [Be aware java.lang.reflect.Method.invoke is ignored for checking the immediate caller](../g911)
 - ACCESS-12: [Avoid using caller-sensitive method names in interface classes](../g912)
 - ACCESS-13: Avoid returning the results of privileged operations
