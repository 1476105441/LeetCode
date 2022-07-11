package 复习.中级算法复习.数学;

public class No1 {
    //          快乐数

    /*public boolean isHappy(int n){
        int temp,res = n;
        Set<Integer> set = new HashSet<>();

        while (true) {
            temp = 0;
            if (res == 1) {
                break;
            }
            while (res > 0) {
                temp += Math.pow(res % 10,2);
                res /= 10;
            }
            res = temp;

            if (set.contains(res)) {
                System.out.println(res);
                return false;
            }else{
                set.add(res);
            }
        }

        return true;
    }*/

    //使用双指针，与判断链表中是否有环的算法一致，若是有环，则必定相遇
    public boolean isHappy(int n){
        if(n == 1){
            return true;
        }
        int slow = n,quick = next(n);
        while (slow != quick) {
            if (slow == 1 || quick == 1) {
                return true;
            }
            slow = next(slow);
            quick = next(next(quick));
        }
        return false;
    }

    public int next(int num){
        int res = 0,temp;
        while (num > 0) {
            temp = num % 10;
            res += temp * temp;
            num /= 10;
        }
        return res;
    }
}
