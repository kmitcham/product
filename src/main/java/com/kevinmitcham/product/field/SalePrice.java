package com.kevinmitcham.product.field;

import com.kevinmitcham.product.InputRow;
import com.kevinmitcham.product.ProductRecord;

public class SalePrice extends BasePrice {

    public void addFieldToProduct(InputRow row, ProductRecord productRecord) {
        // if price, use that
        //      set 
        // else 
        //   if split price
        //      get split number
        //      compute split
        //      set 
        String field = row.get(InputRow.SALE_EACH_PRICE);
        double price = Double.parseDouble(field)/100;
        String splitField = row.get(InputRow.SALE_SPLIT_PRICE);
        double splitPrice = Double.parseDouble(splitField)/100;
        // This is risky since we don't have validation upstream
        int quantity = Integer.parseInt(row.get(InputRow.SALE_SPLIT_QUANTITY));
        setPrices(price, splitPrice, quantity, productRecord);
    }
    
    
    // Just like regular, but different fields and values
    void setValues(double price, String display, ProductRecord productRecord){
        double rounded = roundToPlaces(price,4);
        productRecord.setSaleCaclulatorPrice(rounded);
        productRecord.setSaleDisplayPrice(display);
    }

}
