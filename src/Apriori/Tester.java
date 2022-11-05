/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apriori;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Nguyên
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        double min_support = 2;
        double min_conf = 0.7;
        Map<String, ItemSet> map = Tool.importFileText("file/dataset3.txt", "UNICODE", "\t", min_support);
        Apriori apri = new Apriori(map, min_support);
        Tool.printDetail(map, apri, min_support, min_conf);
        apri.apriorigen();
        apri.getLawGen(min_conf);
    }
}
