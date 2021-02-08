package com.kevinmitcham.product.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.kevinmitcham.product.ProductRecord;
import com.kevinmitcham.product.InputRow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasePriceTests {
    BasePrice basePrice = new BasePrice();
    ProductRecord productRecord = new ProductRecord("anyId");

    @Test public void testRoundToPlaces(){
        double[][] cases = { 
            { 1.00004, 1.0000 },
            { 1.00005, 1.0001 },
            { 1.00009, 1.0001 },
            { -1.00004, -1.0000 },
            { -1.00005, -1.0001 },
        };
        for (double[] pair: cases){
            assertEquals(pair[1], basePrice.roundToPlaces(pair[0], 4));
        }
    }
    @Test
	public void testNegativePrice() {
        InputRow inputLine = new InputRow(
        //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
         "80000001 Negative price                                              -0000666 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz");
        String expectedDisplay = "-$6.66";
        Double expectedCalc = -6.66;

        basePrice.addFieldToProduct(inputLine, productRecord);

        assertEquals(expectedDisplay, productRecord.getRegularDisplayPrice(),"Negative display");
        assertEquals(expectedCalc, productRecord.getRegularCalculatorPrice(), "Negative calculator");
    }
    @Test
	public void testBasePrice() {
        InputRow inputLine = new InputRow("80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz");
        String expectedDisplay = "$5.67";
        Double expectedCalc = 5.67;
        String expectedSaleCalc = null;
        Double expectedSaleDisplay = null;

        basePrice.addFieldToProduct(inputLine, productRecord);

        assertEquals(expectedDisplay, productRecord.getRegularDisplayPrice(),"regular display");
        assertEquals(expectedCalc, productRecord.getRegularCalculatorPrice(), "regular calculator");
        assertEquals(expectedSaleCalc, productRecord.getSaleDisplayPrice(),"sale display when no sale");
        assertEquals(expectedSaleDisplay, productRecord.getSaleCaclulatorPrice(), "sale calculator when no sale");
    }
    @Test 
    public void testSplitPrice(){
        InputRow inputLine = new InputRow(
       //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
        "80000001 Kimchi-flavored white rice                                  00000000 00000000 00000650 00000000 00000002 00000000 NNNNNNNNN      18oz");
        String expectedDisplay = "2 for $6.50";
        Double expectedCalc = 3.25;
        basePrice.addFieldToProduct(inputLine, productRecord);

        assertEquals(expectedDisplay, productRecord.getRegularDisplayPrice(),"split display");
        assertEquals(expectedCalc, productRecord.getRegularCalculatorPrice(), "split calculator");        
    }
    @Test
	public void testBaseAndSalePrice() {
        InputRow inputLine = new InputRow(
       //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
        "99999901 Testing base and sale price                                 00000555 00000444 00000333 00000222 00000002 00000002 NNNNNNNNN      18oz");
       String expectedDisplay = "$5.55";
        Double expectedCalc = 5.55;
        String expectedSaleCalc = "$4.44";
        Double expectedSaleDisplay = 4.44;
        // this is a bit of an integration test, but want to make sure it works.
        SalePrice salePrice = new SalePrice();
        basePrice.addFieldToProduct(inputLine, productRecord);
        salePrice.addFieldToProduct(inputLine, productRecord);
        assertEquals(expectedDisplay, productRecord.getRegularDisplayPrice(),"regular display with sale");
        assertEquals(expectedCalc, productRecord.getRegularCalculatorPrice(), "regular calculator with sale");
        assertEquals(expectedSaleCalc, productRecord.getSaleDisplayPrice(),"sale display with base");
        assertEquals(expectedSaleDisplay, productRecord.getSaleCaclulatorPrice(), "sale calculator with base");
    }
}
