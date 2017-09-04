# FUNDAMENTALS-5: Minimise the number of permission checks
Java is primarily an object-capability language. SecurityManager checks should be considered a last resort. Perform security checks at a few defined points and return an object (a capability) that client code retains so that no further permission checks are required.

## Other Fundamentals
- Guideline 0-0 [Prefer to have obviously no flaws rather than no obvious flaws](../g00)
- Guideline 0-1 [Design APIs to avoid security concerns](../g01)
- Guideline 0-2 [Avoid duplication](../g02)
- Guideline 0-3 [Restrict privileges](../g03)
- Guideline 0-4 [Establish trust boundaries](../g04)
- Guideline 0-5 Minimise the number of permission checks
- Guideline 0-6 [Encapsulate](../g06)
- Guideline 0-7 [Document security-related information](../g07)