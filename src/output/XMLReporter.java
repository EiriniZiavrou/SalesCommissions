package output;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import data.ReceiptManager;

public class XMLReporter extends Reporter{

	Document document;
	Element agentElem;
	TransformerFactory transformerFactory;
		
	public XMLReporter(ReceiptManager a, String fullPathName){
		super(a, fullPathName);
		this.fullPathName += "xml";
	}	

	@Override
	public void initializeReport() {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		transformerFactory = TransformerFactory.newInstance();
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		document = documentBuilder.newDocument();
		agentElem = document.createElement("Agent");
		document.appendChild(agentElem);
	}

	@Override
	public void endReport() {
		//TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		DOMSource domSource = new DOMSource(document);
		System.out.println("Name: " + fullPathName);
		StreamResult streamResult = new StreamResult(new File(fullPathName));
		try {
			transformer.transform(domSource, streamResult);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void report(String text, String value) {
		text = text.replaceAll(" ", "");
		Element elem = document.createElement(text);
		elem.appendChild(document.createTextNode(value));
		agentElem.appendChild(elem);
	}

}