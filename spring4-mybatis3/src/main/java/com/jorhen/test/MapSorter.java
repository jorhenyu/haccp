package com.jorhen.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set; 

public class MapSorter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     Map phone = new HashMap();
	        phone.put("Apple", 7299);
	        phone.put("Meizu", 6000);
	        phone.put("Meizu", 2698);
	        phone.put("Xiaomi", 2400);
	        
	        //key-sort
	        Set set = phone.keySet();
	        Object[] arr = set.toArray();
	        Arrays.sort(arr);
	        for (Object key : arr) {
	            System.out.println(key + ": " + phone.get(key));
	        }
	        System.out.println();
	        
	        //value-sort
	        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(phone.entrySet());
	        //list.sort()
	        list.sort(new Comparator<Map.Entry<String, Integer>>() {
	            //@Override
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
	        
	        //collections.sort()
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	            //@Override
	            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
	        //for
	       // for (int i = 0; i < list.size(); i++) {
	       //     System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
	        //}
	        System.out.println("==========");
	        //for-each
	        for (Map.Entry<String, Integer> mapping : list) {
	            System.out.println(mapping.getKey() + ": " + mapping.getValue());
	        }
	        System.out.println("==========");
	        
	        
	}

}
