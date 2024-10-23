package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.ItemKinds;
import data.Receipt;
import input.HTMLParser;
import input.Parser;
import input.ReceiptUpdater;
import input.ReceiptUpdaterHTML;
import input.ReceiptUpdaterTXT;
import input.ReceiptUpdaterXML;
import input.TXTParser;
import input.XMLParser;

import java.io.IOException;

/**
 * Class to test all files of the input package
 */

public class InputTest {

    final double STANDARD_DEVIATION = 0.001;
    final static String TXT_FILE_PATH = "java/test/resources/input/test-receipt.txt";
    final static String XML_FILE_PATH = "java/test/resources/input/test-receipt.xml";
    final static String HTML_FILE_PATH = "java/test/resources/input/test-receipt.html";
    final static String APPENDED_TXT_FILE_PATH = "java/test/resources/input/correct_appended_file.txt";
    final static String APPENDED_XML_FILE_PATH = "java/test/resources/input/correct_appended_file.xml";
    final static String APPENDED_HTML_FILE_PATH = "java/test/resources/input/correct_appended_file.html";
    List<Receipt> correctReceipts = new ArrayList<>();
    static String originalTxtConents;
    static String originalXmlConents;
    static String originalHtmlConents;
    
    @BeforeClass
    public static void beforeClass(){
        //Set up file restoration
        StringBuilder originalTxtConentsBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(TXT_FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) originalTxtConentsBuilder.append(line).append("\n");
        } catch (IOException e){
            e.printStackTrace();
        }
        originalTxtConents = originalTxtConentsBuilder.toString();

        StringBuilder originalXmlConentsBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(XML_FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) originalXmlConentsBuilder.append(line).append("\n");
        } catch (IOException e){
            e.printStackTrace();
        }
        originalXmlConents = originalXmlConentsBuilder.toString();

        StringBuilder originalHtmlConentsBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(HTML_FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) originalHtmlConentsBuilder.append(line).append("\n");
        } catch (IOException e){
            e.printStackTrace();
        }
        originalHtmlConents = originalHtmlConentsBuilder.toString();
    }

    @Before
    public void setUp(){
        //Set up test receipts
        Receipt rec1 = new Receipt(ItemKinds.Coats, 1, "25/2/2014", 2000, 10, "Hand Made Clothes", "Greece", "Ioannina", "Kaloudi", 10);
        Receipt rec2 = new Receipt(ItemKinds.Skirts, 2, "25/2/2014", 4000, 10, "Clothes and Co", "Greece", "Ioannina", "Kaloudi", 10);
        Receipt rec3 = new Receipt(ItemKinds.Shirts, 3, "25/2/2014", 4000, 10, "Unlimited Apparel Inc.", "Greece", "Ioannina", "Kaloudi", 10);
        Receipt rec4 = new Receipt(ItemKinds.Trousers, 4, "25/2/2014", 1000, 10, "Men's Apparel", "Greece", "Ioannina", "Kaloudi", 10);
        correctReceipts.add(rec1);
        correctReceipts.add(rec2);
        correctReceipts.add(rec3);
        correctReceipts.add(rec4);
    }

    @After
    public void after(){
        //restore files
        try (FileWriter writer = new FileWriter(TXT_FILE_PATH)) {
            writer.write(originalTxtConents);
        } catch (IOException e){
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(XML_FILE_PATH)) {
            writer.write(originalXmlConents);
        } catch (IOException e){
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(HTML_FILE_PATH)) {
            writer.write(originalHtmlConents);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void addReceiptShouldWorkCorrectly(){
        TestInput in = new TestInput();
        Receipt rec = new Receipt(ItemKinds.Coats, 1, "1/1/1990", 5000, 50, "Men Apparel", "Costa Rica", "my city", "Papaguevo", 2);
        in.setTestValues(ItemKinds.Coats, 1, "1/1/1990", 5000, 50, "Men Apparel", "Costa Rica", "my city", "Papaguevo", 2, "Mamalakis", 123);
        in.addAgent();
        in.addReceipt();
        assertEquals("Receipt list length not correct", 1, in.getAgent().getReceipts().size());
        assertTrue("Receipt not the same", rec.equals(in.getAgent().getReceipts().get(0)));
    }

    @Test
    public void TXTInputReadShouldWorkCorrectly(){
        Parser txt = new TXTParser(new File(TXT_FILE_PATH));
        txt.readFile();
        List<Receipt> receipts = txt.getAgent().getReceipts();
        assertEquals("Receipt list length not correct", 4, receipts.size());
        for (int i = 0; i < correctReceipts.size(); i++) {
            assertTrue(String.format("Receipt %d (ID: %d) from TXT not the same", i, receipts.get(i).getReceiptID()), receipts.get(i).equals(correctReceipts.get(i)));
        }
    }

    @Test
    public void XMLInputReadShouldWorkCorrectly(){
        Parser xml = new XMLParser(new File(XML_FILE_PATH));
        xml.readFile();
        List<Receipt> receipts = xml.getAgent().getReceipts();
        assertEquals("Receipt list length not correct", 4, receipts.size());
        for (int i = 0; i < correctReceipts.size(); i++) {
            assertTrue(String.format("Receipt %d (ID: %d) from XML not the same", i, receipts.get(i).getReceiptID()), receipts.get(i).equals(correctReceipts.get(i)));
        }
    }

    @Test
    public void HTMLInputReadShouldWorkCorrectly(){
        Parser html = new HTMLParser(new File(HTML_FILE_PATH));
        html.readFile();
        List<Receipt> receipts = html.getAgent().getReceipts();
        assertEquals("Receipt list length not correct", 4, receipts.size());
        for (int i = 0; i < correctReceipts.size(); i++) {
            assertTrue(String.format("Receipt %d (ID: %d) from XML not the same", i, receipts.get(i).getReceiptID()), receipts.get(i).equals(correctReceipts.get(i)));
        }
    }

    @Test
    public void ReceiptUpdaterTXTShouldWorkCorrectly(){
        ReceiptUpdater txt = new ReceiptUpdaterTXT();
        Receipt recAppend = new Receipt(ItemKinds.Coats, 5, "25/2/2014", 1000, 5, "Handmade Clothes", "Greece", "Ioannina", "Kaloudi", 10);
        txt.setFileToAppend(new File(TXT_FILE_PATH));
        txt.setReceipt(recAppend);
        txt.appendFile();
        BufferedReader correctReader = null;
        BufferedReader reader = null;
        try {
            correctReader = new BufferedReader(new FileReader(APPENDED_TXT_FILE_PATH)); 
            reader = new BufferedReader(new FileReader(TXT_FILE_PATH)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        String correctLine;
        try {
            while((correctLine = correctReader.readLine()) != null){
                String line = reader.readLine();
                assertEquals("File line on txt was not the same", line, correctLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ReceiptUpdaterXMLShouldWorkCorrectly(){
        ReceiptUpdater xml = new ReceiptUpdaterXML();
        Receipt recAppend = new Receipt(ItemKinds.Coats, 5, "25/2/2014", 1000, 5, "Handmade Clothes", "Greece", "Ioannina", "Kaloudi", 10);
        xml.setFileToAppend(new File(XML_FILE_PATH));
        xml.setReceipt(recAppend);
        
        xml.appendFile();
        BufferedReader correctReader = null;
        BufferedReader reader = null;
        try {
            correctReader = new BufferedReader(new FileReader(APPENDED_XML_FILE_PATH)); 
            reader = new BufferedReader(new FileReader(XML_FILE_PATH)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String correctLine;
        try {
            while((correctLine = correctReader.readLine()) != null){
                String line = reader.readLine();
                assertEquals("File line on xml was not the same", line, correctLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    @Test
    public void ReceiptUpdaterHTMLShouldWorkCorrectly(){
        ReceiptUpdater html = new ReceiptUpdaterHTML();
        Receipt recAppend = new Receipt(ItemKinds.Coats, 5, "25/2/2014", 1000, 5, "Handmade Clothes", "Greece", "Ioannina", "Kaloudi", 10);
        html.setFileToAppend(new File(HTML_FILE_PATH));
        html.setReceipt(recAppend);
        html.appendFile();
        BufferedReader correctReader = null;
        BufferedReader reader = null;
        try {
            correctReader = new BufferedReader(new FileReader(APPENDED_HTML_FILE_PATH)); 
            reader = new BufferedReader(new FileReader(HTML_FILE_PATH)); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String correctLine;
        try {
            while((correctLine = correctReader.readLine()) != null){
                String line = reader.readLine();
                assertEquals("File line on html was not the same", correctLine, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
