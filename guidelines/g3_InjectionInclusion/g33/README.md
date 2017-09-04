# INJECT-3: XML and HTML generation requires care
Untrusted data should be properly sanitized before being included in HTML or XML output. Failure to properly sanitize the data can lead to many different security problems, such as Cross-Site Scripting (XSS) and XML Injection vulnerabilities. It is important to be particularly careful when using Java Server Pages (JSP).

There are many different ways to sanitize data before including it in output. Characters that are problematic for the specific type of output can be filtered, escaped, or encoded. Alternatively, characters that are known to be safe can be allowed, and everything else can be filtered, escaped, or encoded. This latter approach is preferable, as it does not require identifying and enumerating all characters that could potentially cause problems.

Implementing correct data sanitization and encoding can be tricky and error-prone. Therefore, it is better to use a library to perform these tasks during HTML or XML construction.

# Others 

- INJECT-1: [Generate valid formatting](../g31)
- INJECT-2: [Avoid dynamic SQL](../g32)
- INJECT-3: XML and HTML generation requires care
- INJECT-4: [Avoid any untrusted data on the command line](../g34)
- INJECT-5: [Restrict XML inclusion](../g35)
- INJECT-6: [Care with BMP files](../g36)
- INJECT-7: [Disable HTML display in Swing components](../g37)
- INJECT-8: [Take care interpreting untrusted code](../g38)
- INJECT-9: [Prevent injection of exceptional floating point values](../g39)


