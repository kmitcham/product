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
    ArrayList<ProductField> fields = new ArrayList<ProductField>(6);
    
    
    public ProductRecordService(){
        // TODO: this addition of fields is clumsy and ugly; and will scale poorly.
        // the fields are fairly static, so loading all this from flat files seems like overkill
        // but with hundreds of fields, it will be hard to maintain.  We could split the fields into groups, and do related things
        fields.add(description);
        fields.add(basePrice);
        fields.add(salePrice);
        fields.add(size);
        fields.add(taxRate);  
        fields.add(unit);  
    }

    ProductRecord parseInputRow(InputRow row){
        // TODO validation as needed
        String productId = row.get("Product ID");
        ProductRecord productRecord = new ProductRecord(productId);
        // 
        for (ProductField field :fields){
            field.addFieldToProduct(row, productRecord);
        }
        return productRecord;
    }
}
