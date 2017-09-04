# ACCESS-12: Avoid using caller-sensitive method names in interface classes
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


When designing an interface class, one should avoid using methods with the same name and signature of caller-sensitive methods, such as those listed in Guidelines 9-8, 9-9, and 9-10. In particular, avoid calling these from default methods on an interface class.
