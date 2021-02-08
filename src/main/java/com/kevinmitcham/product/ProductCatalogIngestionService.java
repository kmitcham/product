package com.kevinmitcham.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProductCatalogIngestionService {
    @Value("${product.catalog}")
    private String INPUT_FILE;
    static final int SECOND = 1000;
    @Autowired
    ProductRecordService productRecordService;

    @Scheduled(fixedDelay = (60 * SECOND) )
    public void readProductFile() {
        Collection<ProductRecord> loadedRecords = new ArrayList<ProductRecord>();
        try {
            File productFile = new File(INPUT_FILE);
            Scanner myReader = new Scanner(productFile);
            while (myReader.hasNextLine()) {                
                String inputLine = myReader.nextLine();
                try {
                    InputRow row = new InputRow(inputLine);
                    ProductRecord productRecord = productRecordService.parseInputRow(row);
                    loadedRecords.add(productRecord);
                } catch (Exception e){
                    System.err.println( "Skipped record due to exception"  );
                    System.err.println(inputLine);
                    System.err.println(e.getMessage());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find "+INPUT_FILE);
            e.printStackTrace();
        }
        sendToDummyDB(loadedRecords);
    }

    public void sendToDummyDB(Collection<ProductRecord> productRecords){
        System.out.println(" sent "+productRecords.size()+ " to dummy db");
    }
}
