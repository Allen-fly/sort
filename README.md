Quick Sort的原理及实现
=====================
---------------------
#简介
>快速排序由于排序效率在同为O(N*logN)的几种排序方法中效率较高，因
此经常被采用，再加上快速排序思想----分治法也确实实用，因此很多软
件公司的笔试面试，包括像腾讯，微软等知名IT公司都喜欢考这个，还有
大大小的程序方面的考试如软考，考研中也常常出现快速排序的身影。

>快速排序是C.R.A.Hoare于1962年提出的一种划分交换排序。它采用了一
种分治的策略，通常称其为分治法(Divide-and-ConquerMethod)。

#原理
>快速排序也是分治法思想的一种实现，他的思路是使数组中的每
个元素与基准值（Pivot，通常是数组的首个值，A[0]）比较，数
组中比基准值小的放在基准值的左边，形成左部；大的放在右边，
形成右部；接下来将左部和右部分别递归地执行上面的过程：选
基准值，小的放在左边，大的放在右边，直到排序结束。

#步骤
>找基准值，设Pivot = a[0]

> 分区（Partition）：比基准值小的放左边，大的放右边，基准值
(Pivot)放左部与右部的之间。

>进行左部（a[0] - a[pivot-1]）的递归，以及右部（a[pivot+1] - a[n-
1]）的递归，重复上述步骤。

#实现


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
            while(low < high && !compare.compare(key, list.get(high))){
                --high;
            }
            list.set(low, list.get(high));
            while(low < high && compare.compare(key, list.get(low))){
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
            public boolean compare(Integer value1, Integer value2) {
                return value1 - value2 > 0;
            }
        });

        System.out.println(Arrays.asList(datas));
    }

