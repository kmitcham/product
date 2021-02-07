package com.kevinmitcham.product.field;

import com.kevinmitcham.product.ProductRecord;

public class Unit extends PassThrough {

    public void addFieldToProduct(String inputLine, ProductRecord productRecord) {
        String flags = extractString(123, 132, inputLine);
        char[] asArray  = flags.toCharArray();
        if (asArray[2] == 'Y'){
            productRecord.setUnitOfMeasure("Pound");
        } else {
                productRecord.setUnitOfMeasure("Each");
        }
        return;
    }
}
