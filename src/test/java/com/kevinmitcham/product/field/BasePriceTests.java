package com.kevinmitcham.product.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.kevinmitcham.product.ProductRecord;

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
	public void testBasePrice() {
        String inputLine = "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";
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
        String inputLine = 
       //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
        "80000001 Kimchi-flavored white rice                                  00000000 00000000 00000650 00000000 00000002 00000000 NNNNNNNNN      18oz";
        String expectedDisplay = "$3.25";
        Double expectedCalc = 3.25;
        basePrice.addFieldToProduct(inputLine, productRecord);

        assertEquals(expectedDisplay, productRecord.getRegularDisplayPrice(),"split display");
        assertEquals(expectedCalc, productRecord.getRegularCalculatorPrice(), "split calculator");        
    }
}
