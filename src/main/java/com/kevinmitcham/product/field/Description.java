package com.kevinmitcham.product.field;

import com.kevinmitcham.product.InputRow;
import com.kevinmitcham.product.ProductRecord;

public class Description implements ProductField  {
    int start, end;
    public Description (){
        this.start = 9; // this may be too much indirection
        this.end = 68;
    }
    public void addFieldToProduct(InputRow row, ProductRecord productRecord) {
        String description = row.get(InputRow.PRODUCT_DESCRIPTION);
        productRecord.setDescription(description.trim());//TODO QUESTION confirm trim is desired
        return;
    }
}
