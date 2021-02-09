package com.kevinmitcham.product;

import java.util.ArrayList;

import com.kevinmitcham.product.field.*;

import org.springframework.stereotype.Component;

@Component
public class ProductRecordService {
    Description description = new Description();
    BasePrice basePrice = new BasePrice();
    SalePrice salePrice = new SalePrice();
    Size size = new Size();
    Tax taxRate = new Tax();
    Unit unit = new Unit();
    ArrayList<ProductField> fields = new ArrayList<ProductField>();
    ArrayList<ProductField> priceFields = new ArrayList<ProductField>();
    
    
    public ProductRecordService(){
        // TODO: this addition of fields is clumsy and ugly; and will scale poorly.
        // the fields are fairly static, so having to change code to adapt is reasonable, if not great
        // the alternative of making a parser to read the records seems like overkill for what should be a simple tool.
        // With hundreds of fields, this will be hard to maintain.  Spliting the fields into groups can help.
        fields.add(description);
        fields.add(size);
        fields.add(taxRate);  
        fields.add(unit);  
        initializePriceFields();
    }

    private void initializePriceFields(){
        priceFields.add(basePrice);
        priceFields.add(salePrice);
    }

    ProductRecord parseInputRow(InputRow row){
        // TODO validation when needed
        String productId = row.get("Product ID");
        ProductRecord productRecord = new ProductRecord(productId);
        // 
        for (ProductField field :fields){
            field.addFieldToProduct(row, productRecord);
        }
        for (ProductField field :priceFields){
            field.addFieldToProduct(row, productRecord);
        }
        return productRecord;
    }
}
