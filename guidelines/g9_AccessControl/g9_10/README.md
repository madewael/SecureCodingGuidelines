# ACCESS-10: Be aware of standard APIs that perform Java language access checks against the immediate caller
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


When an object accesses fields or methods of another object, the JVM performs access control checks to assert the valid visiblity of the target method or field. For example, it prevents objects from invoking private methods in other objects.

Code may also call standard APIs (primarily in the java.lang.reflect package) to reflectively access fields or methods in another object. The following reflection-based APIs mirror the language checks that are enforced by the virtual machine:


        java.lang.Class.newInstance
        java.lang.invoke.MethodHandles.lookup 
        java.lang.reflect.Constructor.newInstance
        java.lang.reflect.Field.get*
        java.lang.reflect.Field.set*
        java.lang.reflect.Method.invoke
        java.util.concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater
        java.util.concurrent.atomic.AtomicLongFieldUpdater.newUpdater
        java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater

Methods such as these that vary their behavior according to the immediate caller's class are considered to be caller-sensitive, and should be annotated in code with the @CallerSensitive annotation [16].

Language checks are performed solely against the immediate caller, not against each caller in the execution sequence. Because the immediate caller may have capabilities that other code lacks (it may belong to a particular package and therefore have access to its package-private members), do not invoke the above APIs on behalf of untrusted code. Specifically, do not invoke the above methods on Class, Constructor, Field, or Method instances that are received from untrusted code. If the respective instances were acquired safely, do not invoke the above methods using inputs that are provided by untrusted code. Also, do not propagate objects that are returned by the above methods back to untrusted code.