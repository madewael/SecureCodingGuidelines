# DOS-2: Release resources in all cases
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