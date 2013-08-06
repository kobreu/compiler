/*
 * Generated by classgen, version 1.5
 * 29.07.13 18:12
 */
package asttweaks;

import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.SyntaxNode;
import edu.tum.lua.ast.Visitor;

public class ParList extends SyntaxNode {

  private SyntaxNode parent;
  protected NameList namelist;
  protected Boolean varparlist;

  public ParList (NameList namelist, Boolean varparlist) {
    this.namelist = namelist;
    this.varparlist = varparlist;
  }

  @Override
public SyntaxNode getParent() {
    return parent;
  }

  @Override
public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }


  @Override
public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ParList(\n");
      if (namelist != null)
        buffer.append(namelist.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append("  "+tab+varparlist);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ParList]");
    return buffer.toString();
  }

@Override
public void accept(Visitor visitor) {
	throw new RuntimeException("Should not be here!");
	
}

@Override
public void childrenAccept(Visitor visitor) {
	throw new RuntimeException("Should not be here!");
	
}

@Override
public void traverseBottomUp(Visitor visitor) {
	throw new RuntimeException("Should not be here!");
	
}

@Override
public void traverseTopDown(Visitor visitor) {
	throw new RuntimeException("Should not be here!");
	
}
}
