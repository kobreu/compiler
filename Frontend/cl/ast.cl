
package edu.tum.lua.ast;

Block ::= StatList:stats LastStat:last

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
		| {FunctionDef} 	"String":ident NameList:members NameList:args "boolean":varargs Block:block
		| {LocalFuncDef} 	"String":ident NameList:args "boolean":varargs Block:block
		| {LocalDecl} 		NameList:namelist ExpList:explist

Exp ::=  {Nil} 
		| {BooleanExp} "boolean":value  
		| {NumberExp} 	"double":number
		| {TextExp} "String":text
		| {Dots} "String":dots
		| {Closure} NameList:args "boolean":varargs Block:block 	
		| {PreExp} PrefixExp:preexp
		| {TableConstructorExp} TableConstructor:tablecons
		| {Binop} Exp:leftexp "int":op Exp:rightexp
		| {Unop} "int":op Exp:exp 
		| {FunctionExp} Function:function

VarList ::= Var*

Var ::=  {Variable} "String":var 
		| {VarTabIndex} PrefixExp:preexp Exp:indexexp 

NameList ::= Name*

Name ::= "String":name

ExpList ::= Exp*

PrefixExp ::= 	{PrefixExpVar} Var:var 
				| {PrefixExpFuncCall} FunctionCall:call 
				| {PrefixExpExp} Exp:exp

FunctionCall ::=  {FuncCall} PrefixExp:preexp  ExpList:explist
				  | {FuncCallSelf} PrefixExp:preexp  Name:name ExpList:explist

Function		::= FuncBody:funcbody
FuncBody	::= ParList:parlist Block:block

FuncName	::= {FuncNameVar} Var:var
				| {FuncNameVarDotFuncName} Var:var FuncName:funcName
				| {FuncNameDDotVar} Var:var
				
ParList		::=  NameList:namelist "Boolean":varparlist
				
TableConstructor ::= FieldList:fieldlist

Field ::= 	{FieldLRExp} Exp:leftexp Exp:rightexp 
			| {FieldNameExp} "String":ident Exp:exp 
			| {FieldExp} Exp:fieldexp

FieldList ::= Field*

	
Op ::= enum
         ADD, SUB, MUL, DIV, POW, MOD, CONCAT, 
		 LT, LE, GT, GE, EQ, NEQ, 
		 AND, OR, UNM, NOT, LEN