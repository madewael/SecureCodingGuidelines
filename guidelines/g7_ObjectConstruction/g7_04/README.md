# OBJECT-4: Prevent constructors from calling methods that can be overridden
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Constructors that call overridable methods give attackers a reference to this (the object being constructed) before the object has been fully initialized. Likewise, clone, readObject, or readObjectNoData methods that call overridable methods may do the same. The readObject methods will usually call java.io.ObjectInputStream.defaultReadObject, which is an overridable method.

