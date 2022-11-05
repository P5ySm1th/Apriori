/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apriori;

/**
 * @author Nguyên
 */
import java.util.*;

public class AssociationLaw {

    String itemList = "";
    public AssociationLaw(String[] ds) {
        for (String s : ds) {
            itemList += s;
        }
    }
    public Map<String, Set<String>> result() {
        Map<String, Set<String>> result = new HashMap();
        Set<String> set = subsetList();
        Set<String> addedKey = new HashSet();
        List<String> collect = new ArrayList(set);
        for (int i = 0; i < collect.size(); i++) {
            for (int j = 0; j < collect.size(); j++) {
                String s1 = collect.get(i);
                String s2 = collect.get(j);
                s2 = s2.replace(s1,"");
                if (!isExisted(s1, s2) && !s1.equals(s2) && !s1.equals("") && !s2.equals("") && s1.length() + s2.length() == itemList.length()) {
                    Set<String> s;
                    if (result.containsKey(s1)) {
                        s = result.get(s1);
                        if (!isExistedInSet(s, s2)) {
                            s.add(s2);
                        }
                    } else {
                        s = new HashSet(); //tạo set mới
                    }
                    if (!isExistedInSet(addedKey, s1)) {
                        addedKey.add(s1);
                        result.put(s1, s);
                    }
                }
            }
        }

        return result;
    }
    public Set<Law> getLawGenerate() {
        Set<Law> setLaw = new HashSet();
        result().forEach((k, v) -> {
            Set<Integer> set1 = new HashSet<>();
            for (int i = 0; i < k.length(); i++) {
                set1.add(Integer.parseInt(k.charAt(i) + ""));
            }
            Set<Integer> set2 = new HashSet<>();
            for (String s : v) {
                for (int i = 0; i < s.length(); i++) {
                    set2.add(Integer.parseInt(s.charAt(i) + ""));
                }
            }
            Law law = new Law(set1, set2, 0);
            setLaw.add(law);
        });
        return setLaw;
    }

    //========================================================================
    private ArrayList<String> merge(ArrayList<String> list, String c) {
        ArrayList<String> res = new ArrayList<>(); //chạy vòng lặp hết list
        for (String s : list) { // cho từng mảng, inset chữ cái cuối cùng vào mọi vị trí có thể,
                               // sau đó add vào list
            for (int i = 0; i <= s.length(); ++i) {
                String ps = new StringBuffer(s).insert(i, c).toString();
                res.add(ps);
            }
        }
        return res;
    }
    private Set<String> subsetList() {
        Set<String> set = new HashSet();
        for (int i = 1; i <= itemList.length(); i++) {
            ArrayList<String> permutation = permutation();
            for (String s : permutation) {
                List<String> strings = nLeftItem(s, i);
                set.addAll(strings);
            }
        }
        return set;
    }
    private ArrayList<String> permutation(String s) {
        ArrayList<String> res = new ArrayList<String>();
        if (s.length() == 1) {
            res.add(s);
        } else if (s.length() > 1) {
            int lastIndex = s.length() - 1;
            // tìm last kí tự cuối cùng
            String last = s.substring(lastIndex);
            // phần còn lại của string thôi :)
            String rest = s.substring(0, lastIndex);
            //thực hiện hoán đổi trên phần còn lại sau đó thì  ghép lại
            res = merge(permutation(rest), last);
        }
        return res;
    }
    private ArrayList<String> permutation() {
        return permutation(itemList);
    }
    private List<String> nLeftItem(String str, int n) {
        List<String> list = new LinkedList();
        for (int i = 0; i < str.length(); i += n) {
            int end = Math.min(i + n, str.length());
            String sublist = str.substring(i, end);
            list.add(sublist);
        }

        return list;
    }
    public static int hashCodeNew(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            result += c;
        }
        return result;

    }
    public static boolean isExistedInSet (Set<String> set, String s) { //check tồn tại trong list chưa ?
        for (String str : set) {
            if (hashCodeNew(str) == hashCodeNew(s)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isExisted(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            if (s2.contains(s1.charAt(i) + "")) {
                return true;
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            if (s1.contains(s2.charAt(i) + "")) {
                return true;
            }
        }
        return false;
    } //check tồn tại không, nó khác với thằng isExistedInSet



}
