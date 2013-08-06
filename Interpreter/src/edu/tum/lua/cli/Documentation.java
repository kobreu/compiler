package edu.tum.lua.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.stdlib.NotImplementedFunction;
import edu.tum.lua.stdlib.ToString;
import edu.tum.lua.types.LuaFunctionNative;
import edu.tum.lua.types.LuaTable;
import edu.tum.lua.types.LuaType;

public class Documentation {

	private final XPath xpath;
	private final Document xmlDocument;
	private final int terminalWidth;

	public Documentation(int termwidth) throws ParserConfigurationException, MalformedURLException, IOException {

		this.terminalWidth = termwidth;

		URL url = new URL("http://www.lua.org/manual/5.1/manual.html");
		InputStream in = url.openConnection().getInputStream();

		Scanner sc = new Scanner(in, "UTF-8");
		String s = sc.useDelimiter("\\A").next();

		sc.close();
		in.close();

		s = s.replace("<hr>", "</div><div>").replace("<h2>", "</div><h2>").replace("</h2>", "</h2><div>")
				.replace("<h1>", "</div><h1>").replace("</h1>", "</h1><div>").replace("<br/>", "\n")
				.replaceAll("[ \t]+", " ").replace("<code>", "").replace("</code>", "").replace("<em>", "")
				.replace("</em>", "").replace("<p>", "").replace("</p>", "").replace("<h3>", "").replace("</h3>", "")
				.replace("<b>", "").replace("</b>", "").replace("<sup>", "^(").replace("</sup>", ")")
				.replace("<pre>", "").replace("</pre>", "").replace("&quot;", "'").replace("&nbsp;", " ")
				.replace("\"", "'");

		TagNode tagNode = new HtmlCleaner().clean(s);
		xmlDocument = new org.htmlcleaner.DomSerializer(new CleanerProperties()).createDOM(tagNode);

		xpath = XPathFactory.newInstance().newXPath();
	}

	public void printHelp() {
		System.out.println("Lua Help\n\npossible Help-Functions in the Interpreter:");
		formatPrint("--help", "show this help");
		formatPrint("--list",
				"list all available lua functions (functions from the standard library with a short description)");
		formatPrint("--list <function>",
				"show a more detailed help for the specific function (only functions from standard library)");
		formatPrint("--env", "show all members, their types and their values of the interpreter's environment");
	}

	public void listFunctions(GlobalEnvironment ge) {
		System.out.println("Functions");

		for (Entry<Object, Object> pair : ge) {
			if (pair.getValue() instanceof NotImplementedFunction || pair.getValue() == null) {
				continue;
			}
			listFunctionsRecursive("", pair, 1);
		}

		System.out.println("\nSee --list <function> for more details");
	}

	private void listFunctionsRecursive(String parent, Entry<Object, Object> pair, int deep) {

		if (deep > 3) {
			return;
		}

		parent += ToString.toString(pair.getKey());

		switch (LuaType.getTypeOf(pair.getValue())) {
		case FUNCTION:
			if (pair.getValue() instanceof LuaFunctionNative) {
				formatPrint(parent, getShortDescription(parent));
			} else {
				System.out.println(parent + "\t\tuser defined function");
			}
			return;
		case TABLE:
			if (!ToString.toString(pair.getKey()).equals("_G")) {
				parent += ".";
				deep++;
				Iterator<Entry<Object, Object>> iter = ((LuaTable) pair.getValue()).iterator();
				while (iter.hasNext()) {
					listFunctionsRecursive(parent, iter.next(), deep);
				}
			}
			return;
		default:
			return;
		}
	}

	public void listSpecialFunction(String function) {
		System.out.println("Usage:\t" + getUsage(function));
		System.out.println("\nDescription\n" + getLongDescription(function));
	}

	private String getUsage(String function) {
		String search = "//a[@name=\"pdf-" + function + "\"]/text()";

		String response;
		try {
			response = xpath.compile(search).evaluate(xmlDocument);
			return response.replaceAll("\\p{Space}+", " ").trim();
		} catch (XPathExpressionException e) {
			return "";
		}
	}

	private String getShortDescription(String function) {
		String response = getLongDescription(function);
		response = response.replaceAll("\\p{Space}+", " ").split("\\. ")[0];

		return response;
	}

	private String getLongDescription(String function) {
		String search = "//a[@name=\"pdf-" + function + "\"]/parent::*";

		String response;
		try {
			response = xpath.compile(search).evaluate(xmlDocument);
			return response.replace(getUsage(function), "").trim().replace("\n\n\n", "\n\n");
		} catch (XPathExpressionException e) {
			return "";
		}
	}

	private void formatPrint(String header, String content) {
		String temp = header + new String(new char[24 - header.length()]).replace("\0", " ");
		String[] words = content.split(" ");

		for (String word : words) {
			if ((word.length() + temp.length()) > terminalWidth - 2) {
				System.out.println(temp);
				temp = new String(new char[24]).replace("\0", " ");
			}
			temp += word + " ";
		}

		System.out.println(temp);
	}

	public void listEnvironment(GlobalEnvironment ge) {
		formatPrint("Name", "Type\t\tValue");

		for (Entry<Object, Object> pair : ge) {
			if (pair.getValue() instanceof NotImplementedFunction || pair.getValue() == null) {
				continue;
			}

			switch (LuaType.getTypeOf(pair.getValue())) {
			case STRING:
			case NUMBER:
			case BOOLEAN:
				formatPrint(ToString.toString(pair.getKey()),
						LuaType.getTypeOf(pair.getValue()) + "\t\t" + ToString.toString(pair.getValue()));
				break;
			case TABLE:
			case USERDATA:
			case THREAD:
				formatPrint(ToString.toString(pair.getKey()), LuaType.getTypeOf(pair.getValue()).toString());
				break;
			case NIL:
			case FUNCTION:
			default:
				break;
			}
		}
	}

	public void deleteDocumentation() {
		new File(System.getProperty("java.io.tmpdir") + "lua_documentation.data").delete();
	}
}
