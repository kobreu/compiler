
/**
*   JFlex Scanner
*/
package edu.tum.lua.parser;
import java_cup.runtime.Symbol;
import java.util.regex.Pattern;



%%

%public
%class Lexer
%cup
%implements sym
%line
%column

%{

  StringBuffer string = new StringBuffer();

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

Number = (0 | [1-9][0-9]*) ("."[0-9]+)?

line_terminator = \r|\n|\r\n

input_character = [^\r\n]

white_space = [ \r\n\t\f]+

/* comments */

Comment = {EndOfLineComment} | {MultipleLineComment}
EndOfLineComment = "--" {input_character}* {line_terminator}
MultipleLineComment = "--[""="*"[" {CommentContent} "]""="*"]"
CommentContent = ([^("]""="*"]")])*

%state STRINGDOUBLE
%state STRINGSINGLE
%state MULTILINE

%%

<YYINITIAL> {

\" { string.setLength(0); yybegin(STRINGDOUBLE); }
\' { string.setLength(0); yybegin(STRINGSINGLE); }

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
".."			 { return symbol(CONCAT); }
"and"            { return symbol(AND); }
"or"             { return symbol(OR); }
"."				 { return symbol(DOT); }
":"				 { return symbol(DDOT); }

/* unary operators */

"not"			{ return symbol(NOT); }
"#"				{ return symbol(LENGTH); }

/* parenthesis */
"(" 			{ return symbol(LPAREN); }
")" 			{ return symbol(RPAREN); }
"["				{ return symbol(LBRACK); }
"]"				{ return symbol(RBRACK); }
"{"				{ return symbol(LCURL); }
"}"				{ return symbol(RCURL); }

/* identifiers */
{Id}           { return symbol(ID, yytext()); }
  
/* numbers */
{Number} { return symbol(NUMBER, new Double(Double.parseDouble(yytext()))); }

/* assignment */
"="				{ return symbol(ASM); }

/* white space */
{white_space}    { return symbol(WS); }

/* comments */
{Comment} { /* ignore */ }

}

<STRINGDOUBLE> {
	"\"" 				{ yybegin(YYINITIAL);
					  return symbol(TEXT,
					  string.toString()); }
	[^\n\r\"\\]+ 	{ string.append( yytext() ); }
	
	"\\t" 			{ string.append("\t"); }
	"\\n" 			{ string.append("\n"); }
	"\\r" 			{ string.append("\r"); }
	/* Every other escape sequence is not valid */
	/*"\\." 			{ string.append(yytext()); }*/
	\\\"			{ string.append("\""); }
	"\n"			{ string.append("\n"); } // Multiline Strings
	\\\\            { string.append("\\"); }
	"\\"			{ string.append("");   }
	
}


<STRINGSINGLE> {
	"'" 			{ yybegin(YYINITIAL);
					  return symbol(TEXT,
					  string.toString()); }
	[^\n\r"'"\\]+ 	{ string.append( yytext() ); }
	\\t 			{ string.append("\t"); }
	\\n 			{ string.append("\n"); }
	\\r 			{ string.append("\r"); }
	/*"\\."			{ string.append(yytext()); }*/
	\\\\            { string.append("\\"); }
	"\\\n"			{ string.append("\n"); } // Multiline Strings
	\\\'			{ string.append("\'"); }

}

/* error fallback */
.|\n              {  /* throw new Error("Illegal character <"+ yytext()+">");*/
		    error("Illegal character <"+ Pattern.quote(yytext())+">");
                  }
