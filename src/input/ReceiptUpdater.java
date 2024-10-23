package input;

import java.io.File;
import data.Receipt;


public abstract class ReceiptUpdater {

	protected File fileToAppend;
	protected Receipt receipt;
	
	public void appendFile(){
		initializeWriting();
		write("Receipt ID", Integer.toString(receipt.getReceiptID()));
		write("Date", receipt.getDate());
		write("Kind", receipt.getKind().name());
		write("Sales", Double.toString(receipt.getSales()));
		write("Items", Integer.toString(receipt.getItems()));
		write("Company", receipt.getCompany().getName());
		write("Country", receipt.getCompany().getCompanyAddress().getCountry());
		write("City", receipt.getCompany().getCompanyAddress().getCity());
		write("Street", receipt.getCompany().getCompanyAddress().getStreet());
		write("Number", Integer.toString(receipt.getCompany().getCompanyAddress().getStreetNumber()));
		endWriting();
	};
	public abstract void setFileToAppend(File fileToAppend); 
	public abstract void setReceipt(Receipt receipt);
	public abstract void write(String text, String value);
	public abstract void initializeWriting();
	public abstract void endWriting();
}