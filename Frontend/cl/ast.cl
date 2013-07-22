
package node;

Prog ::= Output Input Polynom

Output  ::= "String":var 

Constraint ::= "int":lower "String":var "int":upper

Input ::= Constraint:head Input:tail

Term  ::= "int":number "String":var "int":exp ExpSeq:expr

PolyPart  ::= Term:head PolyPart:tail

ExpSeq  ::= Exp:head ExpSeq:tail

Exp     ::= "String":var "int":exp

Polynom ::= "String":var PolyPart:polypart