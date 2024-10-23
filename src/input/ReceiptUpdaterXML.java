package input;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import data.Receipt;

public class ReceiptUpdaterXML  extends ReceiptUpdater{

	Document doc;
	Element receiptElem;

	public void setFileToAppend(File fileToAppend) {
		this.fileToAppend = fileToAppend;
	}
	
	@Override
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public void write(String text, String value){
		text = text.replaceAll(" ", "");//remove spaces
		Element receiptIDElem = doc.createElement(text);
		receiptIDElem.appendChild(doc.createTextNode(value));
		receiptElem.appendChild(receiptIDElem);
	}	

	@Override
	public void initializeWriting(){
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		doc = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(fileToAppend);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	
		Node agent = doc.getFirstChild();

		receiptElem = doc.createElement("Receipt");
		agent.appendChild(receiptElem);		
	}

	@Override
	public void endWriting(){
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
			
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(fileToAppend);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		System.out.println("Added to XML ( " + fileToAppend.getAbsolutePath() + " )");
	}
}