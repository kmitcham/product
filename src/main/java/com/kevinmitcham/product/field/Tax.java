package com.kevinmitcham.product.field;

import com.kevinmitcham.product.InputRow;
import com.kevinmitcham.product.ProductRecord;

public class Tax implements ProductField  {
    public void addFieldToProduct(InputRow row, ProductRecord productRecord) {
        if (row.checkFlag(InputRow.FLAGS, InputRow.TAX )){
            //TODO: confirm do we want this 7.775, .07775, or something else?
            productRecord.setTaxRate(7.775);
        } else {
            //TODO: confirm we want 0 or null when tax does not apply
            productRecord.setTaxRate(0);
        }
        return;
    }

}

