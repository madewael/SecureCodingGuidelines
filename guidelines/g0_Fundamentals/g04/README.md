# FUNDAMENTALS-4: Establish trust boundaries
In order to ensure that a system is protected, it is necessary to establish trust boundaries. Data that crosses these boundaries should be sanitized and validated before use. Trust boundaries are also necessary to allow security audits to be performed efficiently. Code that ensures integrity of trust boundaries must itself be loaded in such a way that its own integrity is assured.

For instance, a web browser is outside of the system for a web server. Equally, a web server is outside of the system for a web browser. Therefore, web browser and server software should not rely upon the behavior of the other for security.

When auditing trust boundaries, there are some questions that should be kept in mind. Are the code and data used sufficiently trusted? Could a library be replaced with a malicious implementation? Is untrusted configuration data being used? Is code calling with lower privileges adequately protected against?

## Other Fundamentals
- Guideline 0-0 [Prefer to have obviously no flaws rather than no obvious flaws](../g00)
- Guideline 0-1 [Design APIs to avoid security concerns](../g01)
- Guideline 0-2 [Avoid duplication](../g02)
- Guideline 0-3 [Restrict privileges](../g03)
- Guideline 0-4 Establish trust boundaries
- Guideline 0-5 [Minimise the number of permission checks](../g05)
- Guideline 0-6 [Encapsulate](../g06)
- Guideline 0-7 [Document security-related information](../g07)