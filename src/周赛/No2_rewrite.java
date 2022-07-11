package 周赛;

public class No2_rewrite {
    public static void main(String[] args) {
        java.util.Scanner s = new java.util.Scanner(System.in);

        int n,m;
        n = s.nextInt();
        m = s.nextInt();

        int[] nums = new int[2*m];
        for (int i = 0; i < 2*m; i++) {
            nums[i] = s.nextInt();
        }

        sort(nums,0,nums.length-1);

        int[] temp = new int[n];

        for (int i = 0; i < 2 * m; i++) {
            temp[nums[i]-1]++;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                System.out.println(i + 1 + " " + temp[i]);
                return;
            }
            if (temp[i] > 2) {
                System.out.println(i + 1 + " " + temp[i]);
                return;
            }
        }

        System.out.println("YES");
    }

    public static void sort(int[] nums,int low,int high){
        if (low >= high) {
            return;
        }

        int i = low,j = high,temp;
        while (i < j) {
            while(i < j && nums[i] <= nums[j]){
                j--;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            while (i < j && nums[i] <= nums[j]) {
                i++;
            }
            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
        }

        sort(nums,low,i-1);
        sort(nums,i+1,high);
    }
}
