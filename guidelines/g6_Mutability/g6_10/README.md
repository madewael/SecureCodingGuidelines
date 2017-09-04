# MUTABLE-10: Ensure public static final field values are constants
Only immutable values should be stored in public static fields. Many types are mutable and are easily overlooked, in particular arrays and collections. Mutable objects that are stored in a field whose type does not have any mutator methods can be cast back to the runtime type. Enum values should never be mutable.

        import static java.util.Arrays.asList;
        import static java.util.Collections.unmodifiableList;
        ...
        public static final List<String> names = unmodifiableList(asList(
            "Fred", "Jim", "Sheila"
        ));

As per Guideline 6-10, protected static fields suffer from the same problems as their public equivalents.


## Others
 - MUTABLE-1: [Prefer immutability for value types](../g61)
 - MUTABLE-2: [Create copies of mutable output values](../g62)
 - MUTABLE-3: [Create safe copies of mutable and subclassable input values](../g63)
 - MUTABLE-4: [Support copy functionality for a mutable class](../g64)
 - MUTABLE-5: [Do not trust identity equality when overridable on input reference objects](../g65)
 - MUTABLE-6: [Treat passing input to untrusted object as output](../g66)
 - MUTABLE-7: [Treat output from untrusted object as input](../g67)
 - MUTABLE-8: [Define wrapper methods around modifiable](../g68)
 - MUTABLE-9: [Make public static fields final](../g69)
 - MUTABLE-10: Ensure public static final field values are constants
 - MUTABLE-11: [Do not expose mutable statics](../g611)
 - MUTABLE-12: [Do not expose modifiable collections](../g612)
