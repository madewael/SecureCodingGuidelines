# INJECT-9: Prevent injection of exceptional floating point values
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Working with floating point numbers requires care when importing those from outside of a trust boundary, as the NaN (not a number) or infinite values can be injected into applications via untrusted input data, for example by conversion of (untrusted) Strings converted by the Double.valueOf method. Unfortunately the processing of exceptional values is typically not immediately noticed without introducing sanitization code. Moreover, passing an exceptional value to an operation propagates the exceptional numeric state to the operation result.

Both positive and negative infinitity values are possible outcomes of a floating point operation [2], when results become too high or too low to be representable by the memory area that backs a primitive floating point value. Also, the exceptional value NaN can result from dividing 0.0 by 0.0 or subtracting infinity from infinity.

The results of casting propagated exceptional floating point numbers to short, integer and long primitive values need special care, too. This is because an integer conversion of a NaN value will result in a 0, and a positive infinite value is transformed to Integer.MAX_VALUE (or Integer.MIN_VALUE for negative infinity), which may not be correct in certain use cases.

There are distinct application scenarios where these exceptional values are expected, such as scientific data analysis which relies on numeric processing. However, it is advised that the result values be contained for that purpose in the local component. This can be achieved by sanitizing any floating point results before passing them back to the generic parts of an application.

As mentioned before, the programmer may wish to include sanitization code for these exceptional values when working with floating point numbers, especially if related to authorization or authentication decisions, or forwarding floating point values to JNI. The Double and Float classes help with sanitization by providing the isNan and isInfinite methods. Also keep in mind that comparing instances of Double.NaN via the equality operator always results to be false, which may cause lookup problems in maps or collections when using the equality operator on a wrapped double field within the equals method in a class definition.

A typical code pattern that can block further processing of unexpected floating point numbers is shown in the following example snippet.

        if (Double.isNaN(untrusted_double_value)) {
            // specific action for non-number case
        }

        if (Double.isInfinite(untrusted_double_value)){
            // specific action for infinite case
        }

        // normal processing starts here

