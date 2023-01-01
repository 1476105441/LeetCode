package contest.year2022.m8.m8d20;

public class No3 {
    /**
     *      字母移位 II
     */


    /*public String shiftingLetters(String s, int[][] shifts) {
        char[] c = s.toCharArray();

        for (int i = 0; i < shifts.length; i++) {
            update(c,shifts[i][2] == 0,shifts[i][0],shifts[i][1]);
        }

        return new String(c);
    }
    public void update(char[] c,boolean flag,int start,int end){
        if (flag) {
            for (int i = start; i <= end; i++) {
                if (c[i] == 97) {
                    c[i] = 122;
                }else{
                    c[i]--;
                }
            }
        }else{
            for (int i = start; i <= end; i++) {
                if (c[i] == 122) {
                    c[i] = 97;
                }else{
                    c[i]++;
                }
            }
        }
    }*/

    /*public String shiftingLetters(String s, int[][] shifts){
        char[] c = s.toCharArray();
        int n = c.length;
        int[] count = new int[n];

        for (int i = 0; i < shifts.length; i++) {
            if (shifts[i][2] == 0) {
                for (int j = shifts[i][0]; j <= shifts[i][1]; j++) {
                    count[j]--;
                }
            }else{
                for (int j = shifts[i][0]; j <= shifts[i][1]; j++) {
                    count[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (count[i] == 0) {
                continue;
            }
            int temp = count[i] % 26;
            if (count[i] > 0) {
                if (c[i] + temp > 'z') {
                    c[i] = (char)('a' + (c[i]+temp-'z'-1));
                }else{
                    c[i] = (char) (c[i] + temp);
                }
            }else{
                if (c[i] + temp < 'a') {
                    c[i] = (char) ('z' + ('a' - (c[i] + temp)-1));
                }else{
                    c[i] = (char) (c[i] + temp);
                }
            }
        }

        return new String(c);
    }*/

    /*public String shiftingLetters(String s, int[][] shifts){
        List<int[]> add = new ArrayList<>(),sub = new ArrayList<>();
        char[] c = s.toCharArray();
        Arrays.sort(shifts,(x,y)->{
            return x[0]-y[0];
        });
        return s;
    }*/

    //差分数组
    /*public static String shiftingLetters(String s, int[][] shifts) {
        char[] cs = s.toCharArray();
        int[] diff = new int[cs.length];
        diff[0] = cs[0];
        for (int i = 1; i < cs.length; i++) {
            diff[i] = cs[i]-cs[i-1];
        }

        for (int i = 0; i < shifts.length; i++) {
            if (shifts[i][2] == 0) {
                diff[shifts[i][0]]--;
                if(shifts[i][1] < cs.length-1)
                    diff[shifts[i][1]+1]++;
            }else{
                diff[shifts[i][0]]++;
                if(shifts[i][1] < cs.length-1)
                    diff[shifts[i][1]+1]--;
            }
        }

        //注意判断第一个元素是否超出了界限，同时转换时还要注意可以取余数
        if (diff[0] < 'a') {
            cs[0] = (char) ('z' + (diff[0] - 'a'+ 1) % 26);
        } else if (diff[0] > 'z') {
            cs[0] = (char) ('a' + (diff[0] - 'z' - 1) % 26);
        }else{
            cs[0] = (char) diff[0];
        }
        for (int i = 1; i < cs.length; i++) {
            cs[i] = (char) (diff[i] + cs[i-1]);
            if (cs[i] < 'a') {
                cs[i] = (char) ('z' + (cs[i] - 'a'+ 1) % 26);
            }else if(cs[i] > 'z'){
                cs[i] = (char) ('a' + (cs[i] - 'z' - 1) % 26);
            }
        }

        return new String(cs);
    }*/

    //重新写一遍，以加深差分数组的印象，差分数组用于频繁的对一个区间进行更改的操作
    public String shiftingLetters(String s, int[][] shifts){
        char[] c = s.toCharArray();
        int n = c.length;
        int[] diff = new int[n];

        diff[0] = c[0];
        for (int i = 1; i < n; i++) {
            diff[i] = (c[i] - c[i-1]);
        }

        //更新差分数组
        for (int i = 0; i < shifts.length; i++) {
            if (shifts[i][2] == 0) {
                diff[shifts[i][0]]--;
                if (shifts[i][1] < n - 1) {
                    diff[shifts[i][1] + 1]++;
                }
            }else{
                diff[shifts[i][0]]++;
                if (shifts[i][1] < n - 1) {
                    diff[shifts[i][1] + 1]--;
                }
            }
        }

        c[0] = translate(diff[0]);
        for (int i = 1; i < n; i++) {
            c[i] = translate((char) (diff[i] + c[i-1]));
        }

        return new String(c);
    }
    //这里参数不能是char类型，数据截断会导致答案错误
    public char translate(int c){
        if (c < 'a') {
            return (char) ('z' + (c-'a'+1)%26);
        } else if (c > 'z') {
            return (char) ('a' + (c-'z'-1)%26);
        }else
            return (char) c;
    }

    //差分数组，重新练习一下
    /*public String shiftingLetters(String s, int[][] shifts) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] diff = new int[n+1];

        diff[0] = c[0];
        for(int i = 1;i < n;i++){
            diff[i] = c[i] - c[i-1];
        }
        for(int i = 0;i < shifts.length;i++){
            if(shifts[i][2] == 0){
                diff[shifts[i][0]]--;
                diff[shifts[i][1]+1]++;
            }else{
                diff[shifts[i][0]]++;
                diff[shifts[i][1]+1]--;
            }
        }
        char[] res = new char[n];
        //恢复第一个字符
        if(diff[0] < 'a'){
            res[0] = (char)('z'+1+(diff[0]-'a')%26);
        }else if(diff[0] > 'z'){
            res[0] = (char)('a'-1+(diff[0]-'z')%26);
        }else
            res[0] = (char)diff[0];
        for(int i = 1;i < n;i++){
            int temp = (diff[i] % 26) + res[i-1];
            if(temp < 'a'){
                temp = 'z'+1+(temp-'a');
            }else if(temp > 'z'){
                temp = 'a'-1+(temp-'z');
            }
            res[i] = (char)temp;
        }
        return new String(res);
    }*/
}
