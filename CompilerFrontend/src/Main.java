import java.io.*;

/**
 * Main program of the interpreter for the polynomial
 * Based on JFlex/CUP.
 * 
 * Taken from the AS interpreter example shipped with JFlex
 */ 
public class Main {

  public static void main(String [] args) throws Exception {
	Reader reader = null;
    
    if (args.length == 1) {
      File input = new File(args[0]);
      if (!input.canRead()) {
        System.out.println("Error: could not read ["+input+"]");
      }
      reader = new FileReader(input);
    }
    else {  
     reader = new InputStreamReader(System.in);
    }

    Yylex scanner = new Yylex(reader);   // create scanner

    parser parser = new parser(scanner); // create parser
    Tprogram syntaxbaum = null;

    try { 
      syntaxbaum = (Tprogram) parser.parse().value;  // parse
    }    
    catch (Throwable t) { 
      // t.printStackTrace(); 
      System.out.println("Syntax error: " + t.getLocalizedMessage());
      System.exit(1);
    }

    // System.out.println(syntaxbaum);

    syntaxbaum.checkcontext();
    if(contexterror>0) return;

    try {
    	// interpret the tree
    	syntaxbaum.interpret();           // interpretation
    } catch(VariableNotConstrainedException exc) {
    	Main.error("Variable not constrained: " + exc.getVarname());
    }
  }

  static int contexterror = 0;        // number of errors in context conditions

  public static void error(String s) { 
    System.out.println((contexterror++)+". "+s); 
  }
}
