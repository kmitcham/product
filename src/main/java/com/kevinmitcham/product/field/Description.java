package com.kevinmitcham.product.field;

import com.kevinmitcham.product.ProductRecord;

public class Description extends PassThrough {
    int start, end;
    public Description (){
        this.start = 9; // this may be too much indirection
        this.end = 68;
    }
    public void addFieldToProduct(String inputLine, ProductRecord productRecord) {
        String description = extractString(start, end, inputLine);
        productRecord.setDescription(description);//TODO QUESTION confirm trim is desired
        return;
    }
}
