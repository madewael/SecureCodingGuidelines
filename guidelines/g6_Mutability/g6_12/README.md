# MUTABLE-12: Do not expose modifiable collections
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


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


