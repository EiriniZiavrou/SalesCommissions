package output;

import data.ReceiptManager;
import data.ItemKinds;

public abstract class Reporter {

	protected ReceiptManager agent;
	protected String fullPathName;

	public Reporter(ReceiptManager a, String fullPathName){
		agent = a;
		this.fullPathName = fullPathName.substring(0, fullPathName.lastIndexOf("\\")) + "\\" + a.getAfm() + "_SALES.";
	}
	
	public void saveFile(){
		initializeReport();
		report("Name", agent.getName());
		report("AFM", agent.getAfm());
		report("Total Sales", Double.toString(agent.calculateTotalSales()));
		report("Trousers Sales", Double.toString(agent.calculateItemSales(ItemKinds.Trousers)));
		report("Skirts Sales", Double.toString(agent.calculateItemSales(ItemKinds.Skirts)));
		report("Shirts Sales", Double.toString(agent.calculateItemSales(ItemKinds.Shirts)));
		report("Coats Sales", Double.toString(agent.calculateItemSales(ItemKinds.Coats)));
		report("Commission", Double.toString(agent.calculateCommission()));
		endReport();
	}

	public abstract void initializeReport();
	public abstract void report(String text, String value);
	public abstract void endReport();
}