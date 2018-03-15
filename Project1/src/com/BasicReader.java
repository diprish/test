package com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class BasicReader {
    
    public static void main(String[] args) {

        Reader reader = null;
        CSVParser csvParser = null;
        String inboundFilePath = args[0];
        String outboundFilePath = args[1];
        int columnSeq = Integer.parseInt(args[2]);
        
        try {
            reader = Files.newBufferedReader(Paths.get(inboundFilePath));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                String val = csvRecord.get(columnSeq);
                    writeRow(outboundFilePath,val,csvRecord);
                            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private static void writeRow(String outboundFilePath, String val, CSVRecord csvRecord) throws IOException {
        try (      FileWriter fw = new FileWriter(outboundFilePath + val + ".csv", true);
                   BufferedWriter writer = new BufferedWriter(fw);
                   CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
               ) {
                   csvPrinter.printRecord(csvRecord);
                   csvPrinter.flush();            
               }
    }
}
