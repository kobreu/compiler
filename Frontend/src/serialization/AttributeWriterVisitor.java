package serialization;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Closure;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.Name;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.VisitorAdaptor;
import edu.tum.lua.ast.VisitorNode;

public class AttributeWriterVisitor extends VisitorAdaptor {

	private Node currElement;
	
	private Document document;
	
	private void addAttr(String name, String value) {
		Attr attr = document.createAttribute(name);
		attr.setValue(value);
		currElement.getAttributes().setNamedItem(attr);
	}
	
	
	public AttributeWriterVisitor(Node curr, Document doc) {
		currElement = curr;
		document = doc;
	}
	
	private String oplookup(int op) {
		switch(op) {
			case Op.ADD: return "ADD";
			case Op.AND: return "AND";
			case Op.CONCAT: return "CONCAT";
			case Op.DIV: return "DIV";
			case Op.EQ: return "EQ";
			case Op.GE: return "GE";
			case Op.GT: return "GT";
			case Op.LE: return "LE";
			case Op.LEN: return "LEN";
			case Op.LT: return "LT";
			case Op.MOD: return "MOD";
			case Op.MUL: return "MUL";
			case Op.NEQ: return "NEQ";
			case Op.NOT: return "NOT";
			case Op.OR: return "OR";
			case Op.POW: return "POW";
			case Op.SUB: return "SUB";
			case Op.UNM: return "UNM";
			default: return "UNKNOWN";
		}
	}
	
	@Override
	public void visit(Binop binop) {
		addAttr("op", String.valueOf(binop.op));
	}
	
	@Override
	public void visit(Unop unop) {
		addAttr("op", oplookup(unop.op));
	}
	
	public void visit(NumberExp numberExp) {
		addAttr("number", String.valueOf(numberExp.number));
	}
	
	public void visit(Variable var) {
		addAttr("var", var.var);
	}
	
	@Override
	public void visit(Closure closure) {
		addAttr("varargs", String.valueOf(closure.varargs));
	}
	
	@Override
	public void visit(Dots dots) {
		addAttr("dots", dots.dots);
	}
	
	@Override
	public void visit(FieldNameExp fieldNameExp) {
		addAttr("ident", fieldNameExp.ident);
	}
	
	@Override
	public void visit(FunctionDef functionDef) {
		addAttr("ident", functionDef.ident);
		addAttr("varargs", String.valueOf(functionDef.varargs));
	}
	
	@Override
	public void visit(LocalFuncDef localFuncDef) {
		addAttr("ident", localFuncDef.ident);
		addAttr("varargs", String.valueOf(localFuncDef.varargs));
	}	
	
	@Override
	public void visit(Name name) {
		addAttr("name", name.name);
	}
	
	@Override
	public void visit(TextExp textExp) {
		addAttr("text", textExp.text);
	}

	@Override
	public void visit() {
		// TODO Auto-generated method stub
		super.visit();
	}
}
