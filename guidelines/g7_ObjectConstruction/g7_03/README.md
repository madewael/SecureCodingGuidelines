# OBJECT-3: Defend against partially initialized instances of non-final classes
When a constructor in a non-final class throws an exception, attackers can attempt to gain access to partially initialized instances of that class. Ensure that a non-final class remains totally unusable until its constructor completes successfully.

From JDK 6 on, construction of a subclassable class can be prevented by throwing an exception before the Object constructor completes. To do this, perform the checks in an expression that is evaluated in a call to this() or super().

        // non-final java.lang.ClassLoader
        public abstract class ClassLoader {
            protected ClassLoader() {
                this(securityManagerCheck());
            }
            private ClassLoader(Void ignored) {
                // ... continue initialization ...
            }
            private static Void securityManagerCheck() {
                SecurityManager security = System.getSecurityManager();
                if (security != null) {
                    security.checkCreateClassLoader();
                }
                return null;
            }
        }

For compatibility with older releases, a potential solution involves the use of an initialized flag. Set the flag as the last operation in a constructor before returning successfully. All methods providing a gateway to sensitive operations must first consult the flag before proceeding:

        public abstract class ClassLoader {

            private volatile boolean initialized;

            protected ClassLoader() {
                // permission needed to create ClassLoader
                securityManagerCheck();
                init();

                // Last action of constructor.
                this.initialized = true;
            }
            protected final Class defineClass(...) {
                checkInitialized();

                // regular logic follows
                ...
            }

            private void checkInitialized() {
                if (!initialized) {
                    throw new SecurityException(
                        "NonFinal not initialized"
                    );
                }
            }
        }

Furthermore, any security-sensitive uses of such classes should check the state of the initialization flag. In the case of ClassLoader construction, it should check that its parent class loader is initialized.

Partially initialized instances of a non-final class can be accessed via a finalizer attack. The attacker overrides the protected finalize method in a subclass and attempts to create a new instance of that subclass. This attempt fails (in the above example, the SecurityManager check in ClassLoader's constructor throws a security exception), but the attacker simply ignores any exception and waits for the virtual machine to perform finalization on the partially initialized object. When that occurs the malicious finalize method implementation is invoked, giving the attacker access to this, a reference to the object being finalized. Although the object is only partially initialized, the attacker can still invoke methods on it, thereby circumventing the SecurityManager check. While the initialized flag does not prevent access to the partially initialized object, it does prevent methods on that object from doing anything useful for the attacker.

Use of an initialized flag, while secure, can be cumbersome. Simply ensuring that all fields in a public non-final class contain a safe value (such as null) until object initialization completes successfully can represent a reasonable alternative in classes that are not security-sensitive.

A more robust, but also more verbose, approach is to use a "pointer to implementation" (or "pimpl"). The core of the class is moved into a non-public class with the interface class forwarding method calls. Any attempts to use the class before it is fully initialized will result in a NullPointerException. This approach is also good for dealing with clone and deserialization attacks.

        public abstract class ClassLoader {

            private final ClassLoaderImpl impl;

            protected ClassLoader() {
                this.impl = new ClassLoaderImpl();
            }
            protected final Class defineClass(...) {
                return impl.defineClass(...);
            }
        }

        /* pp */ class ClassLoaderImpl {
            /* pp */ ClassLoaderImpl() {
                // permission needed to create ClassLoader
                securityManagerCheck();
                init();
            }

            /* pp */ Class defineClass(...) {
                // regular logic follows
                ...
            }
        }

## Others
 - OBJECT-1: [Avoid exposing constructors of sensitive classes](../g71)
 - OBJECT-2: [Prevent the unauthorized construction of sensitive classes](../g72)
 - OBJECT-3: Defend against partially initialized instances of non-final classes
 - OBJECT-4: [Prevent constructors from calling methods that can be overridden](../g74) 
 - OBJECT-5: [Defend against cloning of non-final classes](../g75)
