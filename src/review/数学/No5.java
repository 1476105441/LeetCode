package review.数学;

public class No5 {
    /**
     *      快乐数
     */

    public int count(int n){
        int res = 0;
        while (n > 0) {
            int l = n%10;
            res += l*l;
            n /= 10;
        }
        return res;
    }

    //对于一个无法得到快乐数的数字，一定会出现循环？是的
    //解法一：使用哈希表
    /*public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int con = n;
        set.add(n);

        while (con != 1) {
            int temp = count(con);
            if(!set.add(temp))
                return false;
            con = temp;
        }

        return true;
    }*/

    //解法二：限制次数循环
    //任何一个数字都可以在6次循环内判断出是否是快乐数
    /*public boolean isHappy(int n){
        int c = 0,tmp = n;
        while (c <= 5) {
            tmp = count(tmp);
            c++;
        }
        return tmp == 1;
    }*/

    //解法三：使用快慢指针，效率非常高
    //如果非快乐数一定会出现重复的数字的话，相当于出现
    //了一个闭环，所以使用快慢指针的方法可以解决这个问
    //题，如果快指针和慢指针相等且不为1，则说明出现了环路
    public boolean isHappy(int n){
        int x = n,y = count(n);
        while (x != 1) {
            x = count(x);
            y = count(count(y));
            if(x != 1 && x == y)
                return false;
        }
        return true;
    }
}
