package review.搜索和排序;

public class No11 {
    /**
     *      有序矩阵中第K小的元素
     */

    //最直接的思路，将二维数组展开排序，使用归并排序
    //由于不符合题目要求，就不写出来了


    //归并排序，类似于合并k个链表，使用最小堆，将链表
    //头放入堆中，不停的取出堆顶元素并计数，并把堆顶元
    //素的下一个元素放入堆中，直到计数为k
    /*public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length,c = 0;

        //使用三元组表示堆中的一个元素，分别表示元
        //素的值，元素所在的行，元素所在的列
        int[][] heap = new int[n][3];

        //将每行的首元素放入堆中
        for (int i = 0; i < n; i++) {
            heap[c] = new int[]{matrix[i][0],i,0};
            update(heap,c);
            c++;
        }
        c = 1;
        while (c < k) {
            //取出堆顶元素，将堆顶所在的下一行放入堆中
            int[] tmp = heap[0];
            if (tmp[2] < matrix.length-1) {
                heap[0] = new int[]{matrix[tmp[1]][tmp[2]+1],tmp[1],tmp[2]+1};
            }else{
                //堆顶元素已经为它所在行的最后
                //一个元素，所以将堆的范围缩小
                heap[0] = heap[n-1];
                heap[n-1] = null;
                n--;
            }
            shift(heap,0,n);
            c++;
        }

        return heap[0][0];
    }
    public void shift(int[][] nums,int start,int end){
        int i = start,j = 2*i+1;
        while(j < end){
            if (j + 1 < end && nums[j][0] > nums[j+1][0]) {
                j++;
            }
            if(nums[j][0] < nums[i][0]){
                swap(nums,i,j);
                i = j;
                j = 2*i+1;
            } else
                break;
        }
    }
    public void swap(int[][] nums,int i,int j){
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void update(int[][] nums,int start){
        int parent = (start-1)/2,j = start;
        while (j > 0) {
            if (nums[parent][0] > nums[j][0]) {
                swap(nums,parent,j);
                j = parent;
                parent = (j-1)/2;
            }else
                break;
        }
    }*/


    //二分查找
    //要注意，二分查找时就算是小于当前数
    //的个数等于k了也不一定是答案
    public int kthSmallest(int[][] matrix, int k){
        int n = matrix.length;
        int l = matrix[0][0],r = matrix[n-1][n-1],mid;

        while (l < r) {
            mid = l + ((r-l)>>1);
            int count = count(matrix, mid);
            //要注意，相等时并不一定就是答案，因
            //为有特殊情况可能导致mid并不在矩阵
            //中，但是有和答案一样的k值
            if (count >= k) {
                //此步不把mid排除是因为大于和等
                //于这两种情况都一起考虑了，如果
                //当前的mid不在矩阵中，那么一定
                //先扫描到矩阵中正确的mid，然后
                //将现在这个mid排除掉
                r = mid;
            }else{
                l = mid+1;
            }
        }

        return l;
    }
    public int count(int[][] matrix,int num){
        int i = matrix.length-1,j = 0,c = 0;
        while (i >= 0 && j < matrix.length) {
            if (matrix[i][j] <= num) {
                //当前列全部都小于num，将这一列的元素个数加上
                c += i+1;
                j++;
            }else{
                i--;
            }
        }
        return c;
    }
}
