package com.kevinmitcham.product.field;

import com.kevinmitcham.product.ProductRecord;

public class Tax extends PassThrough  {
    public void addFieldToProduct(String inputLine, ProductRecord productRecord) {
        String flags = extractString(123, 132, inputLine);
        char[] asArray  = flags.toCharArray();
        if (asArray[4] == 'Y'){
            //TODO: confirm do we want this 7.775, .07775, or something else?
            productRecord.setTaxRate(7.775);
        } else {
            //TODO: confirm we want 0 or null?
            productRecord.setTaxRate(0);
        }
        return;
    }
}

