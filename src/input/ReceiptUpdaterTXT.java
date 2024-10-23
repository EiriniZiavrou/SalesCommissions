package input;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import data.Receipt;

public class ReceiptUpdaterTXT extends ReceiptUpdater{

	FileWriter fileWriter;

	public void setFileToAppend(File fileToAppend) {
		this.fileToAppend = fileToAppend;
	}

	@Override
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public void write(String text, String value){
		text = text+ ": ";
		try {
			fileWriter.write(text + value + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeWriting() {
		try {
			fileWriter = new FileWriter(fileToAppend,true);
			fileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void endWriting() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Added to TXT ( " + fileToAppend.getAbsolutePath() + " )");
	}
}