
package program;

Chunk ::= StatList:stats | StatList:stats LastStat:last

StatList ::= Stat*

Block ::= Chunk:chunk

Stat ::= {Asm} 				VarList:varlist ExpList:explist 
		| FunctionCall 
		| {Do} 				Block:block
		| {While} 			Exp:exp Block:block
		| {RepeatUntil} 	Block:block Exp:exp
		| {IfThenElse} 		Exp:ifexp Block:thenblock Block:elseblock
		| {For} 			"String":ident Exp:start Exp:end Exp:step Block:block
		| {ForIn} 			NameList:namelist ExpList:explist Block:block
		| {FunctionDef} 	FuncName:name FuncBody:body
		| {LocalFuncDef} 	"String":ident FuncBody:body
		| {LocalDecl} 		NameList:namelist ExpList:explist

LastStat ::= ExpList:explist

FuncName ::= "String":ident MemberList:members "String":method

MemberList ::= Member*

Member ::= "String":member

VarList ::= Var*

Var ::=  {Variable} "String":var 
		| {TabIndex} PrefixExp:preexp Exp:indexexp 
		| {TabIndex} PrefixExp:preexp "String":ident 

NameList ::= Name*

Name ::= "String":name

ExpList ::= Exp*

Exp ::=  {Nil} 
		| {Boolean} "boolean":value  
		| {Number} 	"double":number
		| String
		| {Dots} "String":dots
		| Function 	
		| PrefixExp
		| {TableConstructor} FieldList:fieldlist
		| {Binop} Exp:leftexp "int":op Exp:rightexp
		| {Unop} "int":op Exp:exp 

PrefixExp ::= 	Var 
				| FunctionCall 
				| Exp

FunctionCall ::=  PrefixExp:preexp Args:args

Args ::=  ExpList
		| {TableConst} FieldList:fieldlist 
		| String 
		
String ::= "String":text

Function ::= Function:func FuncBody:body

FuncBody ::= ParList:parlist Block:block

ParList ::= NameList:namelist "String":dots

FieldList ::= Field*

Field ::= 	{LRExp} Exp:leftexp Exp:rightexp 
			| {NameExp} Name:name Exp:exp 
			| Exp
	
Op::= enum
         ADD, SUB, MUL, DIV, POW, MOD, CONCAT, 
		 LT, LE, GT, GE, EQ, NEQ, 
		 AND, OR, UNM, NOT, LEN