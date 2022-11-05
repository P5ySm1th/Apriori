/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apriori;

/**
 * @author Nguyên
 */
import java.util.HashSet;
import java.util.Set;
import java.text.DecimalFormat;

public class Law {

    DecimalFormat newFormat = new DecimalFormat("#.###");
    Set<Integer> setA;
    Set<Integer> setB;
    double min_conf;

    public Law(Set<Integer> setA, Set<Integer> setB, double min_conf) {
        this.setA = setA;
        this.setB = setB;
        this.min_conf = min_conf;
    }

    public Law(double min_conf) {
        this.setA = new HashSet<>();
        this.setB = new HashSet<>();
        this.min_conf = min_conf;
    }

    public void setMin_conf(double min_conf) {
        this.min_conf = min_conf;
    }

    public void printAssociation(double minconf) {
        min_conf = min_conf;
        String res = "";
        String res2 = "";
        String res1 = "";
        for (Integer i : setA) {
            res += i;
        }
        res += " --> ";
        for (Integer j : setB) {
            res += j;
        }
        res1 += "min_conf = " + Double.valueOf(newFormat.format(min_conf));
        if (Double.valueOf(newFormat.format(min_conf)) >= minconf) {
            res2 += "Strong Association";
        }
        System.out.printf("|%-14s%-19s%-26s|%n",res,res1,res2);
    }

}
