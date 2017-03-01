package com.java.base.merge;

import com.java.base.Compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类说明
 *
 * @author wanggw
 * @date 2017/3/1
 */
public class MergeSort {

    public static <E> void sort(E[] datas, Compare<E> compare) {
        sort(datas, 0, datas.length - 1, compare);
    }

    private static <E> void sort(E[] datas, int start, int end, Compare<E> compare) {
        if(start < end) {
            int middle = (start + end) / 2;
            sort(datas, start, middle, compare);
            sort(datas, middle + 1, end, compare);
            merge(datas, start, middle, end, compare);
        }
    }

    private static <E> void merge(E[] datas, int start, int middle, int end, Compare<E> compare) {
        int i = start;
        int j = middle + 1;

        List<E> temps = new ArrayList<>();
        while(i <= middle && j <= end) {
            if(compare.compare(datas[i], datas[j]) < 0) {
                temps.add(datas[i++]);
            } else {
                temps.add(datas[j++]);
            }
        }
        while(i <= middle) {
            temps.add(datas[i++]);
        }
        while(j <= end) {
            temps.add(datas[j++]);
        }
        for(E temp: temps) {
            datas[start++] = temp;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {4, 3, 6, 1, 2, 5};
        MergeSort.sort(array, new Compare<Integer>() {
            @Override
            public int compare(Integer value1, Integer value2) {
                return value1 - value2;
            }
        });
        System.out.println(Arrays.asList(array));
    }

}
