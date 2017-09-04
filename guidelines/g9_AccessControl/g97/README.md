# ACCESS-7: Understand how thread construction transfers context
Newly constructed threads are executed with the access control context that was present when the Thread object was constructed. In order to prevent bypassing this context, `void run()` of untrusted objects should not be executed with inappropriate privileges.



## Others
 - ACCESS-1: [Understand how permissions are checked](../g91)
 - ACCESS-2: [Beware of callback methods](../g92)
 - ACCESS-3: [Safely invoke java.security.AccessController.doPrivileged](../g93)
 - ACCESS-4: [Know how to restrict privileges through doPrivileged](../g94)
 - ACCESS-5: [Be careful caching results of potentially privileged operations](../g95)
 - ACCESS-6: [Understand how to transfer context](../g96)
 - ACCESS-7: Understand how thread construction transfers context
 - ACCESS-8: [Safely invoke standard APIs that bypass SecurityManager checks depending on the immediate caller's class loader](../g98)
 - ACCESS-9: [Safely invoke standard APIs that perform tasks using the immediate caller's class loader instance](../g99)
 - ACCESS-10: [Be aware of standard APIs that perform Java language access checks against the immediate caller](../g910)
 - ACCESS-11: [Be aware java.lang.reflect.Method.invoke is ignored for checking the immediate caller](../g911)
 - ACCESS-12: [Avoid using caller-sensitive method names in interface classes](../g912)
 - ACCESS-13: [Avoid returning the results of privileged operations](../g913)
