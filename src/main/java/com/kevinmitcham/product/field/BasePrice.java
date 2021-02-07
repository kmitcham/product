package com.kevinmitcham.product.field;

import java.text.NumberFormat;
import java.util.Locale;

import com.kevinmitcham.product.ProductRecord;

public class BasePrice implements ProductField {
    NumberFormat nfUS = NumberFormat.getCurrencyInstance(Locale.US);
    int start = 69;
    int end = 77;
    int splitStart = 87;
    int splitEnd = 95;
    int quantityStart = 105;
    int quantityEnd = 113;
    int ROUNDING = 4;

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

    void setPrices(double price, double splitPrice, int quantity, ProductRecord productRecord){
        if (price != 0){
            setValues(price, productRecord);
        } else if (splitPrice != 0 ) {
            price = splitPrice/quantity;
            setValues(price, productRecord);
        } else {
            setNulls(productRecord);
        }
    }
    
    void setNulls(ProductRecord productRecord){
        productRecord.setRegularCalculatorPrice(null);
        productRecord.setRegularDisplayPrice(null);

    }
    void setValues(double price, ProductRecord productRecord){
        double rounded = roundToPlaces(price,4);
        productRecord.setRegularCalculatorPrice(rounded);
        String display = getDisplayPrice(price);
        productRecord.setRegularDisplayPrice(display);
    }

    // TODO there has got to be a better way to do this.
    double roundToPlaces(double x, int places){
        int round = 1;
        for (int i=0; i < places; i++){
            round *= 10;
        }
        double shifted = (x * round);
        int adjusted = (int) (shifted + ((x < 0.0) ? -0.5 : 0.5));
        double rounded =  ((double)adjusted) / round;
        return rounded;
    }

    String getDisplayPrice(double price){
        String formatted = nfUS.format(price);
        return formatted;
    }
}
