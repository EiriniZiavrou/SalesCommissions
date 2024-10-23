package data;

import input.ReceiptUpdater;
import input.ReceiptUpdaterHTML;
import input.ReceiptUpdaterTXT;
import input.ReceiptUpdaterXML;
import java.util.ArrayList;
import java.util.List;

public class ReceiptManager {
	private String name;
	private String afm;
	private List<Receipt> allReceipts;
	private ReceiptUpdater fileAppender;
	
	
	public ReceiptManager(){
		allReceipts = new ArrayList<Receipt>();
	}
	public void setFileType(String fileType) {
		if(fileType.equals("TXT")){
			fileAppender = new ReceiptUpdaterTXT();
		}	
		else if (fileType.equals("XML")){
			fileAppender = new ReceiptUpdaterXML();
		}	
		else{
			fileAppender = new ReceiptUpdaterHTML();
		}
	}
	public List<Receipt> getReceipts(){
		return allReceipts;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAfm() {
		return afm;
	}
	public void setAfm(String afm) {
		this.afm = afm;
	}
	public double calculateTotalSales(){
		return allReceipts.stream().mapToDouble(r -> r.getSales()).sum();
	}
	public int calculateTotalItems(){
		return allReceipts.stream().mapToInt(r -> r.getItems()).sum();
	}
	public float calculateItemSales(ItemKinds kind){
		return (float) allReceipts.stream().filter(r -> r.getKind().equals(kind)).mapToDouble(r -> r.getItems()).sum();
	}
	public double calculateCommission(){
		double commission = 0;
		if( this.calculateTotalSales() > 6000 && this.calculateTotalSales()<= 10000){
			commission = 0.1*(calculateTotalSales()-6000);
		}
		else if(this.calculateTotalSales() > 10000 && this.calculateTotalSales() <= 40000 ){
			commission = (((calculateTotalSales() - 10000) * 0.15) + (10000*0.1));			
		}
		else if(this.calculateTotalSales() > 40000 ) {
			commission = 10000*0.1 + 30000*0.15 + (calculateTotalSales() - 40000)*0.2;			
		}
		return commission;
	}
	public ReceiptUpdater getFileAppender() {
		return fileAppender;
	}
}