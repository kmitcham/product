package com.kevinmitcham.product.field;

import com.kevinmitcham.product.InputRow;
import com.kevinmitcham.product.ProductRecord;

public class Size implements ProductField {

    public void addFieldToProduct(InputRow row, ProductRecord productRecord) {
        // TODO: confirm product size is a simple pass through, with no parsing
        // size can be:
        //    blank -> 'each'
        //    n x i<unit>-> i<unit>
        //    <unit>  -> 'per <unit'
        productRecord.setProductSize((String) row.get(InputRow.PRODUCT_SIZE).trim());
        return;
    }
}
