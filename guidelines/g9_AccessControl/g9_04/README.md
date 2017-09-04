# ACCESS-4: Know how to restrict privileges through doPrivileged
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


As permissions are restricted to the intersection of frames, an artificial AccessControlContext representing no (zero) frames implies all permissions. The following three calls to doPrivileged are equivalent:

        private static final AccessControlContext allPermissionsAcc =
            new AccessControlContext(
                new java.security.ProtectionDomain[0]
            );
        void someMethod(PrivilegedAction<Void> action) {
            AccessController.doPrivileged(action, allPermissionsAcc);
            AccessController.doPrivileged(action, null);
            AccessController.doPrivileged(action);
        }

All permissions can be removed using an artificial AccessControlContext context containing a frame of a ProtectionDomain with no permissions:

        private static final java.security.PermissionCollection
            noPermissions = new java.security.Permissions();
        private static final AccessControlContext noPermissionsAcc =
            new AccessControlContext(
                new ProtectionDomain[] {
                    new ProtectionDomain(null, noPermissions)
                }
            );

        void someMethod(PrivilegedAction<Void> action) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    ... context has no permissions ...
                    return null;
                }
            }, noPermissionsAcc);
        }

        +--------------------------------+
        | ActionImpl                     |
        |   .run                         |
        +--------------------------------+
        |                                |
        | noPermissionsAcc               |
        + - - - - - - - - - - - - - - - -+
        | java.security.AccessController |
        |   .doPrivileged                |
        +--------------------------------+
        | SomeClass                      |
        |   .someMethod                  |
        +--------------------------------+

        |  OtherClass                    |
        |    .otherMethod                |
        +--------------------------------+
        |                                |

An intermediate situation is possible where only a limited set of permissions is granted. If the permissions are checked in the current context before being supplied to doPrivileged, permissions may be reduced without the risk of privilege elevation. This enables the use of the principle of least privilege:

        private static void doWithFile(final Runnable task,
                                       String knownPath) {
            Permission perm = new java.io.FilePermission(knownPath,
                                                         "read,write");

            // Ensure context already has permission,
            //   so privileges are not elevate.
            AccessController.checkPermission(perm);

            // Execute task with the single permission only.
            PermissionCollection perms = perm.newPermissionCollection();
            perms.add(perm);
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    task.run();
                    return null;
                }},
                new AccessControlContext(
                    new ProtectionDomain[] {
                        new ProtectionDomain(null, perms)
                    }
                )
            );
        }

