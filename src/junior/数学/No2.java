package junior.数学;

public class No2 {
    //             计数质数
    //统计所有小于非负整数 n 的质数的数量。

    //统计质数，首先明白质数的定义：除了1和它本身外没有别的因数
    //怎么找质数呢？

    //--------------------------------------------------------------------
    //           失败，超出时间限制
    /*
    public int countPrimes(int n) {
        int num = 0;

        //存放质数的数组
        int[] primes = new int[10000];
        boolean flag;

        if (n == 0 || n == 1) {
            return 0;
        }
        primes[0] = 2;
        num++;
        for (int i = 3; i <= n; i++) {
            flag = true;
            for (int j = 0; j < num; j++) {
                if (i % primes[j] == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                primes[num++] = i;
            }
        }

        return num;
    }
    */
    //---------------------------------------------------------------------------

    //                正确解法：埃式筛
    //思想：从2开始，将所有质数的倍数标记为非质数
    /*
    public int countPrimes(int n){
        boolean[] primes = new boolean[n];

        //初始化全部标记为真
        for (int i = 0; i < n; i++) {
            primes[i] = true;
        }

        //条件是i*i<n是因为：当前数i的第i倍不能超过n
        for (int i = 2; i * i < n; i++) {
            if (primes[i]){
                int k = i;
                while (i * k < n) {
                    //i*k就是当前质数的k倍，所以不是质数
                    primes[i*k] = false;
                    k++;
                }
            }
        }

        int num = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i]){
                num++;
            }
        }
        return num;
    }*/
    //-------------------------------------------------------------------------------

    //-------------------------------------------------------------------------------
    //                     速度更快的埃氏筛
    /*
    public int countPrimes(int n){
        boolean[] primes = new boolean[n];

        //条件是i*i<n是因为：当前数i的第i倍不能超过n
        for (int i = 2; i * i < n; i++) {
            if (!primes[i]){
                int k = i;
                while (i * k < n) {
                    //i*k就是当前质数的k倍，所以不是质数
                    primes[i*k] = true;
                    k++;
                }
            }
        }

        int num = 0;
        for (int i = 2; i < n; i++) {
            if (!primes[i]){
                num++;
            }
        }
        return num;
    }*/
    //-------------------------------------------------------------------------------


    //-------------------------------------------------------------------------------

    //此题还有一个解法：线性筛，明天再完成。
    //              线性筛
    //思想：遍历2~n，如果是质数（这个数没有被标记为合数），就放进一个集合当中，然后再将集合中的质数与当前的数相乘得到一个数
    //得到的数标记为合数
    //
    //             下面这个失败了，原因：没有在标记过程中加上i%primes[i]=0时终止的条件，导致后面会重复的进行标记，如：
    //                      3 * 4 = 12, 2 * 6 = 12;
    //              这两个运算结果相同，但是却重复标记了
    //
    //可是在测试中线性筛并没有比埃氏筛快啊，什么情况啊
//    public int countPrimes(int n) {
//        //存放质数的集合
//        int[] primes = new int[n + 1];
//
//        int num = 0;
//
//        //标记是否为质数
//        boolean[] bp = new boolean[n + 1];
//
//        for (int i = 2; i < n; i++) {
//            if (!bp[i]) {
//                primes[num++] = i;
//            }
//
//            //你放循环体里万一超出范围还会赋值，最后只能报错。而放判断语句里它会先判断是否会超出范围，一旦超出它就退出，也就不会执行赋值
//            //好像并不是上面的说法吧，
//            //错误写法：
//            /*for(int j = 0;j < num ;j++) {
//                if (i * primes[j] < n) {
//                    bp[i * primes[j]] = true;
//                }
//                //  **关键点在这里**
//                if (i % primes[j] == 0) {
//                    break;
//                }
//            }*/
//
//            //if语句这样就可以了：
//            //这样的话原因我就知道了，因为在溢出之前还有相乘的数大于n，如果不break的话，就还会继续循环，直到溢出
//            /*for(int j = 0;j < num ;j++) {
//                if (i * primes[j] < n) {
//                    bp[i * primes[j]] = true;
//                }else{
//                    break;
//                }
//                if (i % primes[j] == 0) {
//                    break;
//                }
//            }*/
//
//            for (int j = 0; j < num && i * primes[j] < n; j++) {
//                bp[i * primes[j]] = true;
//                //  **关键点在这里**
//                if (i % primes[j] == 0) {
//                    break;
//                }
//            }
//        }
//
//        return num;
//    }


    public int countPrimes(int n) {
        //存放质数的集合
        int[] primes = new int[n + 1];

        int num = 0;

        //标记是否为质数
        boolean[] bp = new boolean[n + 1];

        for (int i = 2; i < n; i++) {
            if (!bp[i]) {
                primes[num++] = i;
            }
            for (int j = 0; j < num && i * primes[j] < n; j++) {
                bp[i * primes[j]] = true;
                //  **关键点在这里**
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }

        return num;
    }
}
