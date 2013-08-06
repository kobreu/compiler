package edu.tum.juna.parser.serialization;

import org.dom4j.Element;

import edu.tum.juna.ast.Binop;
import edu.tum.juna.ast.BooleanExp;
import edu.tum.juna.ast.Dots;
import edu.tum.juna.ast.FieldNameExp;
import edu.tum.juna.ast.ForExp;
import edu.tum.juna.ast.FunctionExp;
import edu.tum.juna.ast.LocalFuncDef;
import edu.tum.juna.ast.Name;
import edu.tum.juna.ast.NumberExp;
import edu.tum.juna.ast.Op;
import edu.tum.juna.ast.TextExp;
import edu.tum.juna.ast.Unop;
import edu.tum.juna.ast.Variable;
import edu.tum.juna.ast.VisitorAdaptor;

public class AttributeWriterVisitor extends VisitorAdaptor {

	private final Element currElement;

	private void addAttr(String name, String value) {
		currElement.addAttribute(name, value);
	}

	public AttributeWriterVisitor(Element curr) {
		currElement = curr;
	}

	private String oplookup(int op) {
		switch (op) {
		case Op.ADD:
			return "ADD";
		case Op.AND:
			return "AND";
		case Op.CONCAT:
			return "CONCAT";
		case Op.DIV:
			return "DIV";
		case Op.EQ:
			return "EQ";
		case Op.GE:
			return "GE";
		case Op.GT:
			return "GT";
		case Op.LE:
			return "LE";
		case Op.LEN:
			return "LEN";
		case Op.LT:
			return "LT";
		case Op.MOD:
			return "MOD";
		case Op.MUL:
			return "MUL";
		case Op.NEQ:
			return "NEQ";
		case Op.NOT:
			return "NOT";
		case Op.OR:
			return "OR";
		case Op.POW:
			return "POW";
		case Op.SUB:
			return "SUB";
		case Op.UNM:
			return "UNM";
		default:
			return "UNKNOWN";
		}
	}

	@Override
	public void visit(Binop binop) {
		addAttr("op", oplookup(binop.op));
	}

	@Override
	public void visit(Unop unop) {
		addAttr("op", oplookup(unop.op));
	}

	@Override
	public void visit(NumberExp numberExp) {
		addAttr("number", String.valueOf(numberExp.number));
	}

	@Override
	public void visit(Variable var) {
		addAttr("var", var.var);
	}

	@Override
	public void visit(Dots dots) {
	}

	@Override
	public void visit(LocalFuncDef localFuncDef) {
		addAttr("name", localFuncDef.name);
		addAttr("varargs", String.valueOf(localFuncDef.varargs));
	}

	@Override
	public void visit(FieldNameExp fieldNameExp) {
		addAttr("ident", fieldNameExp.ident);
	}

	@Override
	public void visit(FunctionExp functionExp) {
		addAttr("varargs", String.valueOf(functionExp.varargs));

	}

	@Override
	public void visit(ForExp forExp) {
		addAttr("ident", forExp.ident);
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
	public void visit(BooleanExp booleanExp) {
		addAttr("value", String.valueOf(booleanExp.value));
	}

	@Override
	public void visit() {
		super.visit();
	}
}
