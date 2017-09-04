# ACCESS-1: Understand how permissions are checked
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


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

