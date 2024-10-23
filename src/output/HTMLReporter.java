package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import data.ReceiptManager;

public class HTMLReporter extends Reporter {

    BufferedWriter bufferedWriter;

    public HTMLReporter(ReceiptManager a, String fullPathName){
		super(a, fullPathName);
        this.fullPathName += "html";
	}  

    @Override
    public void initializeReport() {
        bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(fullPathName)));
            bufferedWriter.write("<!doctype html>\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content\"text/html; charset=windows-1253\">\n<title>Receipt HTML Report</title>\n</head>\n<body>\n<table>\n");

        } catch (IOException e) {
            //JOptionPane.showMessageDialog(null,"������ ������ �������� ���� ��� ���������� ��� �������");
            JOptionPane.showMessageDialog(null, "Could not report to TXT " + e);
        }
    }

    @Override
    public void report(String text, String value) {
        text = text + ": ";
        try {
            bufferedWriter.write("<tr><td>"+ text + "</td><td>" + value + "</td></tr>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endReport() {
        try {
            bufferedWriter.write("</table>\n</body>\n</html>");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
