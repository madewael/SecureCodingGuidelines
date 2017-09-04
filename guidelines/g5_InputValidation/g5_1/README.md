# INPUT-1: Validate inputs
Input from untrusted sources must be validated before use. Maliciously crafted inputs may cause problems, whether coming through method arguments or external streams. Examples include overflow of integer values and directory traversal attacks by including "../" sequences in filenames. Ease-of-use features should be separated from programmatic interfaces. Note that input validation must occur after any defensive copying of that input (see Guideline 6-2).

        
# Others
 - INPUT-1: Validate inputs
 - [INPUT-2: Validate output from untrusted objects as input](../g52)
 - [INPUT-3: Define wrappers around native methods](../g53)
