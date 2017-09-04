# MUTABLE-6: Treat passing input to untrusted object as output
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


The above guidelines on output objects apply when passed to untrusted objects. Appropriate copying should be applied.

        private final byte[] data;

        public void writeTo(OutputStream out) throws IOException {
            // Copy (clone) private mutable data before sending.
            out.write(data.clone());
        }

A common but difficult to spot case occurs when an input object is used as a key. A collection's use of equality may well expose other elements to a malicious input object on or after insertion.


