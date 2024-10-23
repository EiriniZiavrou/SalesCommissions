package test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import data.ReceiptManager;
import data.ItemKinds;
import data.Receipt;

/**
 * Class to test all* files of the data package
 * 
 * * Tests aren't needed for Company, Address and Receipt since they dont contain methods other than set/get
 */
public class DataTest{

    ReceiptManager sales;
    final double STANDARD_DEVIATION = 0.001;

    @Before
    public void setUp(){
        Receipt rec1 = new Receipt(ItemKinds.Coats, 1, "25/12/23", 2500.2, 35, "Clothes", "Greece", "Athina", "Egnatia", 21);
        Receipt rec2 = new Receipt(ItemKinds.Shirts, 2, "29/12/23", 1100.4, 10, "Clothes", "Greece", "Athina", "Egnatia", 21);
        sales = new ReceiptManager();
        sales.getReceipts().add(rec1);
        sales.getReceipts().add(rec2);
        sales.setName("Mpampis");
        sales.setAfm("123456789");
    }

    @Test
    public void calculateTotalItemsShouldWorkCorrectly(){
        int res = sales.calculateTotalItems();
        assertEquals("Total Items not the same", 45, res);
    }

    @Test
    public void calculateTotalSalesShouldWorkCorrectly(){
        double res = sales.calculateTotalSales();
        assertEquals("Total Sales not the same", 3600.6, res, STANDARD_DEVIATION);
    }

    @Test
    public void calculateItemSalesShouldWorkCorrectly(){
        Receipt rec3 = new Receipt(ItemKinds.Coats, 3, "30/12/23", 3600.2, 15, "Clothes", "Greece", "Athina", "Egnatia", 21);
        sales.getReceipts().add(rec3);
        float res = sales.calculateItemSales(ItemKinds.Coats);
        assertEquals("Total Item Sales not the same", 50, res, STANDARD_DEVIATION);
    }

    @Test
    public void calculateCommissionWithLessThat6000SalesShouldReturnZero(){
        double res = sales.calculateCommission();
        assertEquals("Commision on <6000 not correct", 0, res, STANDARD_DEVIATION);
    }

    @Test
    public void calculateCommissionBetween6001And10000SalesShouldReturnCorrect(){
        Receipt rec = new Receipt(ItemKinds.Coats, 3, "30/12/23", 8000, 35, "Clothes", "Greece", "Athina", "Egnatia", 21);
        sales.getReceipts().clear();
        sales.getReceipts().add(rec);
        double res = sales.calculateCommission();
        assertEquals("Commision on 6001-10000 not correct", 200, res, STANDARD_DEVIATION);
    }

    @Test
    public void calculateCommissionBetween10001And40000SalesShouldReturnCorrect(){
        Receipt rec = new Receipt(ItemKinds.Coats, 3, "30/12/23", 20000, 35, "Clothes", "Greece", "Athina", "Egnatia", 21);
        sales.getReceipts().clear();
        sales.getReceipts().add(rec);
        double res = sales.calculateCommission();
        assertEquals("Commision on 10001-40000 not correct", 2500, res, STANDARD_DEVIATION);
    }

    @Test
    public void calculateCommissionAbove40001SalesShouldReturnCorrect(){
        Receipt rec = new Receipt(ItemKinds.Coats, 3, "30/12/23", 50000, 35, "Clothes", "Greece", "Athina", "Egnatia", 21);
        sales.getReceipts().clear();
        sales.getReceipts().add(rec);
        double res = sales.calculateCommission();
        assertEquals("Commision on >40001 not correct", 7500, res, STANDARD_DEVIATION);
    }

}