package main;

import edu.tum.lua.parser.Lexer;
import edu.tum.lua.parser.Parser;
import edu.tum.lua.ast.*;


public class Main {


 /**
   * Runs the parser on an input file.
   *
   *
   * @param argv   the command line, argv[0] is the filename to run
   *               the parser on.
   */
  public Chunk run(String argv[]) 
    throws java.io.IOException, java.lang.Exception
 {
    Lexer scanner = null;
    try {
      scanner = new Lexer( new java.io.FileReader(argv[0]) );
    }
    catch (java.io.FileNotFoundException e) {
      System.out.println("File not found : \""+argv[0]+"\"");
      System.exit(1);
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Usage : java Main <inputfile>");
      System.exit(1);
    }
    
    Chunk prog = null;
    
    try {
      Parser p = new Parser(scanner);
      prog = (Chunk) p.parse().value;
      
    }
    catch (java.io.IOException e) {
      System.out.println("An I/O error occured while parsing : \n"+e);
      System.exit(1);
    }
    
    return prog;
  }

}




