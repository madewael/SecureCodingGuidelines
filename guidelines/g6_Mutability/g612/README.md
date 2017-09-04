# MUTABLE-12: Do not expose modifiable collections
Classes that expose Collections either through public variables or get methods have the potential for side effects, where calling classes can modify contents of the Collection. Developers should consider exposing read-only copies of Collections relating to security authentication or internal state.

While a Collection object reference can be made immutable through the final keyword described in Guideline 6-9, the actual contents of the collection must be made immutable separately through the Collections.unmodifiable... APIs.

        public class Example {
            public static final List SIMPLE = 
                Collections.unmodifiableList(
                    Arrays.asList("first", "second", "...")
                 );
            public static final Map ITEMS;

            static {
                //For complex items requiring construction
                Map temp = new HashMap<>(2);
                temp.put("first", "The first object");
                temp.put("second", "Another object");
                ITEMS = Collections.unmodifiableMap(temp);
            }
            
            private List somethingStateful = new ArrayList<>();
            public List getSomethingStateful() {
                    return  Collections.unmodifiableList(
                                        somethingStateful);
            }
        }


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
 - MUTABLE-10: [Ensure public static final field values are constants](../g610)
 - MUTABLE-11: [Do not expose mutable statics](../g611)
 - MUTABLE-12: Do not expose modifiable collections
