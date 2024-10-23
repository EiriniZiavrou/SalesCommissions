package input;


import java.io.File;

import data.ReceiptManager;
import data.ItemKinds;
import data.Receipt;


public abstract class Parser {
	
	protected ReceiptManager agent;
	protected File inputFile;
	protected String inputFilePath;
	protected String name;
	protected String afm;
	protected int receiptID;
	protected String date;
	protected String kind;
	protected double sales;
	protected int items;
	protected String companyName;
	protected String companyCountry;
	protected String companyCity;
	protected String companyStreet;
	protected int companyStreetNumber;

	public abstract void startInput();
	public abstract String[] readNextReceipt();
	public abstract String[] readNameAndAfm();
	public abstract void endInput();
	// public abstract boolean hasReceipt();

	public void readFile(){
		final int RECEIPT_ID_INDEX = 0;
		final int DATE_INDEX = 1;
		final int KIND_INDEX = 2;
		final int SALES_INDEX = 3;
		final int ITEMS_INDEX = 4;
		final int COMPANY_NAME_INDEX = 5;
		final int COMPANY_COUNTRY_INDEX = 6;
		final int COMPANY_CITY_INDEX = 7;
		final int COMPANY_STREET_INDEX = 8;
		final int COMPANY_STREET_NUMBER_INDEX = 9;
		startInput();
		String[] nameAndAfm = readNameAndAfm();
		name = nameAndAfm[0];
		afm = nameAndAfm[1];
		addAgent();
		System.out.println("Name: " + name + " AFM: " + afm);
		while(true){
			String[] rec = readNextReceipt();
			for (String string : rec) System.out.print(string + ", ");
			System.out.println("||Length: " + rec.length);
			if (rec.length == 0) break;
			receiptID = Integer.parseInt(rec[RECEIPT_ID_INDEX]);
			date = rec[DATE_INDEX];
			kind = rec[KIND_INDEX];
			sales = Double.parseDouble(rec[SALES_INDEX]);
			items = Integer.parseInt(rec[ITEMS_INDEX]);
			companyName = rec[COMPANY_NAME_INDEX];
			companyCountry = rec[COMPANY_COUNTRY_INDEX];
			companyCity = rec[COMPANY_CITY_INDEX];
			companyStreet = rec[COMPANY_STREET_INDEX];
			companyStreetNumber = Integer.parseInt(rec[COMPANY_STREET_NUMBER_INDEX]);
			addReceipt();
		}
		endInput();
	}

	public Parser() {
		agent = new ReceiptManager();
		kind  = new String("");
	}

	public void addAgent() {
		agent.setName(name);
		agent.setAfm(afm);
	}

	public void addReceipt(){
		Receipt receipt = new Receipt(ItemKinds.valueOf(kind.toString()),
			receiptID,
			date,
			sales,
			items,
			companyName,
			companyCountry,
			companyCity,
			companyStreet,
			companyStreetNumber);
		agent.getReceipts().add(receipt);
	}

	public ReceiptManager getAgent() {
		return agent;
	}
}