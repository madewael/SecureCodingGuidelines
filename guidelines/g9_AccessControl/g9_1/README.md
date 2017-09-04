# ACCESS-1: Understand how permissions are checked
The standard security check ensures that each frame in the call stack has the required permission. That is, the current permissions in force is the intersection of the permissions of each frame in the current access control context. If any frame does not have a permission, no matter where it lies in the stack, then the current context does not have that permission.

Consider an application that indirectly uses secure operations through a library.

        package xx.lib;

        public class LibClass {
            private static final String OPTIONS = "xx.lib.options";

            public static String getOptions() {
                // checked by SecurityManager
                return System.getProperty(OPTIONS);
            }
        }

        package yy.app;

        class AppClass {
            public static void main(String[] args) {
                System.out.println(
                    xx.lib.LibClass.getOptions()
                );
            }
        }

When the permission check is performed, the call stack will be as illustrated below.

        +--------------------------------+
        | java.security.AccessController |
        |   .checkPermission(Permission) |
        +--------------------------------+
        | java.lang.SecurityManager      |
        |   .checkPermission(Permission) |
        +--------------------------------+
        | java.lang.SecurityManager      |
        |   .checkPropertyAccess(String) |
        +--------------------------------+
        | java.lang.System               |
        |   .getProperty(String)         |
        +--------------------------------+
        | xx.lib.LibClass                |
        |   .getOptions()                |
        +--------------------------------+
        | yy.app.AppClass                |
        |   .main(String[])              |
        +--------------------------------+
In the above example, if the AppClass frame does not have permission to read a file but the LibClass frame does, then a security exception is still thrown. It does not matter that the immediate caller of the privileged operation is fully privileged, but that there is unprivileged code on the stack somewhere.

For library code to appear transparent to applications with respect to privileges, libraries should be granted permissions at least as generous as the application code that it is used with. For this reason, almost all the code shipped in the JDK and extensions is fully privileged. It is therefore important that there be at least one frame with the application's permissions on the stack whenever a library executes security checked operations on behalf of application code.

## Others
 - ACCESS-1: Understand how permissions are checked
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
 - ACCESS-13: [Avoid returning the results of privileged operations](../g913)
