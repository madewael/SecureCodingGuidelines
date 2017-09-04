# MUTABLE-5: Do not trust identity equality when overridable on input reference objects
Overridable methods may not behave as expected.

For instance, when expecting identity equality behavior, Object.equals may be overridden to return true for different objects. In particular when used as a key in a Map, an object may be able to pass itself off as a different object that it should not have access to.

If possible, use a collection implementation that enforces identity equality, such as IdentityHashMap.

        private final Map<Window,Extra> extras = new IdentityHashMap<>();

        public void op(Window window) {
            // Window.equals may be overridden,
            // but safe as we are using IdentityHashMap
            Extra extra = extras.get(window);
        }

If such a collection is not available, use a package private key which an adversary does not have access to.

        public class Window {
            /* pp */ class PrivateKey {
                // Optionally, refer to real object.
                /* pp */ Window getWindow() {
                    return Window.this;
                }
            }
            /* pp */ final PrivateKey privateKey = new PrivateKey();

            private final Map<Window.PrivateKey,Extra> extras =
                                             new WeakHashMap<>();
            ...
        }

        public class WindowOps {
            public void op(Window window) {
                // Window.equals may be overridden,
                // but safe as we don't use it.
                Extra extra = extras.get(window.privateKey);
                ...
            }
        }


