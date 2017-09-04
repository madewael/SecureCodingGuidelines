# Secure Coding Guidelines

1 Denial of Service
Input into a system should be checked so that it will not cause excessive resource consumption disproportionate to that used to request the service. Common affected resources are CPU cycles, memory, disk space, and file descriptors.

In rare cases it may not be practical to ensure that the input is reasonable. It may be necessary to carefully combine the resource checking with the logic of processing the data. For client systems it is generally left to the user to close the application if it is using excessive resources. Therefore, only attacks resulting in persistent DoS, such as wasting significant disk space, need be defended against. Server systems should be robust against external attacks.

Guideline 1-1 / DOS-1: Beware of activities that may use disproportionate resources
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
Guideline 1-2 / DOS-2: Release resources in all cases
Some objects, such as open files, locks and manually allocated memory, behave as resources which require every acquire operation to be paired with a definite release. It is easy to overlook the vast possibilities for executions paths when exceptions are thrown. Resources should always be released promptly no matter what.

Even experienced programmers often handle resources incorrectly. In order to reduce errors, duplication should be minimized and resource handling concerns should be separated. The Execute Around Method pattern provides an excellent way of extracting the paired acquire and release operations. The pattern can be used concisely using the Java SE 8 lambda feature.

        long sum = readFileBuffered(InputStream in -> {
            long current = 0;
            for (;;) {
                int b = in.read();
                if (b == -1) {
                    return current;
                }
                current += b;
            }
        });

The try-with-resource syntax introduced in Java SE 7 automatically handles the release of many resource types.

        public  R readFileBuffered(
            InputStreamHandler handler
        ) throws IOException {
            try (final InputStream in = Files.newInputStream(path)) {
                handler.handle(new BufferedInputStream(in));
            }
        }
		
For resources without support for the enhanced feature, use the standard resource acquisition and release. Attempts to rearrange this idiom typically result in errors and makes the code significantly harder to follow.

        public  R locked(Action action) {
            lock.lock();
            try {
                return action.run();
            } finally {
                lock.unlock();
            }
        }

Ensure that any output buffers are flushed in the case that output was otherwise successful. If the flush fails, the code should exit via an exception.

        public void writeFile(
            OutputStreamHandler handler
        ) throws IOException {
            try (final OutputStream rawOut = Files.newOutputStream(path)) {
                final BufferedOutputStream out =
                    new BufferedOutputStream(rawOut);
                handler.handle(out);
                out.flush();
            }
        }

Some decorators of resources may themselves be resources that require correct release. For instance, in the current OpenJDK implementation compression-related streams are natively implemented using the C heap for buffer storage. Care must be taken that both resources are released in all circumstances.

        public void bufferedWriteGzipFile(
            OutputStreamHandler handler
        ) throws IOException {
            try (
                final OutputStream rawOut = Files.newOutputStream(path);
                final OutputStream compressedOut = 
                                        new GzipOutputStream(rawOut);
            ) {
                final BufferedOutputStream out =
                    new BufferedOutputStream(compressedOut);
                handler.handle(out);
                out.flush();
            }
        }

Guideline 1-3 / DOS-3: Resource limit checks should not suffer from integer overflow
The Java language provides bounds checking on arrays which mitigates the vast majority of integer overflow attacks. However, some operations on primitive integral types silently overflow. Therefore, take care when checking resource limits. This is particularly important on persistent resources, such as disk space, where a reboot may not clear the problem.

Some checking can be rearranged so as to avoid overflow. With large values, current + max could overflow to a negative value, which would always be less than max.

        private void checkGrowBy(long extra) {
            if (extra < 0 || current > max - extra) {
                  throw new IllegalArgumentException();
            }
        }

If performance is not a particular issue, a verbose approach is to use arbitrary sized integers.

        private void checkGrowBy(long extra) {
            BigInteger currentBig = BigInteger.valueOf(current);
            BigInteger maxBig     = BigInteger.valueOf(max    );
            BigInteger extraBig   = BigInteger.valueOf(extra  );

            if (extra < 0 ||
                currentBig.add(extraBig).compareTo(maxBig) > 0) {
                  throw new IllegalArgumentException();
            }
        }

A peculiarity of two's complement integer arithmetic is that the minimum negative value does not have a matching positive value of the same magnitude. So, Integer.MIN_VALUE == -Integer.MIN_VALUE, Integer.MIN_VALUE == Math.abs(Integer.MIN_VALUE) and, for integer a, a < 0 does not imply -a > 0. The same edge case occurs for Long.MIN_VALUE.

As of Java SE 8, the java.lang.Math class also contains methods for various operations (e.g. addExact, multiplyExact, decrementExact, etc.) that throw an ArithmeticException if the result overflows the given type.

# X

1 Denial of Service
Input into a system should be checked so that it will not cause excessive resource consumption disproportionate to that used to request the service. Common affected resources are CPU cycles, memory, disk space, and file descriptors.

In rare cases it may not be practical to ensure that the input is reasonable. It may be necessary to carefully combine the resource checking with the logic of processing the data. For client systems it is generally left to the user to close the application if it is using excessive resources. Therefore, only attacks resulting in persistent DoS, such as wasting significant disk space, need be defended against. Server systems should be robust against external attacks.

 - DOS-1: [Beware of activities that may use disproportionate resources](g11)
 - DOS-2: [Release resources in all cases](g12)
 - DOS-3: [Resource limit checks should not suffer from integer overflow](g13)




## Other Guidelines
 - 0 [Fundamentals](../g0_Fundamentals)
 - 1 Denial of Service
 - 2 [Confidential Information](../g2_ConfidentialInformation)
 - 3 [Injection and Inclusion](../g3_InjectionInclusion)
 - 4 [Accessibility and Extensibility](../g4_AccessibilityExtensibility)
 - 5 [Input Validation](../g5_InputValidation)
 - 6 [Mutability](../g6_Mutability)
 - 7 [Object Construction](../g7_ObjectConstruction)
 - 8 [Serialization and Deserialization](../g8_SerializationDeserialization)
 - 9 [Access Control](../g9_AccessControl)