package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NetUtils {
    public List<Contact> getContacts() throws IOException {
        //String filePath = "D:\\course\\41\\csvFile.txt";
        String filePath = "csvFile.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line).append("\n");
            }
            return convertCsvToContact(result.toString());
        } catch (Exception e) {
            System.out.println("Unable to get data from the file");
            return Collections.emptyList();
        }
    }

    private List<Contact> convertCsvToContact(String inputDate) {
        String[] lines = inputDate.split("\n");
        List<Contact> contacts = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) { // Skip the header line
            contacts.add(convertLineToContact(lines[i]));
        }
        return contacts;
    }

    private Contact convertLineToContact(String line) {
        String[] tokens = line.split(",");
        return new Contact(tokens[0], tokens[1], tokens[2], tokens[3]);
    }
}
