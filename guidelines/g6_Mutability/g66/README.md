# MUTABLE-6: Treat passing input to untrusted object as output
The above guidelines on output objects apply when passed to untrusted objects. Appropriate copying should be applied.

        private final byte[] data;

        public void writeTo(OutputStream out) throws IOException {
            // Copy (clone) private mutable data before sending.
            out.write(data.clone());
        }

A common but difficult to spot case occurs when an input object is used as a key. A collection's use of equality may well expose other elements to a malicious input object on or after insertion.


## Others
 - MUTABLE-1: [Prefer immutability for value types](../g61)
 - MUTABLE-2: [Create copies of mutable output values](../g62)
 - MUTABLE-3: [Create safe copies of mutable and subclassable input values](../g63)
 - MUTABLE-4: [Support copy functionality for a mutable class](../g64)
 - MUTABLE-5: [Do not trust identity equality when overridable on input reference objects](../g65)
 - MUTABLE-6: Treat passing input to untrusted object as output
 - MUTABLE-7: [Treat output from untrusted object as input](../g67)
 - MUTABLE-8: [Define wrapper methods around modifiable](../g68)
 - MUTABLE-9: [Make public static fields final](../g69)
 - MUTABLE-10: [Ensure public static final field values are constants](../g610)
 - MUTABLE-11: [Do not expose mutable statics](../g611)
 - MUTABLE-12: [Do not expose modifiable collections](../g612)
