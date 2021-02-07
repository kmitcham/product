package com.kevinmitcham.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileUploadService {
    // TODO: move this to application properties
    public String INPUT_FILE = "src/test/resources/input-sample.txt";
    static final int SECOND = 1000;

    @Autowired
    ProductRecordService productRecordService;

    @Scheduled(fixedDelay = (60 * SECOND) )
    public void readProductFile() {
        try {
            File productFile = new File(INPUT_FILE);
            Scanner myReader = new Scanner(productFile);
            while (myReader.hasNextLine()) {
                String inputLine = myReader.nextLine();
                productRecordService.parseAndSaveProductRecord(inputLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find "+INPUT_FILE);
            e.printStackTrace();
        }
    }
}
