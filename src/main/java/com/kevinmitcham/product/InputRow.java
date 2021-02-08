package com.kevinmitcham.product;

public class InputRow {
    public final static String PRODUCT_ID = "Product ID";
    public final static String PRODUCT_DESCRIPTION = "Product Description";
    public final static String REGULAR_EACH_PRICE = "Regular Each Price";
    public final static String SALE_EACH_PRICE = "Sale Each Price";
    public final static String REGULAR_SPLIT_PRICE = "Regular Split Price";
    public final static String SALE_SPLIT_PRICE = "Sale Split Price";
    public final static String REGULAR_SPLIT_QUANTITY = "Regular Split Quantity";
    public final static String SALE_SPLIT_QUANTITY = "Sale Split Quantity";
    public final static String FLAGS = "Flags";
    public final static int TAX = 5;
    public final static int UNIT = 3;
    public final static String PRODUCT_SIZE = "Product Size";

    public final static String[][] dictionary = new String[][] { 
        { PRODUCT_ID,               "1",	    "8",      "Number"},
        { PRODUCT_DESCRIPTION,      "10",	    "68",     "String"},
        { REGULAR_EACH_PRICE,	    "70",	    "77",     "Currency"},
        { SALE_EACH_PRICE,          "79",	    "86",     "Currency"},
        { REGULAR_SPLIT_PRICE,      "88",	    "95",     "Currency"},
        { SALE_SPLIT_PRICE,	        "97",	    "104",    "Currency"},
        { REGULAR_SPLIT_QUANTITY,   "106",	    "113",    "Number"},
        { SALE_SPLIT_QUANTITY,      "115",	    "122",    "Number"},
        { FLAGS,	                "124",	    "132",    "Flags"},
        { PRODUCT_SIZE,	            "134",	    "142",    "String"}
    };
    String input;
    public InputRow(String input){
        this.input = input;
    }
    public String get(String name){
        for (String[] dictrow: dictionary){
            if (dictrow[0].equals(name)){
                return getField(Integer.parseInt(dictrow[1]), Integer.parseInt(dictrow[2]));
            }
        }
        // TODO custom exception here
        throw new RuntimeException("No such field as "+name);
    }
    
    String getField(int start, int end){
        String value = this.input.substring((start-1), end);
        return value;
    }
    // The field name seems redundant, but with 100s more to come, maybe another will have Y/N boolean
    public boolean checkFlag( String fieldName, int position){
            String flags = get(InputRow.FLAGS);
            char[] asArray  = flags.toCharArray();
            // position -1 since arrays count from 0
            return (asArray[position -1] == 'Y');
    }
}   
