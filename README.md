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

#算法分析
>时间复杂度为：O(N*logN)

>它是不稳定的排序方法。（排序的稳定性是指如果在排序的序列
中，存在前后相同的两个元素的话，排序前 和排序后他们的相对
位置不发生变化）


Heap Sort原理及实现
=====================
---------------------
#简介
>堆排序(Heapsort)是指利用堆积树（堆）这种数据结构所设计的一种排序算法，它
是选择排序的一种。可以利用数组的特点快速定位指定索引的元素。堆分为大根堆
和小根堆，是完全二叉树。大根堆的要求是每个节点的值都不大于其父节点的值，
即A[PARENT[i]] >= A[i]。在数组的升序排序中，需要使用的就是大根堆，因为根据大
根堆的要求可知，最大的值一定在堆顶。

>1991年的计算机先驱奖获得者、斯坦福大学计算机科学系教授罗伯特·弗洛伊德
(Robert W．Floyd）和威廉姆斯(J．Williams）在1964年共同发明了著名的堆排序算
法（ Heap Sort )

#堆
>n个关键字序列Kl，K2，…，Kn称为（Heap），当且仅当该序列满足如下
性质（简称为堆性质）：ki<=k(2i）且ki<=k(2i+1)(1≤i≤ n/2），当然，这是
小根堆，大根堆则换成>=号；k(i）相当于二叉树的非叶子结点，K(2i）则
是左子节点，k(2i+1）是右子节点。

>若将此序列所存储的向量R[1..n]看做是一棵完全二叉树的存储结构，则堆
实质上是满足如下性质的完全二叉树：树中任一非叶子结点的关键字均
不大于（或不小于）其左右孩子（若存在）结点的关键字。

#原理
>堆排序利用了大根堆（或小根堆）堆顶记录的关键字最大（或最小）这一特征，使得在当
前无序区中选取最大（或最小）关键字的记录变得简单。

>用大根堆排序的基本思想：① 先将初始文件R[1..n]建成一个大根堆，此堆为初始的无序区
② 再将关键字最大的记录R[1]（即堆顶）和无序区的最后一个记录R[n]交换，由此得到新
的无序区R[1..n-1]和有序区R[n]，且满足R[1..n-1].keys≤R[n].key③由于交换后新的根R[1]可能
违反堆性质，故应将当前无序区R[1..n-1]调整为堆。然后再次将R[1..n-1]中关键字最大的记
录R[1]和该区间的最后一个记录R[n-1]交换，由此得到新的无序区R[1..n-2]和有序区R[n-1..n]，
且仍满足关系R[1..n-2].keys≤R[n-1..n].keys，同样要将R[1..n-2]调整为堆......直到无序区只有一
个元素为止。

#实现
>建堆，建堆是不断调整堆的过程，从len/2处开始调整，一直到第一个节点，此处len是堆中元素的个数。建堆的过程是线性的过程，
从len/2到0处一直调用调整堆的过程，相当于o(h1)+o(h2)…+o(hlen/2) 其中h表示节点的深度，len/2表示节点的个数，这是一个求和
的过程，结果是线性的O(n)。

>调整堆：调整堆在构建堆的过程中会用到，而且在堆排序过程中也会用到。利用的思想是比较节点i和它的孩子节点left(i),right(i)，
选出三者最大(或者最小)者，如果最大（小）值不是节点i而是它的一个孩子节点，那边交互节点i和该节点，然后再调用调整堆过
程，这是一个递归的过程。调整堆的过程时间复杂度与堆的深度有关系，是lgn的操作，因为是沿着深度方向进行调整的。

>堆排序：堆排序是利用上面的两个过程来进行的。首先是根据元素构建堆。然后将堆的根节点取出(一般是与最后一个节点进行交
换)，将前面len-1个节点继续进行堆调整的过程，然后再将根节点取出，这样一直到所有节点都取出。堆排序过程的时间复杂度是
O(nlgn)。因为建堆的时间复杂度是O(n)（调用一次）；调整堆的时间复杂度是lgn，调用了n-1次，所以堆排序的时间复杂度是
O(nlgn)

#算法分析
>时间复杂度为：O(N*logN)

>它是不稳定的排序方法。（排序的稳定性是指如果在排序的序列
中，存在前后相同的两个元素的话，排序前 和排序后他们的相对
位置不发生变化）

>由于建初始堆所需的比较次数较多，所以堆排序不适宜于记录数
较少的文件
