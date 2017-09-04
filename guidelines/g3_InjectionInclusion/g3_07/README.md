# INJECT-7: Disable HTML display in Swing components
![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)


Many Swing pluggable look-and-feels interpret text in certain components starting with <html> as HTML. If the text is from an untrusted source, an adversary may craft the HTML such that other components appear to be present or to perform inclusion attacks.

To disable the HTML render feature, set the "html.disable" client property of each component to Boolean.TRUE (no other Boolean true instance will do).

        label.putClientProperty("html.disable", true);

