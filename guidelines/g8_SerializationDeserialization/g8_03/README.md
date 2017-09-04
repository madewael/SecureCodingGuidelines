# SERIAL-3: View deserialization the same as object construction
Deserialization creates a new instance of a class without invoking any constructor on that class. Therefore, deserialization should be designed to behave like normal construction.

Default deserialization and ObjectInputStream.defaultReadObject can assign arbitrary objects to non-transient fields and does not necessarily return. Use ObjectInputStream.readFields instead to insert copying before assignment to fields. Or, if possible, don't make sensitive classes serializable.

        public final class ByteString implements java.io.Serializable {
            private static final long serialVersionUID = 1L;
            private byte[] data;
            public ByteString(byte[] data) {
                this.data = data.clone(); // Make copy before assignment.
            }
            private void readObject(
                java.io.ObjectInputStream in
            ) throws java.io.IOException, ClassNotFoundException {
                java.io.ObjectInputStreadm.GetField fields =
                    in.readFields();
                this.data = ((byte[])fields.get("data")).clone();
            }
            ...
        }

Perform the same input validation checks in a readObject method implementation as those performed in a constructor. Likewise, assign default values that are consistent with those assigned in a constructor to all fields, including transient fields, which are not explicitly set during deserialization.

In addition create copies of deserialized mutable objects before assigning them to internal fields in a readObject implementation. This defends against hostile code deserializing byte streams that are specially crafted to give the attacker references to mutable objects inside the deserialized container object.

        public final class Nonnegative implements java.io.Serializable {
            private static final long serialVersionUID = 1L;
            private int value;
            public Nonnegative(int value) {
                // Make check before assignment.
                this.data = nonnegative(value);
            }
            private static int nonnegative(int value) {
                if (value < 0) {
                    throw new IllegalArgumentException(value +
                                                       " is negative");
                }
                return value;
            }
            private void readObject(
                java.io.ObjectInputStream in
            ) throws java.io.IOException, ClassNotFoundException {
                java.io.ObjectInputStreadm.GetField fields =
                    in.readFields();
                this.value = nonnegative(field.get(value, 0));
            }
            ...
        }

Attackers can also craft hostile streams in an attempt to exploit partially initialized (deserialized) objects. Ensure a serializable class remains totally unusable until deserialization completes successfully. For example, use an initialized flag. Declare the flag as a private transient field and only set it in a readObject or readObjectNoData method (and in constructors) just prior to returning successfully. All public and protected methods in the class must consult the initialized flag before proceeding with their normal logic. As discussed earlier, use of an initialized flag can be cumbersome. Simply ensuring that all fields contain a safe value (such as null) until deserialization successfully completes can represent a reasonable alternative.


## Others

 - SERIAL-1: [Avoid serialization for security-sensitive classes](../g81)
 - SERIAL-2: [Guard sensitive data during serialization](../g82)
 - SERIAL-3: View deserialization the same as object construction
 - SERIAL-4: [Duplicate the SecurityManager checks enforced in a class during serialization and deserialization](../g84)
 - SERIAL-5: [Understand the security permissions given to serialization and deserialization](../g85)
