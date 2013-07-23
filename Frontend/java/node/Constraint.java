/*
 * Generated by classgen, version 1.5
 * 30.05.13 19:22
 */
package node;

public class Constraint implements SyntaxNode {

  private SyntaxNode parent;
  public int lower;
  public String var;
  public int upper;

  public Constraint (int lower, String var, int upper) {
    this.lower = lower;
    this.var = var;
    this.upper = upper;
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Constraint(\n");
    buffer.append("  "+tab+lower);
    buffer.append("\n");
    buffer.append("  "+tab+var);
    buffer.append("\n");
    buffer.append("  "+tab+upper);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Constraint]");
    return buffer.toString();
  }
}