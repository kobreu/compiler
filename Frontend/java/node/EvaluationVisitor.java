package node;

import java.util.*;


public class EvaluationVisitor implements Visitor{

	private int returnValue;
	private Map<String, Integer> asm;

  // used for terms like x^a*y^b
  private int exponentials;

  public EvaluationVisitor(Map<String, Integer> asm){
  	this.asm = asm;
  	returnValue = 0;
  }

  public int getReturnValue(){
  	return returnValue;
  }


// visit methods:
  @Override
  public void visit(Prog prog) {
  	// reset return value for new calculation
  	returnValue = 0;
  	prog.childrenAccept(this);
  }

  @Override
  public void visit(Output output) {
  	output.childrenAccept(this);
  }

  @Override
  public void visit(Constraint constraint) {
  	constraint.childrenAccept(this);
  }

  @Override
  public void visit(Input input) {
  	input.childrenAccept(this);
  }

  @Override
  public void visit(Term term) {
    // reset exponentials for new multiplications
    exponentials = 1;

  	if(term.var == null){
  		returnValue += term.number;
  	} else if(!asm.containsKey(term.var)) {
  		throw new IllegalArgumentException("Variable "+ term.var+ " needs INPUT constraint to be in the variable assignment mapping!");
  	} else if(term.expr == null){
      returnValue += term.number * Math.pow(asm.get(term.var), term.exp);
    } else {
      exponentials *= term.number * Math.pow(asm.get(term.var), term.exp);
      term.childrenAccept(this);
    }
  }

  @Override
  public void visit(ExpSeq expSeq) {
    expSeq.childrenAccept(this);
    returnValue += exponentials;
  }

  @Override
  public void visit(Exp exp) {
    exponentials *= Math.pow(asm.get(exp.var), exp.exp);
  }

  @Override
  public void visit(PolyPart polyPart) {
  	polyPart.childrenAccept(this);
  }

  @Override
  public void visit(Polynom polynom) {
  	polynom.childrenAccept(this);
  }
}