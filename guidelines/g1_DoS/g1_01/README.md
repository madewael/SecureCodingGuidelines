# Beware of activities that may use disproportionate resources


![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Examples of attacks include:

Requesting a large image size for vector graphics. For instance, SVG and font files.
Integer overflow errors can cause sanity checking of sizes to fail.
An object graph constructed by parsing a text or binary stream may have memory requirements many times that of the original data.
"Zip bombs" whereby a short file is very highly compressed. For instance, ZIPs, GIFs and gzip encoded HTTP contents. When decompressing files it is better to set limits on the decompressed data size rather than relying upon compressed size or meta-data.
"Billion laughs attack" whereby XML entity expansion causes an XML document to grow dramatically during parsing. Set the XMLConstants.FEATURE_SECURE_PROCESSING feature to enforce reasonable limits.
Causing many keys to be inserted into a hash table with the same hash code, turning an algorithm of around O(n) into O(n2).
Regular expressions may exhibit catastrophic backtracking.
XPath expressions may consume arbitrary amounts of processor time.
Java deserialization and Java Beans XML deserialization of malicious data may result in unbounded memory or CPU usage.
Detailed logging of unusual behavior may result in excessive output to log files.
Infinite loops can be caused by parsing some corner case data. Ensure that each iteration of a loop makes some progress.