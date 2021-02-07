package com.kevinmitcham.product.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.kevinmitcham.product.ProductRecord;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SalePriceTests {
    SalePrice salePrice = new SalePrice();
    ProductRecord productRecord = new ProductRecord("anyId");

    @Test
	public void testSalePrice() {
        String inputLine = 
              //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
               "80000001 Sale price test white rice                                  00000000 00004123 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";
        String expectedSaleDisplay = "$41.23";
        Double expectedSaleCalc = 41.23;

        salePrice.addFieldToProduct(inputLine, productRecord);

        assertEquals(expectedSaleDisplay, productRecord.getSaleDisplayPrice(),"sale display ");
        assertEquals(expectedSaleCalc, productRecord.getSaleCaclulatorPrice(), "sale calculator");
    }
}
