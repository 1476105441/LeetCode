package middle.数学;

public class No1 {
    //              快乐数
    //编写一个算法来判断一个数 n 是不是快乐数。
    //「快乐数」定义为：
    //
    //    对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    //    然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    //    如果 可以变为  1，那么这个数就是快乐数。
    //
    //如果 n 是快乐数就返回 true ；不是，则返回 false 。

    //**难点在于如何检测是否存在循环

    //---------------------------------------------------------------------------
    //哈希表，1ms
    /*public boolean isHappy(int n) {
        Set<Integer> set;
        set = new HashSet<>();
        int temp = n,count = 0,x,y;
        long l = 1;

        //不需要循环太多次,实际上任何一个数在五次循环之内都可以判断出来是否是快乐数
        while (!set.contains(temp)) {
            set.add(temp);
            y = 0;
            while (temp > 0) {
                x = temp % 10;
                y += x * x;
                temp = temp / 10;
            }
            if (y == 1) {
                return true;
            }
            count++;
            temp = y;
        }
        return false;
    }*/
    //-------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------------
    //                  0ms，任何一个数字在5循环5次之后都可以得知是否为快乐数
    /*public boolean isHappy(int n) {
        int temp = n,count = 0,x,y;
        long l = 1;

        //不需要循环太多次,实际上任何一个数在五次循环之内都可以判断出来是否是快乐数
        while (count <= 5) {
            y = 0;
            while (temp > 0) {
                x = temp % 10;
                y += x * x;
                temp = temp / 10;
            }
            if (y == 1) {
                return true;
            }
            count++;
            temp = y;
        }
        return false;
    }*/
    //---------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------
    //                  快慢指针检测是否循环法
    //想象一下，如果将所有存在的数（计算得出的结果）放入一个链表上，若是这给定的数是一个快乐数，则该结果
    //是一条正常链表，末尾节点是1；如果该数不是一个快乐数，则该链表中会出现一个闭合的环，计算结果陷入无限
    //的循环之中。利用这一点，可以使用快慢指针的方式来检测是否存在死循环

    public boolean isHappy(int n){
        if (n == 1) {
            return true;
        }
        int slow = n,quick = getNext(n),temp = n,count = 0,x,y;

        while (slow != quick) {
            slow = getNext(slow);
            quick = getNext(getNext(quick));
            if (slow == 1) {
                return true;
            }
        }

        return false;
    }

    public int getNext(int n){
        int res = 0,x;

        while (n > 0) {
            x = n % 10;
            n = n / 10;
            res += x * x;
        }

        return res;
    }
}
