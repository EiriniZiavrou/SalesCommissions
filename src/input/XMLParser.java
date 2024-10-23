package input;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLParser extends Parser {

	Document doc;
	Node agentNode;
	Node currentNode;
 
	public XMLParser(File receiptFileXML ){
		inputFile = receiptFileXML;
	}
	
	@Override
	public void startInput() {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(inputFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			//JOptionPane.showMessageDialog(null,"�������� ������ �������� ���� �� �������� ��� �������");
			JOptionPane.showMessageDialog(null, "Could not open XML file. " + e);
		}
		doc.getDocumentElement().normalize();
		agentNode = doc.getElementsByTagName("Agent").item(0);
		currentNode = agentNode.getFirstChild();
		
	}


	@Override
	public void endInput() {
		return; //nothing is needed here
	}

	@Override
	public String[] readNameAndAfm() {
		currentNode = getNextNonEmptyNode(currentNode);
		String name = currentNode.getTextContent().trim();
		currentNode = getNextNonEmptyNode(currentNode);
		String afm = currentNode.getTextContent().trim();
		currentNode = getNextNonEmptyNode(currentNode).getFirstChild();
		currentNode = getNextNonEmptyNode(currentNode);
		System.out.println("Ended at node " + currentNode.getNodeName());
		return new String[]{name, afm};
	}

	@Override
	public String[] readNextReceipt() {
		if (currentNode == null) return new String[0];
		String[] data = new String[10];
		Node dataNode = currentNode.getFirstChild();
		dataNode = getNextNonEmptyNode(dataNode);
		for (int i = 0; i < data.length; i++) {
			data[i] = dataNode.getTextContent().trim();
			dataNode = getNextNonEmptyNode(dataNode);
		}
		currentNode = getNextNonEmptyNode(currentNode);
		return data;
	}

	private Node getNextNonEmptyNode(Node currentNode){
		while(true){
			currentNode = currentNode.getNextSibling();
			if (currentNode == null) return null;
			if (!currentNode.getTextContent().trim().equals("")) return currentNode;
		}
	}
}