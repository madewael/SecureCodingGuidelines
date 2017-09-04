# INJECT-5: Restrict XML inclusion
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


XML Document Type Definitions (DTDs) allow URLs to be defined as system entities, such as local files and HTTP URLs within the local intranet or localhost. XML External Entity (XXE) attacks insert local files into XML data which may then be accessible to the client. Similar attacks may be made using XInclude, the XSLT document function, and the XSLT import and include elements. The safe way to avoid these problems whilst maintaining the power of XML is to reduce privileges as described in Guideline 9-2. You may decide to give some access through this technique, such as inclusion to pages from the same-origin web site. Another approach, if such an API is available, is to set all entity resolvers to safe implementations.

Note that this issue generally applies to the use of APIs that use XML but are not specifically XML APIs.
