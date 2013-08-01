package edu.tum.lua.cli;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.tum.lua.GlobalEnvironment;
import edu.tum.lua.stdlib.NotImplementedFunction;
import edu.tum.lua.types.LuaTable;

public class Documentation {

	private final XPath xpath;
	private final Document xmlDocument;

	public Documentation() throws ParserConfigurationException, MalformedURLException, SAXException, IOException,
			TransformerException {

		URL url = new URL("http://www.lua.org/manual/5.1/manual.html");
		InputStream in = url.openConnection().getInputStream();

		String s = new Scanner(in, "UTF-8").useDelimiter("\\A").next();

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

		/*// Help for debugging
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(xmlDocument);
		StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
		*/

		xpath = XPathFactory.newInstance().newXPath();
	}

	public void printSpecialHelp(String function) {
		System.out.println("Usage:\t" + getUsage(function));
		System.out.println("\nDescription\n" + getLongDescription(function));
	}

	public void printGlobalHelp(GlobalEnvironment ge) {
		System.out.println("LUA");

		for (Entry<Object, Object> pair : ge.getGlobalEnvironment()) {
			if (pair.getValue() instanceof NotImplementedFunction) {
				continue;
			}

			subTableHelper("", pair, 1);
		}
	}

	private void subTableHelper(String parent, Entry<Object, Object> pair, int deep) {
		if (deep > 5) {
			System.out.println("probably recursive Table");
			return;
		}

		System.out.println(new String(new char[deep]).replace("\0", " |") + "-- " + pair.getKey() + "\t\t"
				+ getShortDescription(parent + pair.getKey().toString()));

		if (pair.getKey().toString().equals("_G")) {
			return;
		}

		if (pair.getValue() instanceof LuaTable) {
			Iterator<Entry<Object, Object>> iter = ((LuaTable) pair.getValue()).iterator();

			parent += pair.getKey() + ".";
			while (iter.hasNext()) {
				subTableHelper(parent, iter.next(), deep + 1);
			}
		}
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

	public String getShortDescription(String function) {
		String response = getLongDescription(function);
		response = response.replaceAll("\\p{Space}+", " ").trim().split("\\. ")[0];

		return response;
	}

	public String getLongDescription(String function) {
		String search = "//a[@name=\"pdf-" + function + "\"]/parent::*";

		String response;
		try {
			response = xpath.compile(search).evaluate(xmlDocument);
			return response.replace(getUsage(function), "").trim();
		} catch (XPathExpressionException e) {
			return "";
		}
	}
}
