package com.reacher.test.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 类说明
 *
 * @author wanggw
 * @date 2017/2/23
 */
public class HeapSort {

    public static <E> boolean sort(List<E> list, Compare<E> compare){//排序
        return sort(list, list.size(), compare);
    }

    public static <E> boolean sort(List<E> list, int n, Compare<E> compare){
        if(null == list || 0 == list.size()){
            return false;
        }
        if(!create(list, n, compare)){
            return false;
        }
        for(int i = n; i > 0; --i){
            swap(list, 0, i - 1);
            adjust(list, 0, i - 1, compare);
        }
        return true;
    }

    private static <E> boolean create(List<E> list, int length, Compare<E> compare){ //创建小根堆
        if(null == list || 0 == list.size()){
            return false;
        }
        for(int i = (length / 2 - 1); i >= 0; --i){
            if(!adjust(list, i, length, compare)){
                return false;
            }
        }
        return true;
    }

    private static <E> boolean adjust(List<E> list, int middle, int length, Compare<E> compare){//调整堆，使其满足小根堆的条件
        if(null == list || 0 == list.size()){
            return false;
        }
        E temp = list.get(middle);
        for(int i = (2 * middle + 1); i < length; i *= 2){
            if(i < (length - 1) && !compare.compare(list.get(i), list.get(i + 1))){
                ++i;
            }
            if(compare.compare(temp,list.get(i))){
                break;
            }
            list.set(middle, list.get(i));
            middle = i;
        }
        list.set(middle, temp);
        return true;
    }

    private static <E> void swap(List<E> list, int i, int j){//数据交换
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void main(String[] args) {
        Integer[] datas = new Integer[] {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};

        HeapSort.sort(Arrays.asList(datas), new Compare<Integer>() {
            @Override
            public boolean compare(Integer value1, Integer value2) {
                return value1 - value2 > 0;
            }
        });

        System.out.println(Arrays.asList(datas));
    }
}
