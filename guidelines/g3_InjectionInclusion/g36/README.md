# INJECT-6: Care with BMP files
BMP images files may contain references to local ICC (International Color Consortium) files. Whilst the contents of ICC files is unlikely to be interesting, the act of attempting to read files may be an issue. Either avoid BMP files, or reduce privileges as Guideline 9-2.


# Others 

- INJECT-1: [Generate valid formatting](../g31)
- INJECT-2: [Avoid dynamic SQL](../g32)
- INJECT-3: [XML and HTML generation requires care](../g33)
- INJECT-4: [Avoid any untrusted data on the command line](../g34)
- INJECT-5: [Restrict XML inclusion](../g35)
- INJECT-6: Care with BMP files
- INJECT-7: [Disable HTML display in Swing components](../g37)
- INJECT-8: [Take care interpreting untrusted code](../g38)
- INJECT-9: [Prevent injection of exceptional floating point values](../g39)


