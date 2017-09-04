# CONFIDENTIAL-3: Consider purging highly sensitive from memory after use
To narrow the window when highly sensitive information may appear in core dumps, debugging, and confidentiality attacks, it may be appropriate to zero memory containing the data immediately after use rather than waiting for the garbage collection mechanism.

However, doing so does have negative consequences. Code quality will be compromised with extra complications and mutable data structures. Libraries may make copies, leaving the data in memory anyway. The operation of the virtual machine and operating system may leave copies of the data in memory or even on disk.

## Others
 - CONFIDENTIAL-1: [Purge sensitive information from exceptions](../g21)
 - CONFIDENTIAL-2: [Do not log highly sensitive information](../g22)
 - CONFIDENTIAL-3: Consider purging highly sensitive from memory after use