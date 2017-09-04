# MUTABLE-9: Make public static fields final
Callers can trivially access and modify public non-final static fields. Neither accesses nor modifications can be guarded against, and newly set values cannot be validated. Fields with subclassable types may be set to objects with malicious implementations. Always declare public static fields as final.

        public class Files {
            public static final String separator = "/";
            public static final String pathSeparator = ":";
        }

If using an interface instead of a class, the modifiers "public static final" can be omitted to improve readability, as the constants are implicitly public, static, and final. Constants can alternatively be defined using an enum declaration.

Protected static fields suffer from the same problem as their public equivalents but also tend to indicate confused design.
