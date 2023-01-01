package senior.排序和搜索;

public class No1 {
    /**
     *          摆动排序 II
     *  给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     * 你可以假设所有输入数组都可以得到满足题目要求的结果。
     *
     * 提示：
     *     1 <= nums.length <= 5 * 104
     *     0 <= nums[i] <= 5000
     *     题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
     *
     * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
     */

    //循序渐进的解决问题，首先用最基本的方法解决，先排序再插入
    /*public void wiggleSort(int[] nums) {
        quickSort(0,nums.length-1,nums);
        int center = (nums.length-1)/2,j=0,k=0;
        int[] temp1 = new int[center+1],temp2 = new int[nums.length-1-center];

        for (int i = 0; i <= center; i++) {
            temp1[i] = nums[i];
            j++;
        }
        for (int i = center+1; i < nums.length; i++) {
            temp2[k] = nums[i];
            k++;
        }

        k--;
        j--;
        for (int i = 0; i < nums.length; i+=2) {
            nums[i] = temp1[j--];
        }
        for (int i = 1; i < nums.length; i+=2) {
            nums[i] = temp2[k--];
        }
    }

    public void quickSort(int i,int j,int[] nums){
        if (i > j) {
            return;
        }

        int temp,low = i,high = j;
        while(low < high){
            while (low < high && nums[low] < nums[high]) {
                high--;
            }
            if (low < high) {
                temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                low++;
            }
            while (low < high && nums[low] < nums[high]) {
                low++;
            }
            if (low < high) {
                temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                high--;
            }
        }

        quickSort(i,low-1,nums);
        quickSort(low+1,j,nums);
    }*/


    //三向切分+快速选择实现O(n)时间复杂度
    /*int center;
    public void wiggleSort(int[] nums){
        center = (nums.length-1)/2;
        int[] temp1 = new int[center],temp2 = new int[nums.length-center-1];
        int j = 0,k = 0;
        quickSearch(nums,0,nums.length-1);

        for (int i = 0; i < center + 1; i++) {
            temp1[j++] = nums[i];
        }
        for (int i = center+1; i < nums.length; i++) {
            temp2[k++] = nums[i];
        }

        j--;
        k--;

        for (int i = 0; i < nums.length; i+=2) {
            nums[i] = temp1[j--];
        }
        for (int i = 1; i < nums.length; i+=2) {
            nums[i] = temp2[k--];
        }
    }

    public void quickSearch(int[] nums,int low,int high){
        if(low > high){
            return;
        }

        int lt = low,i = low+1,gt = high,val = nums[low];
        while (i <= gt) {
            if (nums[i] < val) {
                exchange(nums,i,lt);
                i++;
                lt++;
            }else if(nums[i] > val){
                exchange(nums,i,gt);
                gt--;
            }else {
                i++;
            }
        }

        if(lt > center){
            quickSearch(nums,low,lt-1);
        }else{
            quickSearch(nums,i,high);
        }
    }

    public void exchange(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }*/


    //使用虚地址将空间复杂度将为O(1)
    //这里我是直接用排序了，所以时间复杂度应该是O(nlogn)，而不是O(n)
    /*int center,n;
    public void wiggleSort(int[] nums){
        center = (nums.length-1)/2;
        n = nums.length|1;
        quickSearch(nums,0,nums.length-1);
    }

    public void quickSearch(int[] nums,int low,int high){
        if(low > high){
            return;
        }

        int gt = low,i = high-1,lt = high,val = nums[(1+2*high)%n],loci,loclt,locgt;
        while (i >= gt) {
            loci = (1+2*i)%n;
            if (nums[loci] < val) {
                loclt = (1+2*lt)%n;
                exchange(nums,loci,loclt);
                i--;
                lt--;
            }else if(nums[loci] > val){
                locgt = (1+2*gt)%n;
                exchange(nums,loci,locgt);
                gt++;
            }else {
                i--;
            }
        }

        quickSearch(nums,lt+1,high);
        quickSearch(nums,low,i);
    }

    public void exchange(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }*/


    //思考，前面的几个解法效果都不太理想
    //原因可能是我没有区分开快速选择和三分快速排序
    //快速选择只用来找出中位数，而三分快速排序是为
    //了将数组分成三部分，让和中位数相同的数值在中
    //间而两边大于和小于值并不做过多处理

    //修改版的快速搜索+三向切分
    /*int center,n;
    public void wiggleSort(int[] nums) {
        center = (nums.length-1)/2;
        n = nums.length|1;
        quickSort(0,nums.length-1,nums);
        int center = (nums.length-1)/2,j = 0,k = 0,l = nums.length-1,val = nums[center];
        int[] temp1 = new int[center+1],temp2 = new int[nums.length-1-center];

        while (k < l) {
            if (nums[k] > val) {
                exchange(nums,k,l);
                l--;
            } else if (nums[k] < val) {
                exchange(nums,k,j);
                k++;
                j++;
            }else{
                k++;
            }
        }
        k = 0;
        j = 0;
        for (int i = 0; i <= center; i++) {
            temp1[i] = nums[i];
            j++;
        }
        for (int i = center+1; i < nums.length; i++) {
            temp2[k] = nums[i];
            k++;
        }

        k--;
        j--;
        for (int i = 0; i < nums.length; i+=2) {
            nums[i] = temp1[j--];
        }
        for (int i = 1; i < nums.length; i+=2) {
            nums[i] = temp2[k--];
        }
    }

    public void quickSort(int i,int j,int[] nums){
        if (i > j) {
            return;
        }

        int temp,low = i,high = j;
        while(low < high){
            while (low < high && nums[low] < nums[high]) {
                high--;
            }
            if (low < high) {
                temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                low++;
            }
            while (low < high && nums[low] < nums[high]) {
                low++;
            }
            if (low < high) {
                temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                high--;
            }
        }

        if (low < center) {
            quickSort(low+1,j,nums);
        } else if (low > center) {
            quickSort(i,low-1,nums);
        }
    }

    public void exchange(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }*/


    //最终使用快速搜索+三分+映射地址
    /*int center,n;
    public void wiggleSort(int[] nums){
        center = (nums.length-1)/2;
        n = nums.length|1;
        quickSearch(nums,0,nums.length-1);

        //注意要先取出中间值，因为在交换过程中nums[center]可能会改变，就是这个点卡了我一个下午
        //val = nums[center]
        int i = 0,j = nums.length-1,k = nums.length-1,loci,locj,lock,val = nums[center];
        while (j >= i) {
            locj = mapInt(j);
            if (nums[locj] < val) {
                lock = mapInt(k);
                exchange(nums,lock,locj);
                k--;
                j--;
            } else if (nums[locj] > val) {
                loci = mapInt(i);
                exchange(nums,locj,loci);
                i++;
            }else{
                j--;
            }
        }
    }

    public void quickSearch(int[] nums,int low,int high){
        if(low > high){
            return;
        }

        int i = low,j = high,temp;
        while (i < j) {
            while(i < j && nums[i] < nums[j]){
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] < nums[j]) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        if (i > center) {
            quickSearch(nums,low,i-1);
        } else if (i < center) {
            quickSearch(nums,i+1,high);
        }
    }

    public void exchange(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int mapInt(int i){
        return (1+2*i)%n;
    }*/


    //最优解，应该是桶排序
    //桶排序，在元素的数量不是很大的时候可以使用
    //原理是用元素的值作为桶的下标，简单直接的可
    //以进行操作处理
    /*public void wiggleSort(int[] nums){
        //由于题目说明数组的元素数量小于5000个
        int[] bucket = new int[5001];
        int j = 5000;

        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        for (int i = 1; i < nums.length; i+=2) {
            while(bucket[j] == 0)
                j--;
            nums[i] = j;
            bucket[j]--;
        }

        for (int i = 0; i < nums.length; i+=2) {
            while(bucket[j] == 0)
                j--;
            nums[i] = j;
            bucket[j]--;
        }
    }*/


    //**************************重新写一遍*****************************

    //解法一：桶排序
    //出现了问题，中位数没有分开，解决方案：先给数组中高峰处的元素赋值，从大到小进行
    /*public void wiggleSort(int[] nums) {
        int[] bucket = new int[5001];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            bucket[nums[i]]++;
        }

        int j = 5000;
        for (int i = 1; i < n; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            bucket[j]--;
            nums[i] = j;
        }
        for (int i = 0; i < n; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            bucket[j]--;
            nums[i] = j;
        }
    }*/

    //解题思路：其实只需要找出中位数，并且将中位数分开即可
    //首先第一步：找到中位数，使用快速搜索
    //明确：l所指向的是等于当前元素的开始位置，r指向的是等于当前元素的结束位置
    //成功，28ms，不过理论上空间复杂度并没有达到O(1)
    /*public void wiggleSort(int[] nums){
        int n = nums.length;
        quickSearch(nums,0,n-1,n>>1);
        int l = 0,r = n-1,i = 0;
        //三向切分，把中位数聚集到中间
        while (i <= r) {
            if (nums[i] < mid) {
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                l++;
                i++;
            } else if (nums[i] > mid) {
                int temp = nums[r];
                nums[r] = nums[i];
                nums[i] = temp;
                r--;
            }else{
                i++;
            }
        }

        int[] res = new int[n];
        for (int j = 0; j < n; j++) {
            res[j] = nums[j];
        }
        int j = n-1;
        for (int k = 1; k < n; k += 2) {
            nums[k] = res[j];
            j--;
        }
        for (int k = 0; k < n; k += 2) {
            nums[k] = res[j];
            j--;
        }
    }*/


    int mid;
    //快速查找，找出中位数
    public void quickSearch(int[] nums,int l,int r,int c){
        if (l > r) {
            return;
        }
        int i = l,j = r;
        while(i < j){
            while (i < j && nums[i] < nums[j]) {
                j--;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] < nums[j]) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
        if (i < c) {
            quickSearch(nums,i+1,r,c);
        } else if (i > c) {
            quickSearch(nums,l,i-1,c);
        }else
            mid = nums[i];
    }

    //使用虚拟地址映射到物理地址上
    //地址映射：i -> (2*i+1)%(n|1)
    //如果使用这种地址映射，则需要排序反过来
    public void wiggleSort(int[] nums){
        int n = nums.length;
        quickSearch(nums,0,n-1,n>>1);
        int l = 0,r = n-1,i = n-1;
        while (l <= i) {
            int loc = mapInt(i,n);
            if (nums[loc] > mid) {
                int loc1 = mapInt(l,n);
                int temp = nums[loc1];
                nums[loc1] = nums[loc];
                nums[loc] = temp;
                l++;
            } else if (nums[loc] < mid) {
                int loc1 = mapInt(r,n);
                int temp = nums[loc1];
                nums[loc1] = nums[loc];
                nums[loc] = temp;
                r--;
                i--;
            } else{
                i--;
            }
        }
    }
    public int mapInt(int i,int n){
        return (2*i+1)%(n|1);
    }
}
