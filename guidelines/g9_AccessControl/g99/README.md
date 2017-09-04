# ACCESS-9: Safely invoke standard APIs that perform tasks using the immediate caller's class loader instance
The following static methods perform tasks using the immediate caller's class loader:

        java.lang.Class.forName
        java.lang.Package.getPackage(s)
        java.lang.Runtime.load
        java.lang.Runtime.loadLibrary
        java.lang.System.load
        java.lang.System.loadLibrary
        java.sql.DriverManager.deregisterDriver		
        java.sql.DriverManager.getConnection
        java.sql.DriverManager.getDriver(s)
        java.util.logging.Logger.getAnonymousLogger
        java.util.logging.Logger.getLogger
        java.util.ResourceBundle.getBundle

Methods such as these that vary their behavior according to the immediate caller's class are considered to be caller-sensitive, and should be annotated in code with the @CallerSensitive annotation [16].

For example, System.loadLibrary("/com/foo/MyLib.so") uses the immediate caller's class loader to find and load the specified library. (Loading libraries enables a caller to make native method invocations.) Do not invoke this method on behalf of untrusted code, since untrusted code may not have the ability to load the same library using its own class loader instance. Do not invoke any of these methods using inputs provided by untrusted code, and do not propagate objects that are returned by these methods back to untrusted code.

## Others
 - ACCESS-1: [Understand how permissions are checked](../g91)
 - ACCESS-2: [Beware of callback methods](../g92)
 - ACCESS-3: [Safely invoke java.security.AccessController.doPrivileged](../g93)
 - ACCESS-4: [Know how to restrict privileges through doPrivileged](../g94)
 - ACCESS-5: [Be careful caching results of potentially privileged operations](../g95)
 - ACCESS-6: [Understand how to transfer context](../g96)
 - ACCESS-7: [Understand how thread construction transfers context](../g97)
 - ACCESS-8: [Safely invoke standard APIs that bypass SecurityManager checks depending on the immediate caller's class loader](../g98)
 - ACCESS-9: Safely invoke standard APIs that perform tasks using the immediate caller's class loader instance
 - ACCESS-10: [Be aware of standard APIs that perform Java language access checks against the immediate caller](../g910)
 - ACCESS-11: [Be aware java.lang.reflect.Method.invoke is ignored for checking the immediate caller](../g911)
 - ACCESS-12: [Avoid using caller-sensitive method names in interface classes](../g912)
 - ACCESS-13: [Avoid returning the results of privileged operations](../g913)
