import java.util.List;
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001       Gerwin Klein <lsf@jflex.de>                    *
 * Copyright (C) 2001       Bernhard Rumpe <rumpe@in.tum.de>               *
 * All rights reserved.                                                    *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


/**
 * AST node for the whole program (top node).
 * 
 * Also contains two symbol tables, one for input variables,
 * one for function names. 
 *
 * All operations like context check, symbol table build up
 * etc. start here.
 */ 
class Tprogram implements AST {

  Tident outputvar;           // input variables
  Tident polyvar;
  Tinputlist inputlist;         // function declarations 
  Texp exp;
  
  public Tprogram(Tident out, Tinputlist inl, Tident poly, Texp exp) {
    inputlist=inl;
    outputvar = out;
    polyvar = poly;
    this.exp = exp;
  }

  public String toString() {
    return("Program:\n=============\nOUTPUT "+outputvar+
           "\n"+inputlist + "\n" + polyvar + "=" + exp.toString());
  }

  SymConstraints inputs;      // table of input variables

  /**
   * Check for semantic errors.
   */
  public void checkcontext() {
	 inputlist.checkcontext();
	 if(!outputvar.name.equals(polyvar.name)) {
		 Main.error("Output variable is not the result of polynomial!");
	 }
  }

  /**
   * Return index of the given variable in the input list
   * @param varname
   * @return
 * @throws VariableNotConstrainedException 
   */
  public int lookup(String varname) throws VariableNotConstrainedException {
	 List<Tinput> inputs = inputlist.asList();
	 for(int i = 0; i < inputs.size(); i++) {
		 if(varname.equals(inputs.get(i).var.name)) {
			 return i;
		 }
	 }
	 throw new VariableNotConstrainedException(varname);
  }
  
  /**
   * Interpret the result and print out the table
 * @throws VariableNotConstrainedException 
   */
  public void interpret() throws VariableNotConstrainedException {    
   // System.out.println("Result:\n=============");
    
    List<Tinput> inputs = inputlist.asList();
    for(Tinput input: inputs) {
    	System.out.print(input.var.name + " | ");
    }
    System.out.println("" + outputvar.name);
    // get constraints
    int[] in = new int[inputs.size()];
    buildResultTable(0, in, inputs);
  }

private void buildResultTable(int i, int[] in, List<Tinput> inputs2) throws VariableNotConstrainedException {
	Tinput curr = inputs2.get(i);
	for(int k = curr.first.n; k <= curr.second.n; k++) {
		in[i] = k;
		if(i < inputs2.size() - 1) {
			buildResultTable(i+1, in, inputs2);
		} else {
			// this is the end, you know
			int res = exp.interpret(in, null, this);
			for(int q = 0;  q <= i; q++) {
				System.out.print(in[q] + " ");
			}
			System.out.println(res);
		}
	}
}

}

