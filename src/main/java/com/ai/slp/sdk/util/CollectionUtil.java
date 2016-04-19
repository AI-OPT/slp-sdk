package com.ai.slp.sdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class CollectionUtil {
	private CollectionUtil(){}
    public static boolean isEmpty(Collection<?> collection) {
        if (null == collection) {
            return true;
        } else {
            return collection.isEmpty();
        }
    }

    public static boolean isEmpty(Object[] objects) {
        return (objects == null || objects.length == 0) ? true : false;
    }

    /**
     * 数组转换为List
     * 
     * @param arr
     * @return
     */
    public static List<?> arrayToList(Object[] arr) {
        List<?> list = new ArrayList<>();
        if (arr == null)
            {return list;}
        list = Arrays.asList(arr);
        return list;
    }
    /**
     * 集合分割成字符串
     * @param collections  集合对象
     * @param separator  分隔符
     * @return
     * @author rui
     */
    public static String split(Collection<?> collections, String separator){
        
        Object[] array = collections.toArray(new Object[0]);
        int length = array.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
                stringBuilder.append(array[i]);
                if (i != length-1) {
                    stringBuilder.append(separator);
                }
        }
        return stringBuilder.toString();
    }
    
    /**
     * 对Map按照值大小排序
     * @param map
     * @return
     * @author zhangchao
     * @ApiDocMethod
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    

}
