package org.example.timezoneviewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimeZoneOptions {

    public static String[] TimeZoneOptions(String filename) {
        List<String> optionsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                optionsList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return optionsList.toArray(new String[0]);
    }
}