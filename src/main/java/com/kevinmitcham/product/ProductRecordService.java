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

/*
----Id-- --Description---------------------------------------------- -RegPri- -salPri- -split-- -salspl- -regQua- -salQua- -flags--- -size----
80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz
14963801 Generic Soda 12-pack                                        00000000 00000549 00001300 00000000 00000002 00000000 NNNNYNNNN   12x12oz
40123401 Marlboro Cigarettes                                         00001000 00000549 00000000 00000000 00000000 00000000 YNNNNNNNN          
50133333 Fuji Apples (Organic)                                       00000349 00000000 00000000 00000000 00000000 00000000 NNYNNNNNN        lb
123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
         1         2         3         4         5         6         7         8         9         0         1         2         3         4         5
*/

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
