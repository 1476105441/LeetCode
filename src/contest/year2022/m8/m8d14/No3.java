package contest.year2022.m8.m8d14;

public class No3 {
    /**
     *      根据模式串构造最小数字
     */

    public String smallestNumber(String pattern) {
        int[] map = new int[10];
        char[] cs = pattern.toCharArray();
        int n = cs.length;
        char[] res = new char[n+1];
        char pre = cs[0];
        if (pre == 'I') {
            res[0] = '1';
            map[1] = 1;
        }else{
            int c = 1;
            for (int i = 1; i < n; i++) {
                if (cs[i] == 'D') {
                    c++;
                }else
                    break;
            }
            res[0] = (char)(1+c+'0');
            map[1+c] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (pre == 'D') {
                for (int j = res[i-1]-'0';j > 0;j--){
                    if (map[j] == 0) {
                        res[i] = (char)(j + '0');
                        map[j] = 1;
                        break;
                    }
                }
            }else{
                int j = res[i-1]-'0';
                //找到最近的大于前一个元素的值
                for(;j < 10;j++){
                    if (map[j] == 0) {
                        break;
                    }
                }

                if(cs[i] == 'D'){
                    //统计下一个应该加多少
                    int c = 1;
                    for (int l = i+1; l < n; l++) {
                        if (cs[l] == 'D') {
                            c++;
                        }else
                            break;
                    }
                    res[i] = (char)(j+c+'0');
                    map[j+c] = 1;
                }else{
                    res[i] = (char)(j + '0');
                    map[j] = 1;
                }
            }

            pre = cs[i];
        }

        int i = res[n-1] - '0';

        if (pre == 'D') {
            for (i = i-1;i > 0;i--){
                if (map[i] == 0) {
                    res[n] = (char)(i+'0');
                    break;
                }
            }
        }else{
            for (i = i+1;i < 10;i++){
                if (map[i] == 0) {
                    res[n] = (char)(i+'0');
                    break;
                }
            }
        }

        return new String(res);
    }
}
