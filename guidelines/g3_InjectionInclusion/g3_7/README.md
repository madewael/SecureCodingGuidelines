# INJECT-7: Disable HTML display in Swing components
Many Swing pluggable look-and-feels interpret text in certain components starting with <html> as HTML. If the text is from an untrusted source, an adversary may craft the HTML such that other components appear to be present or to perform inclusion attacks.

To disable the HTML render feature, set the "html.disable" client property of each component to Boolean.TRUE (no other Boolean true instance will do).

        label.putClientProperty("html.disable", true);

# Others 

- INJECT-1: [Generate valid formatting](../g31)
- INJECT-2: [Avoid dynamic SQL](../g32)
- INJECT-3: [XML and HTML generation requires care](../g33)
- INJECT-4: [Avoid any untrusted data on the command line](../g34)
- INJECT-5: [Restrict XML inclusion](../g35)
- INJECT-6: [Care with BMP files](../g36)
- INJECT-7: Disable HTML display in Swing components
- INJECT-8: [Take care interpreting untrusted code](../g38)
- INJECT-9: [Prevent injection of exceptional floating point values](../g39)


