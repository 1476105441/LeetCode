public class 分治实验 {
    int[] newNums;
    int[][] res;
    int num = 0;

    public static void main(String[] args) {
        //算法设计实验报告，分治法求逆序对
        int[] nums = {23, 13, 35, 6, 19, 50, 30, 38, 26, 18, 45,40};
        分治实验 test = new 分治实验();
        test.res = new int[3*nums.length][2];
        test.newNums = new int[nums.length];
        test.recursion(0,nums.length-1, nums);
        for (int i = 0; i < test.num; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(test.res[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(test.num);
    }

    public void recursion(int low,  int high, int[] nums) {
        if (low == high) {
            return;
        }
        int center = (low + high) / 2;
        //左半部分
        recursion(low, center, nums);
        //右半部分
        recursion(center + 1,  high, nums);
        //归并
        merge(nums,newNums,low,center,high);
        //将临时数组中的排好序的元素放回原数组中
        for (int i = low; i <= high; i++) {
            nums[i] = newNums[i];
        }
    }

    public void merge(int[] nums,int[] newNums,int left,int center,int right){
        int i = left,k = left,j = center + 1;
        //左边的数组到center为止，而右边的数组从center+1到right为止
        while (i <= center && j <= right) {
            if (nums[i] < nums[j]) {
                //前面数组的元素比后面数组前面部分的大，构成逆序
                for (int l = center + 1; l < j; l++) {
                    res[num][0] = nums[i];
                    res[num++][1] = nums[l];
                }
                newNums[k++] = nums[i++];
            }else{
                newNums[k++] = nums[j++];
            }
        }
        //处理剩下的元素
        while (i <= center) {
            //前面的数组还剩有元素，这些元素全部比后面数组的元素要大，构成逆序
            for (int l = center + 1; l <= right; l++) {
                res[num][0] = nums[i];
                res[num++][1] = nums[l];
            }
            newNums[k++] = nums[i++];
        }
        while (j <= right) {
            newNums[k++] = nums[j++];
        }
    }
}



