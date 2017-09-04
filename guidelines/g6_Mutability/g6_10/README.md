# MUTABLE-10: Ensure public static final field values are constants
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Only immutable values should be stored in public static fields. Many types are mutable and are easily overlooked, in particular arrays and collections. Mutable objects that are stored in a field whose type does not have any mutator methods can be cast back to the runtime type. Enum values should never be mutable.

        import static java.util.Arrays.asList;
        import static java.util.Collections.unmodifiableList;
        ...
        public static final List<String> names = unmodifiableList(asList(
            "Fred", "Jim", "Sheila"
        ));

As per Guideline 6-10, protected static fields suffer from the same problems as their public equivalents.

