package com.kevinmitcham.product.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.kevinmitcham.product.ProductRecord;
import com.kevinmitcham.product.InputRow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SizeTests {
    Size size = new Size();
    ProductRecord productRecord = new ProductRecord("anyId");

    @Test
	public void testSize() {
        InputRow inputLine = new InputRow(
            //----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
             "99999999 Size test                                                   00000555 00000444 00000333 00000222 00000002 00000002 NNNNYNNNN      18oz");
        String expected = "18oz";
        size.addFieldToProduct(inputLine, productRecord);
        assertEquals(expected, productRecord.getProductSize());
    }
}
