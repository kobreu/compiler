package serialization;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import edu.tum.lua.ast.Binop;
import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.Closure;
import edu.tum.lua.ast.Dots;
import edu.tum.lua.ast.Exp;
import edu.tum.lua.ast.FieldNameExp;
import edu.tum.lua.ast.FunctionDef;
import edu.tum.lua.ast.LocalFuncDef;
import edu.tum.lua.ast.Name;
import edu.tum.lua.ast.NameList;
import edu.tum.lua.ast.NumberExp;
import edu.tum.lua.ast.Op;
import edu.tum.lua.ast.TextExp;
import edu.tum.lua.ast.Unop;
import edu.tum.lua.ast.Variable;
import edu.tum.lua.ast.VisitorNode;

public class XMLDeserializer {
	
	int opToInt(String op) {
		if(op.equals("UNM")) return Op.UNM;
		return -1;
	}

	private VisitorNode deserialize(Element ele) {
		ele.normalize();
		try {
			if(ele.getName().equals("Null")) {
				return null;
			}
			if (ele.getName().equals("NumberExp")) {
				NumberExp exp = new NumberExp(Double.valueOf(ele
						.attributeValue("number")));
				return exp;
			} else if (ele.getName().equals("Binop")) {
				Exp child1 = (Exp) deserialize((Element) ele.elements().get(0));
				Exp child2 = (Exp) deserialize((Element) ele.elements().get(1));

				Binop binop = new Binop(child1, opToInt(ele.attributeValue("op")), child2 );
				return binop;
			}	else if (ele.getName().equals("Unop")) {
				Exp child = (Exp) deserialize((Element) ele.elements().get(0));
				Unop unop = new Unop(opToInt(ele.attributeValue("op")), child );
				return unop;
			} else if (ele.getName().equals("Closure")) {
				NameList args = (NameList) deserialize((Element) ele.elements().get(0));
				Block block = (Block) deserialize((Element) ele.elements().get(1));
				return new  Closure(args, Boolean.valueOf(ele.attributeValue("varargs")).booleanValue(), block);
			} else if (ele.getName().equals("Dots")) {
				return new Dots(ele.attributeValue("dots"));
			} else if(ele.getName().equals("FieldNameExp")) {
				Exp exp = (Exp) deserialize((Element) ele.elements().get(0));
				return new FieldNameExp(ele.attributeValue("ident"), exp);
			} else if(ele.getName().equals("FunctionDef")) {
				NameList members = (NameList) deserialize((Element) ele.elements().get(0));
				NameList args = (NameList) deserialize((Element) ele.elements().get(1));
				Block block = (Block) deserialize((Element) ele.elements().get(2));
				return new FunctionDef(ele.attributeValue("ident"), members, args, Boolean.valueOf(ele.attributeValue("varargs")).booleanValue(), block);
			} else if(ele.getName().equals("LocalFuncDef")) {
				NameList args = (NameList) deserialize((Element) ele.elements().get(0));
				Block block = (Block) deserialize((Element) ele.elements().get(1));
				return new LocalFuncDef(ele.attributeValue("ident"), args, Boolean.valueOf(ele.attributeValue("varargs")).booleanValue(), block);
			} else if(ele.getName().equals("Name")) {
				return new Name(ele.attributeValue("name"));
			} else if(ele.getName().equals("TextExp")) {
				return new TextExp(ele.attributeValue("text"));
			} else if(ele.getName().equals("Variable")) {
				return new Variable(ele.attributeValue("var"));
			}
			
			else { // without attributes can be done automatically

				Class clazz = Class.forName("edu.tum.lua.ast."
						+ ele.getName());

				VisitorNode[] deserializedChildren = new VisitorNode[ele
						.elements().size()];
				Class[] constructorClasses = new Class[ele.elements()
						.size()];

				for (int i = 0; i < ele.elements().size(); i++) {
					Element child = (Element) ele.elements().get(i);

					deserializedChildren[i] = deserialize(child);
					if(deserializedChildren[i] != null) {
						constructorClasses[i] = deserializedChildren[i].getClass();
					} else {
						constructorClasses[i] = Object.class;
					}
				}
				
				Constructor constructor = findConstructor(clazz, constructorClasses);

				VisitorNode deserialized = (VisitorNode) constructor
						.newInstance(deserializedChildren);

				return deserialized;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private Constructor findConstructor(Class clazz, Class[] constructorClasses) {
		Constructor[] constructors = clazz.getDeclaredConstructors();
		outer: for(Constructor c : constructors) {
			if(c.getParameterTypes().length != constructorClasses.length) continue;
			int index = 0;
			for(Class paramClass : c.getParameterTypes()) {
				Class match = constructorClasses[index++];
				if(match == Object.class || paramClass == match || paramClass == match.getSuperclass()) {
					// ok
				} else {
					break outer;
				}
			}
			return c;
		}
		throw new RuntimeException("Didn't find constructor!");
	}

	public VisitorNode deserialize(String file) {
		return deserialize(new File(file));
	}

	public VisitorNode deserialize(File file) {
        SAXReader reader = new SAXReader();
        Document document;
		try {
			document = reader.read(file);
			return (Block) deserialize(document.getRootElement());

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
