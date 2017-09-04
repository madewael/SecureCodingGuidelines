# ACCESS-5: Be careful caching results of potentially privileged operations
A cached result must never be passed to a context that does not have the relevant permissions to generate it. Therefore, ensure that the result is generated in a context that has no more permissions than any context it is returned to. Because calculation of privileges may contain errors, use the AccessController API to enforce the constraint.

        private static final Map cache;

        public static Thing getThing(String key) {
            // Try cache.
            CacheEntry entry = cache.get(key);
            if (entry != null) {
                // Ensure we have required permissions before returning
                //   cached result.
                AccessController.checkPermission(entry.getPermission());
                return entry.getValue();
            }

            // Ensure we do not elevate privileges (per Guideline 9-2).
            Permission perm = getPermission(key);
            AccessController.checkPermission(perm);

            // Create new value with exact privileges.
            PermissionCollection perms = perm.newPermissionCollection();
            perms.add(perm);
            Thing value = AccessController.doPrivileged(
                new PrivilegedAction<Thing>() { public Thing run() {
                    return createThing(key);
                }},
                new AccessControlContext(
                    new ProtectionDomain[] {
                        new ProtectionDomain(null, perms)
                    }
                )
            );
            cache.put(key, new CacheEntry(value, perm));

            return value;
        }


## Others
 - ACCESS-1: [Understand how permissions are checked](../g91)
 - ACCESS-2: [Beware of callback methods](../g92)
 - ACCESS-3: [Safely invoke java.security.AccessController.doPrivileged](../g93)
 - ACCESS-4: [Know how to restrict privileges through doPrivileged](../g94)
 - ACCESS-5: Be careful caching results of potentially privileged operations
 - ACCESS-6: [Understand how to transfer context](../g96)
 - ACCESS-7: [Understand how thread construction transfers context](../g97)
 - ACCESS-8: [Safely invoke standard APIs that bypass SecurityManager checks depending on the immediate caller's class loader](../g98)
 - ACCESS-9: [Safely invoke standard APIs that perform tasks using the immediate caller's class loader instance](../g99)
 - ACCESS-10: [Be aware of standard APIs that perform Java language access checks against the immediate caller](../g910)
 - ACCESS-11: [Be aware java.lang.reflect.Method.invoke is ignored for checking the immediate caller](../g911)
 - ACCESS-12: [Avoid using caller-sensitive method names in interface classes](../g912)
 - ACCESS-13: [Avoid returning the results of privileged operations](../g913)
