# INPUT-2: Validate output from untrusted objects as input
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


In general method arguments should be validated but not return values. However, in the case of an upcall (invoking a method of higher level code) the returned value should be validated. Likewise, an object only reachable as an implementation of an upcall need not validate its inputs.
