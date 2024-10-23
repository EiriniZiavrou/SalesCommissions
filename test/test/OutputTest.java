package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.ReceiptManager;
import data.ItemKinds;
import data.Receipt;
import output.HTMLReporter;
import output.TXTReporter;
import output.XMLReporter;

/**
 * Class to test all files of the output package
 */
public class OutputTest {

     static ReceiptManager agent;
    
    @BeforeClass
    public static void beforeClass(){
        Receipt receipt1 = new Receipt(ItemKinds.Coats,1,"10/12/2023",5519,87,"KitsLand","Greece","Ioannina","Pyrsinela",8);
        Receipt receipt2 = new Receipt(ItemKinds.Coats,2,"12/12/2023",650,16,"KitsLand","Greece","Ioannina","Pyrsinela",8);
        Receipt receipt3 = new Receipt(ItemKinds.Shirts,3,"12/12/2023",1085,27,"KitsLand","Greece","Ioannina","Pyrsinela",8);
        Receipt receipt4 = new Receipt(ItemKinds.Trousers,4,"15/12/2023",5078,156,"KitsLand","Greece","Ioannina","Pyrsinela",8);
        Receipt receipt5 = new Receipt(ItemKinds.Trousers,5,"25/12/2023",6500,18,"KitsLand","Greece","Ioannina","Pyrsinela",8);
        agent = new ReceiptManager();
        agent.setName("Kitsos Kitsopoulos");
        agent.setAfm("123456789");
        agent.getReceipts().add(receipt1);
        agent.getReceipts().add(receipt2);
        agent.getReceipts().add(receipt3);
        agent.getReceipts().add(receipt4);
        agent.getReceipts().add(receipt5);
    }

    @Before
    public void setUp(){
        
        
    }

    @Test
    public void ReportToTXTShouldWorkCorrectly(){
        TXTReporter reportTXT = new TXTReporter(agent, "java\\test\\resources\\output\\thisWillGetChanged.txt");
        reportTXT.saveFile();
        BufferedReader correctReader = null;
        BufferedReader reader = null;
        try {
            correctReader = new BufferedReader(new FileReader("java\\test\\resources\\output\\correct_output.txt")); 
            reader = new BufferedReader(new FileReader("java\\test\\resources\\output\\123456789_SALES.txt")); 
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
    public void ReportToXMLShouldWorkCorrectly(){
        XMLReporter reportXML = new XMLReporter(agent, "java\\test\\resources\\output\\thisWillGetChanged.xml");
        reportXML.saveFile();
        BufferedReader correctReader = null;
        BufferedReader reader = null;
        try {
            correctReader = new BufferedReader(new FileReader("java\\test\\resources\\output\\correct_output.xml")); 
            reader = new BufferedReader(new FileReader("java\\test\\resources\\output\\123456789_SALES.xml")); 
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
    public void ReportToHTMLShouldWorkCorrectly(){
        HTMLReporter reportHTML = new HTMLReporter(agent, "java\\test\\resources\\output\\thisWillGetChanged.html");
        reportHTML.saveFile();
        BufferedReader correctReader = null;
        BufferedReader reader = null;
        try {
            correctReader = new BufferedReader(new FileReader("java\\test\\resources\\output\\correct_output.html")); 
            reader = new BufferedReader(new FileReader("java\\test\\resources\\output\\123456789_SALES.html")); 
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
}
