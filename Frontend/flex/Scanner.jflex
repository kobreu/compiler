
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

Var = [a-zA-Z]+

Int = 0 | [1-9][0-9]*

new_line = \r|\n|\r\n;

white_space = {new_line} | [ \t\f]

%%


/* keywords */
"OUTPUT"          {return symbol(OUT); }
"INPUT"           { return symbol(IN); }

/* variables */
{Var}           { return symbol(VARIABLE, yytext()); }
  
/* numbers */
{Int} { return symbol(NUMBER, new Integer(Integer.parseInt(yytext()))); }

/* separators */
";"               { return symbol(SEMI); }

/* operators */
"="               { return symbol(EQ); }
"<="               { return symbol(LEQ); }
"+"               { return symbol(ADD); }
"*"               { return symbol(MUL); }
"^"               { return symbol(POW); }

/* white space */
{white_space}     { /* ignore */ }

/* error fallback */
.|\n              {  /* throw new Error("Illegal character <"+ yytext()+">");*/
		    error("Illegal character <"+ yytext()+">");
                  }
