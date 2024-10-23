package data;

public class Receipt {
	private int receiptId;
	private String date;
	private double sales;
	private int items;
	private Company company;
	private ItemKinds kind;
		
	
	public Receipt(ItemKinds kind){	
		this.kind = kind;
		company  = new Company();
	}

	public Receipt(
		ItemKinds kind,
		int receiptID,
		String date,
		double sales,
		int items,
		String companyName,
		String companyCountry,
		String companyCity,
		String companyStreet,
		int companyStreetNumber
	){
		this.kind = kind;
		this.receiptId = receiptID;
		this.date = date;
		this.sales = sales;
		this.items = items;
		company = new Company();
		company.setName(companyName);
		company.getCompanyAddress().setCountry(companyCountry);
		company.getCompanyAddress().setCity(companyCity);
		company.getCompanyAddress().setStreet(companyStreet);
		company.getCompanyAddress().setStreetNumber(companyStreetNumber);
	}
	
	public Company getCompany(){
		return company;
	}

	public ItemKinds getKind() {
		return kind;
		
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public int getItems() {
		return this.items;
	}

	public void setReceiptID(int id) {
		this.receiptId = id;		
	}

	public int getReceiptID() {
		return receiptId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;			
	}
	
	public boolean equals(Receipt rec){
		if(receiptId!=rec.getReceiptID()) return false;
		if(!date.equals(rec.getDate())) return false;
		if(sales!=rec.getSales()) return false;
		if(items!=rec.getItems()) return false;
		if(!company.equals(rec.getCompany())) return false;
		if(!kind.equals(rec.getKind())) return false;
		return true;
	}
}
