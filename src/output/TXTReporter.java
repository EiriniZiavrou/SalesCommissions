package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import data.ReceiptManager;


public class TXTReporter extends Reporter{

	BufferedWriter bufferedWriter;
    
	public TXTReporter(ReceiptManager a, String fullPathName){
		super(a, fullPathName);
        this.fullPathName += "txt";
	}  
	
    @Override
    public void initializeReport() {
        bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(fullPathName)));
        } catch (IOException e) {
            //JOptionPane.showMessageDialog(null,"������ ������ �������� ���� ��� ���������� ��� �������");
            JOptionPane.showMessageDialog(null, "Could not report to TXT " + e);
        }
    }

    @Override
    public void endReport() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void report(String text, String value) {
        text = text + ": ";
        try {
            bufferedWriter.write(text + value);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}