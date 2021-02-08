package com.kevinmitcham.product.field;

import com.kevinmitcham.product.InputRow;
import com.kevinmitcham.product.ProductRecord;

public class Size implements ProductField {

    public void addFieldToProduct(InputRow row, ProductRecord productRecord) {
        productRecord.setProductSize((String) row.get(InputRow.PRODUCT_SIZE).trim());
        return;
    }
}
