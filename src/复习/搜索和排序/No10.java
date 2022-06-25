package 复习.搜索和排序;

public class No10 {
    /**
     * 摆动排序
     */

    //最直观的想法，先排序，然后将数组中大的数和小的数分开放
    /*public void wiggleSort(int[] nums) {
        int n = nums.length;

        //调整为最大堆
        for (int i = 0; i < n; i++) {
            update(nums,i);
        }

        for (int i = n - 1; i > -1; i--) {
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            shift(nums,0,i);
        }

        int[] temp = new int[n];
        int p1=((n-1)>>1),p2=n-1;
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                temp[i] = nums[p1];
                p1--;
            }else{
                temp[i] = nums[p2];
                p2--;
            }
        }

        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }

    //堆排序的调整函数
    public void shift(int[] nums,int start,int n){
        int i = start,j = 2*i+1;
        while (j < n) {
            if (j < n - 1 && nums[j] < nums[j + 1]) {
                j++;
            }
            if (nums[i] < nums[j]) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i = j;
                j = 2*i+1;
            } else {
                break;
            }
        }
    }
    public void update(int[] nums,int i){
        int parent = (i-1)/2;
        while (i > 0) {
            if (nums[parent] < nums[i]) {
                int tmp = nums[parent];
                nums[parent] = nums[i];
                nums[i] = tmp;
                i = parent;
                parent = (i-1)/2;
            }else
                break;
        }
    }*/

    //使用桶排序，速度很快，但是空间复杂度不符合要求
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
            nums[i] = j;
            bucket[j]--;
        }
        for (int i = 0; i < n; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
    }*/

    //实际上我们并不需要将数组完全
    //排序，因为会出问题的只在数组
    //的中位数，所以我们只要找到所
    //有的中位数，然后再将数组交叉，
    //就可以实现摆动排序了
    /*int mid;
    public void wiggleSort(int[] nums){
        halfSearch(nums,0,nums.length-1);

        //三向切分，i与j之间的是mid，i之前的
        //是小于mid的数，k之后是大于mid的数
        int[] temp = new int[nums.length];
        int i = 0,j = 0,k = nums.length-1;
        while (j < k) {
            if (nums[j] > mid) {
                temp[k] = nums[j];
                nums[j] = nums[k];
                k--;
            } else if (nums[j] < mid) {
                temp[i] = nums[j];
                nums[j] = nums[i];
                temp[j] = nums[i];
                i++;
                j++;
            }else {
                temp[j] = nums[j];
                j++;
            }
        }
        temp[j] = nums[j];

        i = (nums.length-1)/2;
        for (int l = 0; l < nums.length; l+=2) {
            nums[l] = temp[i];
            i--;
        }
        j = nums.length-1;
        for (int l = 1; l < nums.length; l+=2) {
            nums[l] = temp[j];
            j--;
        }
    }
    public void halfSearch(int[] nums,int l,int r){
        int i = l,j = r;
        while (i < j) {
            while (i < j && nums[i] < nums[j]) {
                j--;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] <= nums[j]) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        if (i > nums.length / 2) {
            halfSearch(nums,l,i-1);
        } else if (i < nums.length / 2) {
            halfSearch(nums,i+1,r);
        }else{
            mid = nums[i];
        }
    }*/

    //再写一遍快速搜索+三向切分
    /*public void wiggleSort(int[] nums){
        quickSearch(nums,0,nums.length-1);
        int center = nums[nums.length/2],i = 0,j = 0,k = nums.length-1,temp;
        while (j < k) {
            if (nums[j] < center) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
                i++;
            } else if (nums[j] > center) {
                temp = nums[j];
                nums[j] = nums[k];
                nums[k] = temp;
                k--;
            }else
                j++;
        }
        int[] fun = new int[nums.length];
        i = (nums.length-1)/2;
        j = nums.length-1;
        for (int l = 0; l < nums.length; l+=2) {
            fun[l] = nums[i];
            i--;
        }
        for (int l = 1; l < nums.length; l+=2) {
            fun[l] = nums[j];
            j--;
        }
        for (int l = 0; l < nums.length; l++) {
            nums[l] = fun[l];
        }
    }
    public void quickSearch(int[] nums,int l,int r){
        if (l > r) {
            return;
        }
        int i = l,j = r,temp;
        while (i < j) {
            while (i < j && nums[i] < nums[j]) {
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

        if (i > nums.length / 2) {
            quickSearch(nums,l,i-1);
        } else if (i < nums.length / 2) {
            quickSearch(nums,i+1,r);
        }
    }*/

    //使用虚拟地址，空间复杂度达到O(1)
    int n;
    public void wiggleSort(int[] nums){
        n = nums.length;
        halfSearch(nums, 0, n - 1);
        int c = nums[nums.length/2],i = 0,j = 0,k = n-1,temp;

        //注意细节，这里必须要是<=，因为使用虚拟地址是边换边确定好了位置
        while (j <= k) {
            if (nums[locMap(j)] > c) {
                temp = nums[locMap(j)];
                nums[locMap(j)] = nums[locMap(i)];
                nums[locMap(i)] = temp;
                i++;
                j++;
            } else if (nums[locMap(j)] < c) {
                temp = nums[locMap(j)];
                nums[locMap(j)] = nums[locMap(k)];
                nums[locMap(k)] = temp;
                k--;
            }else
                j++;
        }
    }
    public int locMap(int i){
        return (1+2*i)%(n|1);
    }
    public void halfSearch(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        int i = l,j = r,temp;
        while (i < j) {
            while (i < j && nums[i] < nums[j]) {
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }while (i < j && nums[i] < nums[j]) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        if (i > nums.length / 2) {
            halfSearch(nums,l,i-1);
        } else if (i < nums.length / 2) {
            halfSearch(nums,i+1,r);
        }
    }
}
