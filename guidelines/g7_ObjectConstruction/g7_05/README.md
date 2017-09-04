# OBJECT-5: Defend against cloning of non-final classes
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


A non-final class may be subclassed by a class that also implements java.lang.Cloneable. The result is that the base class can be unexpectedly cloned, although only for instances created by an adversary. The clone will be a shallow copy. The twins will share referenced objects but have different fields and separate intrinsic locks. The "pointer to implementation" approach detailed in Guideline 7-3 provides a good defense.

