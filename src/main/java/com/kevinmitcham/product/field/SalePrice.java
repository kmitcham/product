package com.kevinmitcham.product.field;

import com.kevinmitcham.product.ProductRecord;

public class SalePrice extends BasePrice {
    int start = 78;
    int end = 86;
    int splitStart = 96;
    int splitEnd = 104;
    int quantityStart = 114;
    int quantityEnd = 122;

    public void addFieldToProduct(String inputLine, ProductRecord productRecord) {
        // if price, use that
        //      set regularprice
        //      set regular display
        // else 
        //   if split price
        //      get split number
        //      compute split
        //      set regular price
        //      set display
        String field = inputLine.substring(start, end);
        double price = Double.parseDouble(field)/100;
        String splitField = inputLine.substring(splitStart, splitEnd);
        double splitPrice = Double.parseDouble(splitField)/100;
        int quantity = Integer.parseInt(inputLine.substring(quantityStart, quantityEnd));
        setPrices(price, splitPrice, quantity, productRecord);
    }
    
    
    // Just like regular, but different fields and values
    void setValues(double price, ProductRecord productRecord){
        double rounded = roundToPlaces(price,4);
        productRecord.setSaleCaclulatorPrice(rounded);
        String display = getDisplayPrice(price);
        productRecord.setSaleDisplayPrice(display);
    }

}
