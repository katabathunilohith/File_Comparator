package com.example.filecompare.service;

import java.io.*;
import java.util.*;

public class FileService {


    public static String compareFiles(String file1, String file2) throws IOException {

        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));

        ArrayList<String> words1 = new ArrayList<>();
        ArrayList<String> words2 = new ArrayList<>();

        String line;

        while ((line = br1.readLine()) != null) {
            String[] tempwords = line.split(" ");
            words1.addAll(Arrays.asList(tempwords));
        }

        while ((line = br2.readLine()) != null) {
            String[] tempwords = line.split(" ");
            words2.addAll(Arrays.asList(tempwords));
        }

        br1.close();
        br2.close();

        ArrayList<String> common = new ArrayList<>();
        ArrayList<String> different = new ArrayList<>();

        for (String w1 : words1) {
            if (words2.contains(w1)) {
                if (!common.contains(w1)) {
                    common.add(w1);
                }
            } else {
                different.add(w1);
            }
        }

        for (String w2 : words2) {
            if (!words1.contains(w2)) {
                different.add(w2);
            }
        }

        int totalWords = words1.size() + words2.size();
        int commonWords = common.size() * 2;
        double similarity = ((double) commonWords / totalWords) * 100;

        return "Common Words: " + common +
                "\nDifferent Words: " + different +
                "\nSimilarity Percentage: " + String.format("%.2f", similarity) + "%";
    }

}