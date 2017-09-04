# ACCESS-9: Safely invoke standard APIs that perform tasks using the immediate caller's class loader instance
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


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
