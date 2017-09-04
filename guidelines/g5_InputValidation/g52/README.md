# INPUT-2: Validate output from untrusted objects as input
In general method arguments should be validated but not return values. However, in the case of an upcall (invoking a method of higher level code) the returned value should be validated. Likewise, an object only reachable as an implementation of an upcall need not validate its inputs.

        
# Others
 - [INPUT-1: Validate inputs](../g51)
 - INPUT-2: Validate output from untrusted objects as input
 - [INPUT-3: Define wrappers around native methods](../g53)
