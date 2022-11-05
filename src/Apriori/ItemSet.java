/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apriori;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nguyên
 */
public class ItemSet {
    
    Set<Integer> itemset;
    double support;

    public ItemSet(Set<Integer> itemset, double support) {
        this.itemset = itemset;
        this.support = support;
    }
    public ItemSet() {
        itemset = new HashSet();
        support = 0;
    }
    
    public String printData() {
        String result= "";
        for (Integer integer : itemset) {
            result += integer + ",";
        }
        result = result.substring(0, result.length() - 1);
        return result;    
    }
}
