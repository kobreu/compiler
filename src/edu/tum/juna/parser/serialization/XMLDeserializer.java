package edu.tum.juna.parser.serialization;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import edu.tum.juna.ast.Binop;
import edu.tum.juna.ast.Block;
import edu.tum.juna.ast.BooleanExp;
import edu.tum.juna.ast.Exp;
import edu.tum.juna.ast.FieldNameExp;
import edu.tum.juna.ast.ForExp;
import edu.tum.juna.ast.FunctionExp;
import edu.tum.juna.ast.LocalFuncDef;
import edu.tum.juna.ast.Name;
import edu.tum.juna.ast.NameList;
import edu.tum.juna.ast.NumberExp;
import edu.tum.juna.ast.Op;
import edu.tum.juna.ast.TextExp;
import edu.tum.juna.ast.Unop;
import edu.tum.juna.ast.Variable;
import edu.tum.juna.ast.VisitorNode;

public class XMLDeserializer {

	int opToInt(String op) {
		if (op.equals("UNM"))
			return Op.UNM;
		else if (op.equals("ADD")) {
			return Op.ADD;
		} else if (op.equals("SUB")) {
			return Op.SUB;
		} else if (op.equals("MUL")) {
			return Op.MUL;
		} else if (op.equals("DIV")) {
			return Op.DIV;
		} else if (op.equals("POW")) {
			return Op.POW;
		} else if (op.equals("MOD")) {
			return Op.MOD;
		} else if (op.equals("CONCAT")) {
			return Op.CONCAT;
		} else if (op.equals("LT")) {
			return Op.LT;
		} else if (op.equals("LE")) {
			return Op.LE;
		} else if (op.equals("GT")) {
			return Op.GT;
		} else if (op.equals("GE")) {
			return Op.GE;
		} else if (op.equals("EQ")) {
			return Op.EQ;
		} else if (op.equals("NEQ")) {
			return Op.NEQ;
		} else if (op.equals("AND")) {
			return Op.AND;
		} else if (op.equals("OR")) {
			return Op.OR;
		} else if (op.equals("NOT")) {
			return Op.NOT;
		} else if (op.equals("LEN")) {
			return Op.LEN;
		}
		return -1;
	}

	private VisitorNode deserialize(Element ele) {
		ele.normalize();
		try {
			if (ele.getName().equals("Null")) {
				return null;
			}
			if (ele.getName().equals("NumberExp")) {
				NumberExp exp = new NumberExp(Double.valueOf(ele.attributeValue("number")));
				return exp;
			} else if (ele.getName().equals("Binop")) {
				Exp child1 = (Exp) deserialize(ele.elements().get(0));
				Exp child2 = (Exp) deserialize(ele.elements().get(1));

				Binop binop = new Binop(child1, opToInt(ele.attributeValue("op")), child2);
				return binop;
			} else if (ele.getName().equals("Unop")) {
				Exp child = (Exp) deserialize(ele.elements().get(0));
				Unop unop = new Unop(opToInt(ele.attributeValue("op")), child);
				return unop;
			} else if (ele.getName().equals("BooleanExp")) {
				BooleanExp bexp = new BooleanExp(Boolean.valueOf((ele.attributeValue("value"))).booleanValue());
				return bexp;
			} else if (ele.getName().equals("FieldNameExp")) {
				Exp exp = (Exp) deserialize(ele.elements().get(0));
				return new FieldNameExp(ele.attributeValue("ident"), exp);
			} else if (ele.getName().equals("ForExp")) {
				Exp ex1 = (Exp) deserialize(ele.elements().get(0));
				Exp ex2 = (Exp) deserialize(ele.elements().get(1));
				Exp ex3 = (Exp) deserialize(ele.elements().get(2));
				Block block = (Block) deserialize(ele.elements().get(3));
				return new ForExp(ele.attributeValue("ident"), ex1, ex2, ex3, block);
			} else if (ele.getName().equals("Name")) {
				return new Name(ele.attributeValue("name"));
			} else if (ele.getName().equals("LocalFuncDef")) {
				NameList args = (NameList) deserialize(ele.elements().get(0));
				Block block = (Block) deserialize(ele.elements().get(1));
				return new LocalFuncDef(ele.attributeValue("name"), args,
						Boolean.valueOf(ele.attributeValue("varargs")), block);
			} else if (ele.getName().equals("FunctionExp")) {
				NameList args = (NameList) deserialize(ele.elements().get(0));
				Block block = (Block) deserialize(ele.elements().get(1));
				return new FunctionExp(args, Boolean.valueOf(ele.attributeValue("varargs")), block);
			} else if (ele.getName().equals("TextExp")) {
				return new TextExp(ele.attributeValue("text"));
			} else if (ele.getName().equals("Variable")) {
				return new Variable(ele.attributeValue("var"));
			}

			else { // without attributes can be done automatically

				Class<?> clazz = Class.forName("edu.tum.juna.ast." + ele.getName());

				VisitorNode[] deserializedChildren = new VisitorNode[ele.elements().size()];
				Class<?>[] constructorClasses = new Class[ele.elements().size()];

				for (int i = 0; i < ele.elements().size(); i++) {
					Element child = ele.elements().get(i);

					deserializedChildren[i] = deserialize(child);
					if (deserializedChildren[i] != null) {
						constructorClasses[i] = deserializedChildren[i].getClass();
					} else {
						constructorClasses[i] = Object.class;
					}
				}

				VisitorNode deserialized = null;
				Method method;
				try {
					method = findAppendMethod(clazz, constructorClasses[0]);
					Constructor<?> constructor = findConstructor(clazz, new Class[] { constructorClasses[0] });

					deserialized = (VisitorNode) constructor.newInstance(deserializedChildren[0]);
					for (int i = 1; i < constructorClasses.length; i++) {
						method.invoke(deserialized, deserializedChildren[i]);
					}

				} catch (ArrayIndexOutOfBoundsException | NoSuchMethodException e) {
					Constructor<?> constructor = findConstructor(clazz, constructorClasses);

					deserialized = (VisitorNode) constructor.newInstance(deserializedChildren);
				}

				return deserialized;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Method findAppendMethod(Class<?> clazz, Class<?> constructorClass) throws NoSuchMethodException,
			SecurityException {
		try {
			return clazz.getDeclaredMethod("append", constructorClass);
		} catch (NoSuchMethodException e) {
			return clazz.getDeclaredMethod("append", constructorClass.getSuperclass());
		}
	}

	@SuppressWarnings("rawtypes")
	private Constructor<?> findConstructor(Class<?> clazz, Class[] constructorClasses) {
		Constructor[] constructors = clazz.getDeclaredConstructors();
		outer: for (Constructor c : constructors) {
			if (c.getParameterTypes().length != constructorClasses.length)
				continue;
			int index = 0;
			for (Class paramClass : c.getParameterTypes()) {
				Class<?> match = constructorClasses[index++];
				if (match == Object.class || paramClass == match || paramClass == match.getSuperclass()) {
					// ok
				} else {
					break outer;
				}
			}
			return c;
		}
		throw new RuntimeException("Didn't find constructor! " + clazz.getSimpleName());
	}

	public VisitorNode deserialize(String file) {
		return deserialize(new File(file));
	}

	public VisitorNode deserialize(File file) {
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(file);
			return deserialize(document.getRootElement());

		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}

}
