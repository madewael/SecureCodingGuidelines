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
