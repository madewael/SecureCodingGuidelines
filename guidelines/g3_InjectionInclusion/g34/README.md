# INJECT-4: Avoid any untrusted data on the command line
When creating new processes, do not place any untrusted data on the command line. Behavior is platform-specific, poorly documented, and frequently surprising. Malicious data may, for instance, cause a single argument to be interpreted as an option (typically a leading - on Unix or / on Windows) or as two separate arguments. Any data that needs to be passed to the new process should be passed either as encoded arguments (e.g., Base64), in a temporary file, or through a inherited channel.

# Others 

- INJECT-1: [Generate valid formatting](../g31)
- INJECT-2: [Avoid dynamic SQL](../g32)
- INJECT-3: [XML and HTML generation requires care](../g33)
- INJECT-4: Avoid any untrusted data on the command line
- INJECT-5: [Restrict XML inclusion](../g35)
- INJECT-6: [Care with BMP files](../g36)
- INJECT-7: [Disable HTML display in Swing components](../g37)
- INJECT-8: [Take care interpreting untrusted code](../g38)
- INJECT-9: [Prevent injection of exceptional floating point values](../g39)


