Juna
========
Visit us on http://kobreu.github.io/compiler/

License
===
GPLv3

JFLEX - used for scanning the program. Download Manual. Licensed by GNU General Public License.
Java CUP Parser - used for parsing the program. More about CUP parser .This is an open source license. It is also GPL-Compatible
JLine - used for handling the console input. JLine is distributed under the BSD license.
HtmlCleaner - used for removing the syntactically wrong HTML code. Licensed by BSD license

Build Instructions
===

* ant gen - generates lexer and parser
* ant compile - compiles everything
* ant jar - bundles everything into a single jar
* ant clean - cleans the project

Interactive/Batch Mode
===

Usage in Batch Mode: java -jar juna.jar [-options] [Lua-File] with following options:

* -h, -help - show the Batch-Help
* [-i [<file>]] - open Juna in interactive Mode (Default) and if set, execute the given Lua-File (equal to dofile(<file>) in the interactive mode)
* [-f] <file> - executes a File in batch Mode (Default for Files) and exit Juna afterwards

Usage in Interactive Mode:

* --help - show the Interactive-Help
* --list - list all available functions in the global environment with a short description
* --list <function> - print detailed help for a predefinied function
* --env  - list all members (without functions) of the global environment with their types and values
* --exit - leave the interactive mode and exit Juna
* all other inputs are handled in Lua behavior (in realtime) and probably closing Juna in consequences of an error

Architecture
===

The Scanner (JFLEX) takes an Lua Code, either the current line in the interactive mode or from a file, and produce a Token-Stream. The Parser (Cup) generates out of the Token-Stream together with the different block types provided by Classgen an Abstract Syntax Tree matching the Lua grammar.
Interpreter

The Interpreter simply step through the abstract syntax tree and handle each block. The tree visitor differ a block by operator (like '+', '-'), by block element (like loops or conditions) or by functions (standard library functions as well as user defined functions). Currently, all operations and block elements are implemented but some standard library functions are still missing yet.
Important annotation

Thus Juna is based on Java there won't be exactly the same behavior like it is given by the Lua implementation in C. Especially the possibility to communicate with the underlying environment is missing in Java. Therefor Juna is more secure than the official Lua interpreter.

Team
===
 * Lisa (velden (at) in.tum.de)
 * Johannes (mikulasc (at) in.tum.de)
 * Salomon (sickert (at) in.tum.de)
 * Isábel (isabel.delesques-grauby (at) polytechnique.edu)
 * Matthias (kneidel (at) in.tum.de)
 * Shruthi (shruthi.padma (at) tum.de)
 * Korbinian (Korbinian.breu (at) mytum.de)
