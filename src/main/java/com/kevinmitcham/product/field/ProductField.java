package com.kevinmitcham.product.field;
import com.kevinmitcham.product.ProductRecord;
import com.kevinmitcham.product.InputRow;

public interface ProductField {    
    void addFieldToProduct(InputRow row, ProductRecord productRecord);
}
