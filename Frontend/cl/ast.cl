
package program;

Chunk ::= StatList:stats | StatList:stats LastStat:last

StatList ::= Stat:head StatList:tail

Block ::= Chunk:chunk

Stat ::= {Asm} VarList:varlist ExpList:explist 
		| {FunctionCall} 
		| {Do} Block:block
		| {While} Exp:exp Block:block
		| {RepeatUntil} Block:block Exp:exp
		| {IfThenElse} Exp:ifexp Block:thenblock Elseif:elseifpart Block:elseblock
		| {For} "String":ident Exp:start Exp:end Exp:step Block:block
		| {ForIn} NameList:namelist ExpList:explist Block:block
		| {FunctionDef} FuncName:name FuncBody:body
		| {LocalFuncDef} "String":ident FuncBody:body
		| {LocalDecl} NameList:namelist ExpList:explist
		
Elseif ::= ElseifPart:head Elseif:tail

ElseifPart ::= Exp:exp Block:block

LastStat ::= Explist:explist

FuncName ::= "String":ident MemberList:members "String":method

MemberList ::= "String":head MemberList:tail

VarList ::= Var:head VarList:tail

Var ::=  {Variable} "String":var 
		| {TabIndex} PrefixExp:preexp Exp:indexexp 
		| {TabIndex} PrefixExp:preexp "String":ident 
