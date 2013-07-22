
/**
*   JFlex Scanner
*/

import java_cup.runtime.Symbol;

%%

%class Lexer
%cup
%implements sym
%line
%column

%{

  private Symbol symbol(int sym) {
    return new Symbol(sym, yyline+1, yycolumn+1);
  }
  
  private Symbol symbol(int sym, Object val) {
    return new Symbol(sym, yyline+1, yycolumn+1, val);
  }
  
  private void error(String message) {
    System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }
%} 

Id = [_a-zA-Z]+[_0-9a-zA-Z]*

Int = 0 | [1-9][0-9]*

Number = Int | Int ("."[0-9]+)

new_line = \r|\n|\r\n;

white_space = {new_line} | [ \t\f]

%%


/* keywords */
"local"         {return symbol(LOCAL); }
"function"      { return symbol(FUNC); }
"end"			{ return symbol(END); }
"do"			{ return symbol(DO); }
"while"			{ return symbol(WHILE); }
"for"			{ return symbol(FOR); }
"in"			{ return symbol(IN); }
"repeat"		{ return symbol(REPEAT); }
"until"			{ return symbol(UNTIL); }
"if"			{ return symbol(IF); }
"then"			{ return symbol(THEN); }
"else"			{ return symbol(ELSE); }
"elseif"		{ return symbol(ELSEIF); }
"return"		{ return symbol(RETURN); }
"break"			{ return symbol(BREAK); }

/* special values */
"nil"			{ return symbol(NIL); }
"false"			{ return symbol(FALSE); }
"true"			{ return symbol(TRUE); }
"..."			{ return symbol(PARAMS); }


/* identifiers */
{Id}           { return symbol(ID, yytext()); }
  
/* numbers */
{Number} { return symbol(NUMBER, new Double(Double.parseDouble(yytext()))); }

/* separators */
";"               { return symbol(SEMI); }
","				 { return symbol(COM); }

/* binary operators */
"=="             { return symbol(EQ); }
"~="             { return symbol(NEQ); }
"<="             { return symbol(LEQ); }
"<"              { return symbol(LE); }
">"              { return symbol(GR); }
">="             { return symbol(GEQ); }
"+"              { return symbol(ADD); }
"-"              { return symbol(SUB); }
"*"              { return symbol(MUL); }
"/"              { return symbol(DIV); }
"^"              { return symbol(POW); }
"%"              { return symbol(MOD); }
".."			 { return symbol(TIL); }
"and"            { return symbol(AND); }
"or"             { return symbol(OR); }
"."				 { return symbol(DOT); }
":"				 { return symbol(DDOT); }

/* unary operators */

"not"			{ return symbol(NOT); }
"#"				{ return symbol(LENGTH); }

/* parenthesis */
"(" 			{ return symbol(PAREN); }
")" 			{ return symbol(RPAREN); }
"["				{ return symbol(LBRACK); }
"]"				{ return symbol(RBRACK); }
"{"				{ return symbol(LCURL); }
"}"				{ return symbol(RCURL); }

/* assignment */
"="				{ return symbol(ASM); }

/* white space */
{white_space}     { /* ignore */ }

/* error fallback */
.|\n              {  /* throw new Error("Illegal character <"+ yytext()+">");*/
		    error("Illegal character <"+ yytext()+">");
                  }
