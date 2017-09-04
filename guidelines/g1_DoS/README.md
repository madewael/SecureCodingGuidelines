# Denial of Service
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Input into a system should be checked so that it will not cause excessive resource consumption disproportionate to that used to request the service. Common affected resources are CPU cycles, memory, disk space, and file descriptors.

In rare cases it may not be practical to ensure that the input is reasonable. It may be necessary to carefully combine the resource checking with the logic of processing the data. For client systems it is generally left to the user to close the application if it is using excessive resources. Therefore, only attacks resulting in persistent DoS, such as wasting significant disk space, need be defended against. Server systems should be robust against external attacks.

 - DOS-1: [Beware of activities that may use disproportionate resources](g1_01)
 - DOS-2: [Release resources in all cases](g1_02)
 - DOS-3: [Resource limit checks should not suffer from integer overflow](g1_03)
