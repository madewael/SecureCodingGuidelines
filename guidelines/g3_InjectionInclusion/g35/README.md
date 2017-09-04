# INJECT-5: Restrict XML inclusion
XML Document Type Definitions (DTDs) allow URLs to be defined as system entities, such as local files and HTTP URLs within the local intranet or localhost. XML External Entity (XXE) attacks insert local files into XML data which may then be accessible to the client. Similar attacks may be made using XInclude, the XSLT document function, and the XSLT import and include elements. The safe way to avoid these problems whilst maintaining the power of XML is to reduce privileges as described in Guideline 9-2. You may decide to give some access through this technique, such as inclusion to pages from the same-origin web site. Another approach, if such an API is available, is to set all entity resolvers to safe implementations.

Note that this issue generally applies to the use of APIs that use XML but are not specifically XML APIs.

# Others 

- INJECT-1: [Generate valid formatting](../g31)
- INJECT-2: [Avoid dynamic SQL](../g32)
- INJECT-3: [XML and HTML generation requires care](../g33)
- INJECT-4: [Avoid any untrusted data on the command line](../g34)
- INJECT-5: Restrict XML inclusion
- INJECT-6: [Care with BMP files](../g36)
- INJECT-7: [Disable HTML display in Swing components](../g37)
- INJECT-8: [Take care interpreting untrusted code](../g38)
- INJECT-9: [Prevent injection of exceptional floating point values](../g39)


