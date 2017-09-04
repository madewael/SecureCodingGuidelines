# DOS-3: Resource limit checks should not suffer from integer overflow
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
