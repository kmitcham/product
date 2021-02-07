package com.kevinmitcham.product.field;

import com.kevinmitcham.product.ProductRecord;

public class Size extends PassThrough {
    int start, end;
    public Size (){
        this.start = 133; // this may be too much indirection
        this.end = 142;
    }
    public void addFieldToProduct(String inputLine, ProductRecord productRecord) {
        String sizeField = extractString(start, end, inputLine);
        // TODO: confirm this. 
        // size can be:
        //    blank -> 'each'
        //    n x i<unit>-> i<unit>
        //    <unit>  -> 'per <unit'
        productRecord.setProductSize(sizeField);
        return;
    }
}
