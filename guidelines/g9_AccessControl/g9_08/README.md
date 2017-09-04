# ACCESS-8: Safely invoke standard APIs that bypass SecurityManager checks depending on the immediate caller's class loader
Certain standard APIs in the core libraries of the Java runtime enforce SecurityManager checks but allow those checks to be bypassed depending on the immediate caller's class loader. When the java.lang.Class.newInstance method is invoked on a Class object, for example, the immediate caller's class loader is compared to the Class object's class loader. If the caller's class loader is an ancestor of (or the same as) the Class object's class loader, the newInstance method bypasses a SecurityManager check. (See Section 4.3.2 in [1] for information on class loader relationships). Otherwise, the relevant SecurityManager check is enforced.

The difference between this class loader comparison and a SecurityManager check is noteworthy. A SecurityManager check investigates all callers in the current execution chain to ensure each has been granted the requisite security permission. (If AccessController.doPrivileged was invoked in the chain, all callers leading back to the caller of doPrivileged are checked.) In contrast, the class loader comparison only investigates the immediate caller's context (its class loader). This means any caller who invokes Class.newInstance and who has the capability to pass the class loader check--thereby bypassing the SecurityManager--effectively performs the invocation inside an implicit AccessController.doPrivileged action. Because of this subtlety, callers should ensure that they do not inadvertently invoke Class.newInstance on behalf of untrusted code.

        package yy.app;

        class AppClass {
           OtherClass appMethod() throws Exception {
               return OtherClass.class.newInstance();
           }
        }
        +--------------------------------+
        | xx.lib.LibClass                |
        |   .LibClass                    |
        +--------------------------------+
        | java.lang.Class                |
        |   .newInstance                 |
        +--------------------------------+
        | yy.app.AppClass                |<-- AppClass.class.getClassLoader
        |   .appMethod                   |       determines check
        +--------------------------------+

        |                                |
Code has full access to its own class loader and any class loader that is a descendent. In the case of Class.newInstance access to a class loader implies access to classes in restricted packages (e.g., system classes prefixed with "sun.").

In the diagram below, classes loaded by B have access to B and its descendents C, E, and D. Other class loaders, shown in grey strikeout font, are subject to security checks.

        +-------------------------+

        |    bootstrap loader     | <--- null
        +-------------------------+
                 ^              ^
        +------------------+  +---+

        | extension loader |  | A |
        +------------------+  +---+
                 ^
        +------------------+

        |  system loader   | <--- Class.getSystemClassLoader()
        +------------------+
             ^           ^
        +----------+   +---+

        |    B     |   | F |
        +----------+   +---+
          ^      ^       ^

        +---+  +---+   +---+
        | C |  | E |   | G |                
        +---+  +---+   +---+
          ^
        +---+
        | D |
        +---+

The following methods behave similar to Class.newInstance, and potentially bypass SecurityManager checks depending on the immediate caller's class loader:

        java.io.ObjectStreamField.getType
        java.io.ObjectStreamClass.forClass
        java.lang.Class.newInstance
        java.lang.Class.getClassLoader
        java.lang.Class.getClasses
        java.lang.Class.getField(s)
        java.lang.Class.getMethod(s)
        java.lang.Class.getConstructor(s)
        java.lang.Class.getDeclaredClasses
        java.lang.Class.getDeclaredField(s)
        java.lang.Class.getDeclaredMethod(s)
        java.lang.Class.getDeclaredConstructor(s)
        java.lang.Class.getDeclaringClass
        java.lang.Class.getEnclosingMethod
        java.lang.Class.getEnclosingClass
        java.lang.Class.getEnclosingConstructor
        java.lang.ClassLoader.getParent
        java.lang.ClassLoader.getSystemClassLoader
        java.lang.invoke.MethodHandleProxies.asInterfaceInstance
        java.lang.reflect.Proxy.getInvocationHandler
        java.lang.reflect.Proxy.getProxyClass
        java.lang.reflect.Proxy.newProxyInstance
        java.lang.Thread.getContextClassLoader
        javax.sql.rowset.serial.SerialJavaObject.getFields
		
Methods such as these that vary their behavior according to the immediate caller's class are considered to be caller-sensitive, and should be annotated in code with the @CallerSensitive annotation [16].

Refrain from invoking the above methods on Class, ClassLoader, or Thread instances that are received from untrusted code. If the respective instances were acquired safely (or in the case of the static ClassLoader.getSystemClassLoader method), do not invoke the above methods using inputs provided by untrusted code. Also, do not propagate objects that are returned by the above methods back to untrusted code.

