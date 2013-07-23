import java.util.*;
import node.*;


public class Main {


 /**
   * Runs the parser on an input file.
   *
   *
   * @param argv   the command line, argv[0] is the filename to run
   *               the parser on.
   */
  public static void main(String argv[]) 
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

    try {
      Parser p = new Parser(scanner);
      Prog prog = (Prog) p.parse().value;

      if (!prog.output.var.equals(prog.polynom.var)) {
        System.out.println("Output variable should be the same as result variable in the polynomial: \n"
            + prog.output.var + " != " + prog.polynom.var);
        System.exit(1);
      }

      printVars(prog);
      LinkedHashMap<String, Integer> asm = new LinkedHashMap<String, Integer>();
      EvaluationVisitor evaluation = new EvaluationVisitor(asm);
      visit(asm, evaluation, prog, prog.input);
      
    }
    catch (java.io.IOException e) {
      System.out.println("An I/O error occured while parsing : \n"+e);
      System.exit(1);
    }
  }

  // print first row containing all variables
  private static void printVars(Prog prog){
    Input tmp = prog.input;
    while(tmp != null){
      System.out.print(tmp.head.var + " | ");
      tmp = tmp.tail;
    }
    System.out.println(prog.output.var);
  }

  // print all assignments and the corresponding result
  private static void printValues(Map<String, Integer> asm){
    Set<String> keys = asm.keySet();
    for(String var: keys){
      System.out.print(asm.get(var) + "   ");
    }
  }

  private static void visit(LinkedHashMap<String, Integer> asm, EvaluationVisitor evaluation, Prog prog, Input input){

    // check illegal inputs
    if(input.head.lower > input.head.upper){
      throw new IllegalArgumentException("Lower bound greater than upper bound: " + input.head.lower + " > " + input.head.upper);
    }

    if(asm.containsKey(input.head.var)){
      throw new IllegalArgumentException("There is already a constraint for variable: " + input.head.var);
    }

    if((input.head.var).equals(prog.output.var)){
      throw new IllegalArgumentException("INPUT and OUTPUT variables cannot be the same: INPUT " + input.head.var + " = OUTPUT " + prog.output.var);
    }

    // call prog.accept(evaluation) for all possible variable assignments
    for(int i=input.head.lower; i<=input.head.upper; i++){
      asm.put(input.head.var, i);
      if(input.tail != null){
        visit(asm, evaluation, prog, input.tail);
      }else{
        printValues(asm);
        prog.accept(evaluation);
        System.out.println(evaluation.getReturnValue());
      }
      asm.remove(input.head.var);
    }
  }
}




