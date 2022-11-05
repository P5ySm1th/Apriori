/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apriori;

import java.util.*;

/**
 *
 * @author Nguyên
 */
public class Apriori {

    Set<ItemSet> setL;
    Map<String, ItemSet> dataSet;
    double support;

    public Apriori(Map<String, ItemSet> dataSet, double support) {
        this.dataSet = dataSet;
        this.support = support;
        setL = new HashSet();
    }

    public int countFrequency(int number) { //đếm số lần xuất hiện của phần tử trong tập hợp
        int count = 0;                      // chưa check tới Minsupport, chỉ tìm số lần xuất hiện
        for (ItemSet it : this.dataSet.values()) {
            for (Integer j : it.itemset) {
                if (j == number) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean checkContain(ItemSet itemCheck, Set<ItemSet> setLi) {
        for (ItemSet it : setLi) {                         //cho duyệt từng phẩn tử itemset trong SetLi
            if (it.itemset.equals(itemCheck.itemset)) { //nếu số ở trong itemset bằng với số ở trong checkItemSet
                //truyền vào thì trả true
                return true;
            }
        }
        return false;
    }

    //hàm check minsup
    private Set<ItemSet> checkMinSupport(Set<ItemSet> setLi) {
        Set<ItemSet> set = new HashSet();
        for (ItemSet it : setLi) {
            if (it.support >= this.support) {
                set.add(it);
            }
        }
        return set;
    }

    public Set<ItemSet> getCadidates() { // duyệt các phần tử độc lập trong mảng
        Set<ItemSet> setLi = new HashSet();
        for (ItemSet it : this.dataSet.values()) {
            // duyệt trong dataSet được khai báo bên trong class Apriori
            // trong dataSet có ItemSet mà ItemSet khai báo chứa một Set integer
            // phải cậy ra được mấy thằng integer đó
            for (Integer number : it.itemset) {
                int count = countFrequency(number);
                Set<Integer> setItem = new HashSet();
                setItem.add(number);
                ItemSet itemset = new ItemSet(setItem, count);
                if (!checkContain(itemset, setLi)) {
                    setLi.add(itemset);       //đếm nhưng mà chưa bỏ phần tử bé hơn minsup
                }
            }
            setLi = checkMinSupport(setLi); // bỏ được điều kiện theo minSup rồi
        }
        return setLi;
    }

    public Set<ItemSet> getCadidatesNotCondition() {
        Set<ItemSet> setLi = new HashSet();
        for (ItemSet it : this.dataSet.values()) {
            for (Integer number : it.itemset) {
                int count = countFrequency(number);
                Set<Integer> setItem = new HashSet();
                setItem.add(number);
                ItemSet itemset = new ItemSet(setItem, count);
                if (!checkContain(itemset, setLi)) {
                    setLi.add(itemset);
                }
            }
        }
        return setLi;
    }

    public Set<ItemSet> apriorigen() {
        System.out.printf("%n%40s%n", "GROWTH NUMBER 1 ");
        Set<ItemSet> setCan = getCadidates();
        this.setL.addAll(setCan);
        printSetItem(this.setL);
        int size = 2;
        Set<Set<Integer>> setGen = new HashSet();
        while ((setGen = getCandidates(setCan, size)) != null) {
            System.out.printf("%39s%n", "GROWTH NUMBER " + String.valueOf(size));
            Set<ItemSet> setItem = new HashSet();
            for (Set<Integer> item : setGen) {
                if (has_subnet(item)) {
                    int count = countFrequency(item);
                    setItem.add(new ItemSet(item, count));
                }
            }
            setItem = checkMinSupport(setItem);
            this.setL.addAll(setItem);
            printSetItem(this.setL);
            size++;
        }
        return setL;
    }

    private int countFrequency(Set<Integer> item) {
        int count = 0;
        for (ItemSet it : this.dataSet.values()) {
            if (it.itemset.containsAll(item)) {
                count++;
            }
        }
        return count;
    }

    public Set<Set<Integer>> getSubnet(Set<Integer> set) {
        Set<Set<Integer>> result = new HashSet();
        int count = 0;
        int[] arr = new int[set.size()];
        int n = arr.length;
        for (Integer i : set) {
            arr[count] = i;
            count++;
        }

        //add vào subset con dựa vào superset VD: superset {1,2,3} --> các powerset {rỗng},{1},{2},{3},{1,2},{1,3},{2,3}
        for (int i = 0; i < (1 << n); i++) {
            Set<Integer> setA = new HashSet();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    setA.add(arr[j]);
                }
            }
            if (setA.size() == set.size() - 1) { //kiểm tra điều kiện size để chọn ra cho đúng với size của mảng
                result.add(setA);
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    private boolean has_subnet(Set<Integer> item) {
        Set<Set<Integer>> setSubnet = getSubnet(item);
        int count = 0;
        for (Set<Integer> it : setSubnet) {
            if (checkSubnet(it)) {
                count++;
            }
        }
        if (count == setSubnet.size()) {
            return true;
        }
        return false;
    }

    private boolean checkSubnet(Set<Integer> it) {
        for (ItemSet item : this.setL) {
            if (item.itemset.equals(it)) {
                return true;
            }
        }
        return false;
    }

    public Set<Set<Integer>> getCandidates(Set<ItemSet> set, int size) { //size cho nhập vào giai đoạn của phát sinh
        // số size = số phần tử được ghép vào trong mảng
        Set<Set<Integer>> result = new HashSet();
        int count = 0;
        int[] arr = new int[set.size()];
        int n = arr.length;
        for (ItemSet it : set) {
            for (Integer i : it.itemset) {
                arr[count] = i;
            }
            count++;
        }
        //add vào subset con dựa vào superset VD: superset {1,2,3} --> các powerset {rỗng},{1},{2},{3},{1,2},{1,3},{2,3}
        for (int i = 0; i < (1 << n); i++) {
            Set<Integer> setA = new HashSet();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    setA.add(arr[j]);
                }
            }
            if (setA.size() == size) { //kiểm tra điều kiện size để chọn ra cho đúng với size của mảng
                result.add(setA);
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public void printSetItem(Set<ItemSet> set) {
        System.out.printf("|%-1s%55s|%n", "ITEM", "SUPPORT");
        for (ItemSet it : set) {
            List<String> intString = new ArrayList();
            for (Integer i : it.itemset) {
                intString.add(String.valueOf(i));
            }
            String result = String.join(",", intString);
            System.out.printf("|%-15s%41s%.1f|%n", result, "", it.support);

        }
        System.out.format("+-------------+------------------+------------+-------------+%n");

    }

    public Set<Law> getLawGen(double minconf) {
        Set<Law> setLaw = new HashSet();
        Set<ItemSet> temp = this.setL;
        int size = 0;
        for (ItemSet item : temp) {
            if(item.itemset.size() > size){
                size=item.itemset.size(); 
            } 
        }
        for (ItemSet it : this.setL) {
            if (it.itemset.size() >= size) {
                String[] arr = new String[it.itemset.size()];
                int count = 0;
                for (Integer integer : it.itemset) {
                    arr[count] = integer + "";
                    count++;
                }
                AssociationLaw aL = new AssociationLaw(arr);
                Set<Law> setTempLaw = aL.getLawGenerate();
                for (Law law : setTempLaw) {
                    int countASet = countFrequency(law.setA);
                    Set<Integer> setB = new HashSet();
                    setB.addAll(law.setB);
                    setB.addAll(law.setA);
                    int countBSet = countFrequency(setB);
                    double minconfidence = getMinConfidence(countBSet, countASet);
                    law.setMin_conf(minconfidence);
                    setLaw.add(law);
                }
            }
        }
        printLaw(setLaw, minconf);
        return setLaw;
    }

    public void printLaw(Set<Law> set, double minconf) {
        System.out.format("|---------------------GROWTH ASSOCIATION--------------------|%n");
        System.out.format("+-------------+------------------+------------+-------------+%n");
        System.out.format("|Assocation   |No of transaction |Status                    |%n");
        for (Law law : set) {
            law.printAssociation(minconf);
        }
        System.out.format("+-------------+------------------+------------+-------------+%n");

    }

    private double getMinConfidence(int countASet, int countBSet) {
        return (double) countASet / countBSet;
    }
}
