package contest.year2022.m11.d12;

public class No4 {
    //重点在于，不知道分割出多少来给limit
    //看题解的，直接模拟就行了，18ms
    public String[] splitMessage(String message, int limit) {
        char[] chars = message.toCharArray();
        int n = chars.length;

        for (int i = 1,cap = 0,tail; ; i++) {
            if(i < 10)
                tail = 5;
            else if (i < 100) {
                //i刚达到10的时候，前面的元素必须都减少使用一个字符，后面的都是以此类推
                if(i == 10)
                    cap -= 9;
                tail = 7;
            } else if (i < 1000) {
                if (i == 100)
                    cap -= 99;
                tail = 9;
            } else {
                if (i == 1000)
                    cap -= 999;
                tail = 11;
            }
            //当limit仅能够满足（甚至满足不了）尾部所需要的大小时，无法将整个字符串切割成符合条件的情况
            if(limit <= tail)
                return new String[]{};
            //cap存储的是当前使用字符串中字符的数量，所以要减去尾部的大小
            cap += limit - tail;
            //当还没有用完字符串中的所有字符的时候继续叠加
            if(cap < n)
                continue;

            //拼接结果
            String[] res = new String[i];
            StringBuilder sb;
            //len是尾部固定的长度
            int loc = 0,len = 3 + log10(i);
            for (int j = 1; j <= i; j++) {
                //计算出当前应该从字符串中抽取的数量
                int c = limit - (len + log10(j));
                sb = new StringBuilder();
                for (int k = 0; k < c && loc < n; k++) {
                    sb.append(chars[loc++]);
                }
                sb.append("<").append(j).append("/").append(i).append(">");
                res[j-1] = sb.toString();
            }

            return res;
        }
    }
    public int log10(int num){
        if(num < 10)
            return 1;
        else if(num < 100)
            return 2;
        else if(num < 1000)
            return 3;
        else
            return 4;
    }
}
