package com.java.base.quick;

import com.java.base.Compare;

import java.util.Arrays;
import java.util.List;

/**
 * 类说明
 *
 * @author wanggw
 * @date 2017/2/23
 */
public class QuickSort {


    public static <E> List<E> sort(List<E> list, Compare<E> compare){
        return sort(list, list.size(), compare);
    }

    public static <E> List<E> sort(List<E> list, int length, Compare<E> compare){
        if(length > list.size()){
            length = list.size();
        }
        sort(list, 0, length - 1, compare);
        return list;
    }

    private static <E> void sort(List<E> list, int low, int high, Compare<E> compare){
        if(low >= high){
            return;
        }
        int midde = partition(list, low, high, compare);
        sort(list, low, midde - 1, compare);
        sort(list, midde + 1, high, compare);
    }

    private static <E> int partition(List<E> list, int low, int high, Compare<E> compare){
        E key = list.get(low);
        while(low < high){
            while(low < high && compare.compare(key, list.get(high)) < 0){
                --high;
            }
            list.set(low, list.get(high));
            while(low < high && compare.compare(key, list.get(low)) > 0){
                ++low;
            }
            list.set(high, list.get(low));
        }
        list.set(low, key);
        return low;
    }

    public static void main(String[] args) {
        Integer[] datas = new Integer[] {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};

        QuickSort.sort(Arrays.asList(datas), new Compare<Integer>() {
            @Override
            public int compare(Integer value1, Integer value2) {
                return value1 - value2;
            }
        });

        System.out.println(Arrays.asList(datas));
    }

}
