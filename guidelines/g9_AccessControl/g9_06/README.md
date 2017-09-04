# ACCESS-6: Understand how to transfer context
It is often useful to store an access control context for later use. For example, one may decide it is appropriate to provide access to callback instances that perform privileged operations, but invoke callback methods in the context that the callback object was registered. The context may be restored later on in the same thread or in a different thread. A particular context may be restored multiple times and even after the original thread has exited.

AccessController.getContext returns the current context. The two-argument forms of AccessController.doPrivileged can then replace the current context with the stored context for the duration of an action.

        package xx.lib;

        public class Reactor {
            public void addHandler(Handler handler) {
                handlers.add(new HandlerEntry(
                        handler, AccessController.getContext()
                ));
            }
            private void fire(final Handler handler,
                              AccessControlContext acc) {
                if (acc == null) {
                    throw new SecurityException(
                                  "Missing AccessControlContext");
                }
                AccessController.doPrivileged(
                    new PrivilegedAction<Void>() {
                        public Void run() {
                            handler.handle();
                            return null;
                        }
                    }, acc);
             }
             ...
        }


UML

                                             +--------------------------------+
                                             | xx.lib.FileHandler             |
                                             |   handle()                     |
                                             +--------------------------------+
                                             | xx.lib.Reactor.(anonymous)     |
                                             |   run()                        |
    +--------------------------------+ \     +--------------------------------+
    | java.security.AccessController |  `    |                                |
    |   .getContext()                |  +--> | acc                            |
    +--------------------------------+  |    + - - - - - - - - - - - - - - - -+
    | xx.lib.Reactor                 |  |    | java.security.AccessController |
    |   .addHandler(Handler)         |  |    |   .doPrivileged(handler, acc)  |
    +--------------------------------+  |    +--------------------------------+
    | yy.app.App                     |  |    | xx.lib.Reactor                 |
    |   .main(String[] args)         |  ,    |   .fire                        |
    +--------------------------------+ /     +--------------------------------+
                                             | xx.lib.Reactor                 |
    
                                             | .run                           |
                                             +--------------------------------+
                                             |                                |
