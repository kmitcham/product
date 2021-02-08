package com.kevinmitcham.product.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.kevinmitcham.product.ProductRecord;
import com.kevinmitcham.product.InputRow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaxTests {
    Tax tax = new Tax();
    ProductRecord productRecord = new ProductRecord("anyId");

    @Test
	public void testWithTax() {
        InputRow inputLine = new InputRow(
            //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
             "99999999 Tax test with tax                                           00000555 00000444 00000333 00000222 00000002 00000002 NNNNYNNNN      18oz");
        double expected = 7.775;
        tax.addFieldToProduct(inputLine, productRecord);
        assertEquals(expected, productRecord.getTaxRate());
    }
    @Test
	public void testWithoutTaxX() {
        InputRow inputLine = new InputRow(
            //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
             "99999999 Tax test without tax                                        00000555 00000444 00000333 00000222 00000002 00000002 NNNNXNNNN      18oz");
        double expected = 0.0;
        tax.addFieldToProduct(inputLine, productRecord);
        assertEquals(expected, productRecord.getTaxRate());
    }
    @Test
	public void testWithoutTaxN() {
        InputRow inputLine = new InputRow(
            //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
             "99999999 Tax test without tax                                        00000555 00000444 00000333 00000222 00000002 00000002 NNNNNNNNN      18oz");
        double expected = 0.0;
        tax.addFieldToProduct(inputLine, productRecord);
        assertEquals(expected, productRecord.getTaxRate());
    }
    @Test
	public void testWithoutTaxSpace() {
        InputRow inputLine = new InputRow(
            //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
             "99999999 Tax test without tax                                        00000555 00000444 00000333 00000222 00000002 00000002                18oz");
        double expected = 0.0;
        tax.addFieldToProduct(inputLine, productRecord);
        assertEquals(expected, productRecord.getTaxRate());
    }

}
