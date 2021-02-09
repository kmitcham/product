package com.kevinmitcham.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ProductCatalogIngestionService {
    @Value("${product.catalog}")
    private String INPUT_FILE;
    
    @Autowired
    ProductRecordService productRecordService;

    Logger logger = LoggerFactory.getLogger(ProductCatalogIngestionService.class);

    static final int SECOND = 1000;

    // instead of keeping a hash, we could delete the update file after getting it.
    // deleting is awfully permenant, so we will let the customer blow away the old one 
    // when the new one comes in, and just ignore the repeats until that happens.
    // this may become large for some values of large.
    Map<String, byte[]> cachedProductHashes = new HashMap<>();
    
    // updates up to every minute, but maybe the file is big, and it takes us time to get through it, 
    // so check more often.  The cache should protect us from duplicate updates.
    @Scheduled(fixedDelay = (30 * SECOND) )
    public void readProductFile() {
        Collection<ProductRecord> loadedRecords = new ArrayList<ProductRecord>();
        try {
            File productFile = new File(INPUT_FILE);
            Scanner myReader = new Scanner(productFile);
            while (myReader.hasNextLine()) {                
                String inputLine = myReader.nextLine();
                try {
                    InputRow row = new InputRow(inputLine);
                    String productId = row.get(InputRow.PRODUCT_ID);
                    byte[] rowHash = getSHA(inputLine);
                    if (cachedProductHashes.containsKey(productId) ) {
                        if (Arrays.equals(
                            cachedProductHashes.get(productId),rowHash ) ) {
                            logger.info("Skipping record already processed for "+productId);
                            continue;
                        } 
                    }
                    cachedProductHashes.put(productId, rowHash);
                    ProductRecord productRecord = productRecordService.parseInputRow(row);
                    loadedRecords.add(productRecord);
                    
                } catch (Exception e){
                    logger.error( "Skipped record due to exception"  );
                    logger.error(inputLine);
                    logger.error(e.getMessage());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            logger.error("Could not find "+INPUT_FILE);
            e.printStackTrace();
        }
        sendToDummyDB(loadedRecords);
    }

    private byte[] getSHA(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return hash;
        } catch (Exception e){
            logger.error("Error getting hash:"+e);
        }
        // return something that will never match
        return new byte[1];
    }
    
    public void sendToDummyDB(Collection<ProductRecord> productRecords){
        logger.info(" sent "+productRecords.size()+ " to dummy db");
        for (ProductRecord pr: productRecords){
            logger.info(pr.toString());
        }
    }
}
