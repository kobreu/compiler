
package edu.tum.lua.ast;

Chunk ::= StatList:stats LastStat:last

Block ::= Chunk:chunk

StatList ::= Stat*

LastStat ::= {LastReturn} ExpList:explist
			| {LastBreak}
			
Stat ::= {Asm} 				VarList:varlist ExpList:explist 
		| {FuncCallStmt}	FunctionCall:call
		| {DoExp} 			Block:block
		| {WhileExp} 		Exp:exp Block:block
		| {RepeatUntil} 	Block:block Exp:exp
		| {IfThenElse} 		Exp:ifexp Block:thenblock Block:elseblock
		| {ForExp} 			"String":ident Exp:start Exp:end Exp:step Block:block
		| {ForIn} 			NameList:namelist ExpList:explist Block:block
		| {FunctionDef} 	FuncName:name FuncBody:body
		| {LocalFuncDef} 	"String":ident FuncBody:body
		| {LocalDecl} 		NameList:namelist ExpList:explist

Exp ::=  {Nil} 
		| {BooleanExp} "boolean":value  
		| {Number} 	"double":number
		| {TextExp} Text:text
		| {Dots} "String":dots
		| {FunctionExp} Function:function 	
		| {PreExp} PrefixExp:preexp
		| {TableConstructor} FieldList:fieldlist
		| {Binop} Exp:leftexp "int":op Exp:rightexp
		| {Unop} "int":op Exp:exp 

FuncName ::= "String":ident MemberList:members "String":method

MemberList ::= Member*

Member ::= "String":member

VarList ::= Var*

Var ::=  {Variable} "String":var 
		| {VarTabIndex} PrefixExp:preexp Exp:indexexp 

NameList ::= Name*

Name ::= "String":name

ExpList ::= Exp*

PrefixExp ::= 	{PrefixExpVar} Var:var 
				| {PrefixExpFuncCall} FunctionCall:call 
				| {PrefixExpExp} Exp:exp


Args ::=  {ArgsExpList} ExpList:explist
		| {ArgsTableConst} FieldList:fieldlist 
		| {ArgsText} Text:text 

FunctionCall ::=  PrefixExp:preexp Args:args
		
Text ::= "String":text

Function ::= Function:func FuncBody:body

FuncBody ::= ParList:parlist Block:block

ParList ::= NameList:namelist "String":dots

Field ::= 	{FieldLRExp} Exp:leftexp Exp:rightexp 
			| {FieldNameExp} Name:name Exp:exp 
			| {FieldExp} Exp:fieldexp

FieldList ::= Field*
	
Op::= enum
         ADD, SUB, MUL, DIV, POW, MOD, CONCAT, 
		 LT, LE, GT, GE, EQ, NEQ, 
		 AND, OR, UNM, NOT, LEN