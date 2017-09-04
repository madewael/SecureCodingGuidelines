# FUNDAMENTALS-3: Restrict privileges
Despite best efforts, not all coding flaws will be eliminated even in well reviewed code. However, if the code is operating with reduced privileges, then exploitation of any flaws is likely to be thwarted. The most extreme form of this is known as the principle of least privilege. Using the Java security mechanism this can be implemented statically by restricting permissions through policy files and dynamically with the use of the java.security.AccessController.doPrivileged mechanism (see section 9).

Rich Internet Applications (RIA) can specify their requested permissions via an applet parameter or in the JNLP. A signed jar can also include a manifest attribute that specifies whether it must run in a sandbox or with all permissions (see [11]). If a sandboxed applet or application attempts to execute security-sensitive code, the JRE will throw a security exception. RIAs should follow the principle of least privilege, and should be configured to run with the least amount of necessary permissions. Running a RIA with all permissions should be avoided whenever possible.

## Other Fundamentals
- Guideline 0-0 [Prefer to have obviously no flaws rather than no obvious flaws](../g00)
- Guideline 0-1 [Design APIs to avoid security concerns](../g01)
- Guideline 0-2 [Avoid duplication](../g02)
- Guideline 0-3 Restrict privileges
- Guideline 0-4 [Establish trust boundaries](../g04)
- Guideline 0-5 [Minimise the number of permission checks](../g05)
- Guideline 0-6 [Encapsulate](../g06)
- Guideline 0-7 [Document security-related information](../g07)