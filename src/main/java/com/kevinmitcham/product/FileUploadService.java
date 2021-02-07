package com.kevinmitcham.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileUploadService {
    // TODO: move this to application properties
    public String INPUT_FILE = "src/test/resources/input-sample.txt";
    static final int SECOND = 1000;
    Map<String, byte[]> cachedProductHashes = new HashMap<>();
    // this might be a memory problem; but if we could just send the updates... 
    Map<String, ProductRecord> cachedProducts = new HashMap<>();

    @Autowired
    ProductRecordService productRecordService;

    @Scheduled(fixedDelay = (60 * SECOND) )
    public void readProductFile() {
        Collection<ProductRecord> loadedRecords = new ArrayList();
        try {
            File productFile = new File(INPUT_FILE);
            Scanner myReader = new Scanner(productFile);
            while (myReader.hasNextLine()) {
                ProductRecord productRecord;
                String inputLine = myReader.nextLine();
                InputRow row = new InputRow(inputLine);
                String productId = row.get("Product ID");
                byte[] hash = getSHA(inputLine);
                if (cachedProductHashes.containsKey(productId)){
                    productRecord = cachedProducts.get(productId);
                } else {
                    productRecord = productRecordService.parseInputRow(row);
                }
                loadedRecords.add(productRecord);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find "+INPUT_FILE);
            e.printStackTrace();
        }
    }

    public void sendToDummyDB(Collection<ProductRecord> productRecords){
        System.out.println(" sent "+productRecords.size()+ " to dummy db");
    }
    byte[] getSHA(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return hash;
        } catch (Exception e){
            System.err.println("Error getting hash:"+e);
        }
        // return something that will never match
        return new byte[1];
    }
}
