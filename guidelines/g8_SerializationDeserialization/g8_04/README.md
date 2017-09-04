# SERIAL-4: Duplicate the SecurityManager checks enforced in a class during serialization and deserialization
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Prevent an attacker from using serialization or deserialization to bypass the SecurityManager checks enforced in a class. Specifically, if a serializable class enforces a SecurityManager check in its constructors, then enforce that same check in a readObject or readObjectNoData method implementation. Otherwise an instance of the class can be created without any check via deserialization.


        public final class SensitiveClass implements java.io.Serializable {
             public SensitiveClass() {
                // permission needed to instantiate SensitiveClass
                securityManagerCheck();

                // regular logic follows
            }

            // implement readObject to enforce checks
            //   during deserialization
            private void readObject(java.io.ObjectInputStream in) {
                // duplicate check from constructor
                securityManagerCheck();

                // regular logic follows
            }
        }

If a serializable class enables internal state to be modified by a caller (via a public method, for example) and the modification is guarded with a SecurityManager check, then enforce that same check in a readObject method implementation. Otherwise, an attacker can use deserialization to create another instance of an object with modified state without passing the check.

        public final class SecureName implements java.io.Serializable {

            // private internal state
            private String name;

            private static final String DEFAULT = "DEFAULT";

            public SecureName() {
                // initialize name to default value
                name = DEFAULT;
            }

            // allow callers to modify private internal state
            public void setName(String name) {
                if (name!=null ? name.equals(this.name)
                               : (this.name == null)) {
                    // no change - do nothing
                    return;
                } else {
                    // permission needed to modify name
                    securityManagerCheck();

                    inputValidation(name);

                    this.name = name;
                }
            }

            // implement readObject to enforce checks
            //   during deserialization
            private void readObject(java.io.ObjectInputStream in) {
                java.io.ObjectInputStream.GetField fields =
                    in.readFields();
                String name = (String) fields.get("name", DEFAULT);

                // if the deserialized name does not match the default
                //   value normally created at construction time,
                //   duplicate checks


                if (!DEFAULT.equals(name)) {
                    securityManagerCheck();
                    inputValidation(name);
                }
                this.name = name;
            }

        }

If a serializable class enables internal state to be retrieved by a caller and the retrieval is guarded with a SecurityManager check to prevent disclosure of sensitive data, then enforce that same check in a writeObject method implementation. Otherwise, an attacker can serialize an object to bypass the check and access the internal state simply by reading the serialized byte stream.

        public final class SecureValue implements java.io.Serializable {
            // sensitive internal state
            private String value;

            // public method to allow callers to retrieve internal state

            public String getValue() {
                // permission needed to get value
                securityManagerCheck();

                return value;
            }


            // implement writeObject to enforce checks during serialization
            private void writeObject(java.io.ObjectOutputStream out) {
                // duplicate check from getValue()
                securityManagerCheck();
                out.writeObject(value);
            }
        }
