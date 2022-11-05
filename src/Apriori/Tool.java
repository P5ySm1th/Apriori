/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apriori;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Nguyên
 */
public class Tool {

    public static Map<String, ItemSet> importFileText(String fileName, String charset, String delemited, double support) throws IOException, FileNotFoundException {
        Map<String, ItemSet> record = new HashMap();
        File f = new File(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), charset));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] stringLine = line.split(delemited);
            ItemSet itemSet = new ItemSet();
            itemSet.support = support;
            if (stringLine.length == 2) {
                String[] items = stringLine[1].split(",");
                for (String s : items) {
                    itemSet.itemset.add(Integer.parseInt(s));
                }
            }
            record.put(stringLine[0], itemSet);
        }
        br.close();
        return record;
    }

    public static void printDetail(Map<String, ItemSet> map, Apriori apri, double min_support, double min_conf) {
        int count = 0;
        int linesCount = 0;
        Set<ItemSet> unchecked;
        unchecked = apri.getCadidatesNotCondition();
        for (ItemSet item : unchecked) {
            count++;
        }
        for (String key : map.keySet()) {
            linesCount++;
        }

        System.out.format("|--------------------SUMMARRY ITEM DETAIL-------------------|%n");
        System.out.format("+-------------+------------------+------------+-------------+%n");
        System.out.format("|No Of items  |No of transaction |Min support |Min confident|%n");
        System.out.format("+-------------+------------------+------------+-------------+%n");
        System.out.format("%d%15d%26f%13f%n", count, linesCount, min_support, min_conf);
        System.out.format("|--------------------Distinct item in list------------------|%n");
        for (ItemSet item : unchecked) {
            System.out.format("%10s", item.printData());
        }
        System.out.format("%n+-------------+------------------+------------+-------------+\n");
        System.out.printf("|%-1s%45s|%n", "TRANSACTION ID", "ITEMSET");
        for (Map.Entry<String, ItemSet> mapEntry : map.entrySet()) {
            System.out.printf("|%-1s%55s|%n", mapEntry.getKey(), mapEntry.getValue().printData());
        }
        System.out.format("%n+-------------+------------------+------------+-------------+");

    }
}
