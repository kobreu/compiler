package serialization;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;















import edu.tum.lua.ast.Block;
import edu.tum.lua.ast.Chunk;
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

	public VisitorNode deserialize(Element ele) {
		try {
			if (ele.getTagName().equals("NumberExp")) {
				NumberExp exp = new NumberExp(Double.valueOf(ele
						.getAttribute("number")));
				return exp;
			} else if (ele.getTagName().equals("Unop")) {
				Exp child = (Exp) deserialize((Element) ele.getChildNodes().item(0));
				Unop unop = new Unop(opToInt(ele.getAttribute("op")), child );
				return unop;
			} else if (ele.getTagName().equals("Closure")) {
				NameList args = (NameList) deserialize((Element) ele.getChildNodes().item(0));
				Block block = (Block) deserialize((Element) ele.getChildNodes().item(1));
				return new  Closure(args, Boolean.valueOf(ele.getAttribute("varargs")).booleanValue(), block);
			} else if (ele.getTagName().equals("Dots")) {
				return new Dots(ele.getAttribute("dots"));
			} else if(ele.getTagName().equals("FieldNameExp")) {
				Exp exp = (Exp) deserialize((Element) ele.getChildNodes().item(0));
				return new FieldNameExp(ele.getAttribute("ident"), exp);
			} else if(ele.getTagName().equals("FunctionDef")) {
				NameList members = (NameList) deserialize((Element) ele.getChildNodes().item(0));
				NameList args = (NameList) deserialize((Element) ele.getChildNodes().item(1));
				Block block = (Block) deserialize((Element) ele.getChildNodes().item(2));
				return new FunctionDef(ele.getAttribute("ident"), members, args, Boolean.valueOf(ele.getAttribute("varargs")).booleanValue(), block);
			} else if(ele.getTagName().equals("LocalFuncDef")) {
				NameList args = (NameList) deserialize((Element) ele.getChildNodes().item(0));
				Block block = (Block) deserialize((Element) ele.getChildNodes().item(1));
				return new LocalFuncDef(ele.getAttribute("ident"), args, Boolean.valueOf(ele.getAttribute("varargs")).booleanValue(), block);
			} else if(ele.getTagName().equals("Name")) {
				return new Name(ele.getAttribute("name"));
			} else if(ele.getTagName().equals("TextExp")) {
				return new TextExp(ele.getAttribute("text"));
			} else if(ele.getTagName().equals("Variable")) {
				return new Variable(ele.getAttribute("var"));
			}
			
			else { // without attributes can be done automatically

				Class clazz = Class.forName("edu.tum.lua.ast."
						+ ele.getTagName());

				VisitorNode[] deserializedChildren = new VisitorNode[ele
						.getChildNodes().getLength()];
				Class[] constructorClasses = new Class[ele.getChildNodes()
						.getLength()];

				for (int i = 0; i < ele.getChildNodes().getLength(); i++) {
					Element child = (Element) ele.getChildNodes().item(i);

					deserializedChildren[i] = deserialize(child);
					constructorClasses[i] = deserializedChildren[i].getClass();
				}

				Constructor constructor = clazz.getDeclaredConstructor(constructorClasses);

				VisitorNode deserialized = (VisitorNode) constructor
						.newInstance(deserializedChildren);

				return deserialized;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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

	public Chunk deserialize(String file) {
		return deserialize(new File(file));
	}

	public Chunk deserialize(File file) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();
		System.out.println("Root element :"
				+ doc.getDocumentElement().getNodeName());

		return (Chunk) deserialize(doc.getDocumentElement());
	}

}
