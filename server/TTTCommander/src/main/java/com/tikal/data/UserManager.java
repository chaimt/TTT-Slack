package com.tikal.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Haim.Turkel on 2/16/2016.
 */
@Service
public class UserManager {

    @Value("${users.dir}")
    private String userDir;
    private File users;

    private SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
    private static final String NEW_LINE_SEPARATOR = "\n";


    @PostConstruct
    public void init() {
        users = new File(userDir);
        if (!users.exists()) {
            users.mkdirs();
        }

        saveUser(new UserInfo("chaim", "TMS", new Date(), new Date()));

    }

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {"userId", "project", "in", "out"};


    private List<UserInfo> readCsvFile(File currentFile) {
        List<UserInfo> users = new LinkedList<>();
        try {
            Reader fileReader = new FileReader(currentFile);
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
            CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
            List csvRecords = csvFileParser.getRecords();
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = (CSVRecord) csvRecords.get(i);
                users.add(new UserInfo(record.get("userId"),record.get("project"),new Date(Long.valueOf(record.get("in"))),new Date(Long.valueOf(record.get("in")))));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void writeCsvFile(List<UserInfo> users, File currentFile) {
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            fileWriter = new FileWriter(currentFile);
            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER_MAPPING);
            //Write a new student object list to the CSV file
            for (UserInfo user : users) {
                List userDataRecord = new ArrayList();
                userDataRecord.add(user.getUserId());
                userDataRecord.add(String.valueOf(user.getProject()));
                userDataRecord.add(String.valueOf(user.getIn().getTime()));
                userDataRecord.add(String.valueOf(user.getOut().getTime()));
                csvFilePrinter.printRecord(userDataRecord);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter!=null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }

    public void saveUser(UserInfo userInfo) {
        if (!userInfo.isValid()) {
            throw new RuntimeException("invalid user");
        }
        File user = new File(users, userInfo.getUserId());
        if (!user.exists()) {
            user.mkdirs();
        }
        File currentMonth = new File(user, yearMonthFormat.format(userInfo.getIn()) + ".csv");
        List<UserInfo> users = readCsvFile(currentMonth);
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(userInfo);
        writeCsvFile(users, currentMonth);

    }
}
