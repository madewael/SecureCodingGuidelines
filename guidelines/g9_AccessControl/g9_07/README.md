# ACCESS-7: Understand how thread construction transfers context
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Newly constructed threads are executed with the access control context that was present when the Thread object was constructed. In order to prevent bypassing this context, `void run()` of untrusted objects should not be executed with inappropriate privileges.
