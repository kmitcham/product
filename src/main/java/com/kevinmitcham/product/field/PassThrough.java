package com.kevinmitcham.product.field;


public abstract class PassThrough implements ProductField {
    String extractString(int start, int end, String inputLine){
        //TODO validate string is long enough
        String extracted = inputLine.substring(start, end);
        return extracted.trim();
    }
}
