/**
 * <pre>
 *  This Package contains the Scanner.
 * 
 *  Dictionary:
 *  - <b>Lexem</b>:
 *   adjacent characters belonging together are called lexemes.
 *   Example: |while|␣|x36|␣␣|<=|67|␣|do|␣…
 * 
 *  - <b>token</b>: either terminals or pairs consisting of a terminal and an attribute.
 *    Each token is made from a lexem.
 *    Examples: WHILE, (IDENT, "x36"), (RELOPR, LE)…
 * 
 *  - <b>SENTINEL</b>: special terminal marking the end of the sequence of tokens
 * 
 *  - Terminal: WHILE, IDENT, RELOPR, …, SENTINEL
 *    Every token has a Terminal. Some terminals also need an attribute.
 * 
 *  - Attribute:
 *   Attribute to a terminal. both together make one token.
 * 
 *  - RelOpr / Operator: Attribute that is an relational Operator.
 *  - Ident: Attribute that is an "ident". Integer or Boolean.
 *  - Literal: Attribute that is a "literal". Could be the name of a variable.
 * 
 * - Symbol: The IML Spec defines a set of Symbols. This Parser treats those Symbols that have an Attribute just like other Operators.
 *   So a Symbol in this Parse is a Symbol without Attribute.
 * </pre>
 */
package ch.fhnw.cpib.iml.scanner;