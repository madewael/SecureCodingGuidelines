# ACCESS-11: Be aware java.lang.reflect.Method.invoke is ignored for checking the immediate caller
Consider:

        package xx.lib;

        class LibClass {
            void libMethod(
                PrivilegedAction action
            ) throws Exception {
                Method doPrivilegedMethod =
                    AccessController.class.getMethod(
                        "doPrivileged", PrivilegedAction.class
                    );
                doPrivilegedMethod.invoke(null, action);
            }
        }

If Method.invoke was taken as the immediate caller, then the action would be performed with all permissions. So, for the methods discussed in Guidelines 9-8 through 9-10, the Method.invoke implementation is ignored when determining the immediate caller.

        +--------------------------------+
        | action                         |
        |   .run                         |
        +--------------------------------+
        | java.security.AccessController |
        |   .doPrivileged                |
        +--------------------------------+

        | java.lang.reflect.Method       |
        |    .invoke                     |
        +--------------------------------+
        | xx.lib.LibClass                | <--- Effective caller
        |   .libMethod                   |

        +--------------------------------+
        |                                |
Therefore, avoid Method.invoke.
