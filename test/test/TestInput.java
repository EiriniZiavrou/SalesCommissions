package test;

import data.ItemKinds;
import input.Parser;

public class TestInput extends Parser{

    @Override
    public void startInput() {
        return;
    }

    @Override
    public void endInput() {
        return;
    }

    public void setTestValues(ItemKinds kind, int receiptID, String date, double sales, int items,
            String companyName, String companyCountry, String companyCity, String companyStreet,
            int companyStreetNumber, String agentName, int agentAfm) {
        this.kind = kind.name();
        this.receiptID = receiptID;
        this.date = date;
        this.sales = sales;
        this.items = items;
        this.companyName = companyName;
        this.companyCountry = companyCountry;
        this.companyCity = companyCity;
        this.companyStreet = companyStreet;
        this.companyStreetNumber = companyStreetNumber;
    }

    @Override
    public String[] readNameAndAfm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readNameAndAfm'");
    }

    @Override
    public String[] readNextReceipt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readNextReceipt'");
    }
    
}
