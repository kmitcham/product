package com.kevinmitcham.product.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.kevinmitcham.product.ProductRecord;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DescriptionTests {
    ProductRecord productRecord = new ProductRecord("anyId");
    Description description = new Description();

    @Test
	public void testHappyDescription() {
        String inputLine = "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";
        String expected = "Kimchi-flavored white rice";
        description.addFieldToProduct(inputLine, productRecord);
        String actual = productRecord.getDescription();
        assertEquals(expected, actual, "Happy path description works");
    }

    @Test
    public void testEmptyDescription(){
        String inputLine = "80000001                                                             00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";
        String expected = "";
        description.addFieldToProduct(inputLine, productRecord);
        String actual = productRecord.getDescription();
        assertEquals(expected, actual, "empty description works");
    }
    @Test
    public void testLongDescription(){
        String inputLine = "80000001_01234567890123456789012345678901234567890123456789012345678900000567_00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";
        String expected =           "0123456789012345678901234567890123456789012345678901234567";
        description.addFieldToProduct(inputLine, productRecord);
        String actual = productRecord.getDescription();
        assertEquals(expected, actual, "long description works");
    }
}
