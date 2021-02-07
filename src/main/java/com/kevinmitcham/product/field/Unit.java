package com.kevinmitcham.product.field;

import com.kevinmitcham.product.InputRow;
import com.kevinmitcham.product.ProductRecord;

public class Unit implements ProductField {

    public void addFieldToProduct(InputRow row, ProductRecord productRecord) {
        if (row.checkFlag( InputRow.FLAGS,InputRow.UNIT )){
            productRecord.setUnitOfMeasure("Pound");
        } else {
            productRecord.setUnitOfMeasure("Each");
        }
        return;
    }
}
