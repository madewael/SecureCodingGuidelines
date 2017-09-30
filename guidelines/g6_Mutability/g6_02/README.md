# MUTABLE-2: Create copies of mutable output values
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


If a method returns a reference to an internal mutable object, then client code may modify the internal state of the instance. Unless the intention is to share state, copy mutable objects and return the copy.

To create a copy of a trusted mutable object, call a copy constructor or the clone method:

        public class CopyOutput {
            private final java.util.Date date;
            ...
            public java.util.Date getDate() {
                return (java.util.Date)date.clone();
            }
        }


## Attack Using Very Simple Java Code

![Author](https://img.shields.io/badge/Author-Mattias.De.Wael-blue.svg)
![Date](https://img.shields.io/badge/Date-20170930-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

The class ```be.howest.ti.secure.development.g6.g02.Person``` keeps a ```String``` field for a name and a ```Date``` field for the birthday of the person. The method ```isOfAge``` checks if a person is 18 years or older.

The field is make ```final``` because we do not want the birthday to change. However, because the getter ```getBirthday``` returns a mutable ```Date``` object, the birthday of a person can easily be updated using sime Java code only.

This is a vulnerability because such changes are not anticipated, which may result in unexpected behaviour; and because such changes can give the adversary access to code that otherwise would not have been executed. This is an easy to exploit vulnerability. 

