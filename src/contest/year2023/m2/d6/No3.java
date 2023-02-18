package contest.year2023.m2.d6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class No3 {
    //使用字符串匹配，超时
    /*public int[][] substringXorQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length,m = queries.length;
        int[][] res = new int[m][2];
        Map<String,int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int val = queries[i][0] ^ queries[i][1];
            String t = null;
            if (val == 0) {
                t = "0";
            } else {
                StringBuilder sb = new StringBuilder();
                while (val > 0) {
                    sb.append(val & 1);
                    //逻辑右移一位
                    val >>>= 1;
                }
                t = sb.reverse().toString();
            }
            if (map.containsKey(t)) {
                res[i] = map.get(t);
                continue;
            }
            char[] temp = t.toCharArray();
            int l = temp.length;
            boolean flag = false;
            for (int j = 0; j < n + 1 - l; j++) {
                if (flag = compare(chars, temp, j)) {
                    res[i] = new int[]{j,j + l - 1};
                    break;
                }
            }
            if (!flag) {
                res[i] = new int[]{-1,-1};
            }
            map.put(t,res[i]);
        }
        return res;
    }
    private boolean compare(char[] c1,char[] c2,int b1){
        int m = c1.length,n = c2.length;
        for (int i = 0; i < n; i++) {
            if (c1[b1] == c2[i]) {
                b1++;
            } else {
                return false;
            }
        }
        return true;
    }*/

    //kmp匹配
    /*public int[][] substringXorQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length,m = queries.length;
        int[][] res = new int[m][2];
        for (int i = 0; i < m; i++) {
            int val = queries[i][0] ^ queries[i][1];
            StringBuilder sb = new StringBuilder();
            while (val > 0) {
                sb.append(val & 1);
                //逻辑右移一位
                val >>>= 1;
            }
            char[] temp = sb.reverse().toString().toCharArray();
            //和原字符串做字符串匹配
            int[] next = new int[temp.length];
            next[0] = -1;
            for (int j = 1; j < temp.length; j++) {
                int k = next[j-1];
                while (k != -1) {
                    if (temp[k] == temp[j]) {
                        next[j] = k + 1;
                        break;
                    }else {
                        k = next[k];
                    }
                }
                if (k == -1) {
                    next[j] = 0;
                }
            }
        }
        return res;
    }*/

    //800多ms，接近超时
    /*public int[][] substringXorQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length,m = queries.length;
        int[][] res = new int[m][2];
        Map<Long,int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                long val = 1;
                if (!map.containsKey(val)) {
                    map.put(val, new int[]{i, i});
                }
                for (int j = i + 1; j < n; j++) {
                    val = (val << 1) + (chars[j] == '1' ? 1 : 0);
                    if (!map.containsKey(val)) {
                        map.put(val, new int[]{i, j});
                    }
                }
            } else if (!map.containsKey(0L)){
                map.put(0L,new int[]{i,i});
            }
        }
        for (int i = 0; i < m; i++) {
            long val = queries[i][0] ^ queries[i][1];
            res[i] = map.getOrDefault(val,new int[]{-1,-1});
        }
        return res;
    }*/

    //使用set过滤仍然是800多ms，没有提升效率
    /*public int[][] substringXorQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length,m = queries.length;
        int[][] res = new int[m][2];
        Map<Long,int[]> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                if(!set.contains(s.substring(i))) set.add(s.substring(i));
                else continue;
                long val = 1;
                if (!map.containsKey(val)) {
                    map.put(val, new int[]{i, i});
                }
                for (int j = i + 1; j < n; j++) {
                    val = (val << 1) + (chars[j] == '1' ? 1 : 0);
                    if (!map.containsKey(val)) {
                        map.put(val, new int[]{i, j});
                    }
                }
            } else if (!map.containsKey(0L)){
                map.put(0L,new int[]{i,i});
            }
        }
        for (int i = 0; i < m; i++) {
            long val = queries[i][0] ^ queries[i][1];
            res[i] = map.getOrDefault(val,new int[]{-1,-1});
        }
        return res;
    }*/

    public int[][] substringXorQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length,m = queries.length;
        int[][] res = new int[m][2];
        Map<Integer,int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                int val = 1,c = 1;
                if (!map.containsKey(val)) {
                    map.put(val, new int[]{i, i});
                }
                for (int j = i + 1; j < n && c < 31; j++) {
                    val = (val << 1) + (chars[j] == '1' ? 1 : 0);
                    if (!map.containsKey(val)) {
                        map.put(val, new int[]{i, j});
                    }
                    c++;
                }
            } else if (!map.containsKey(0)){
                map.put(0,new int[]{i,i});
            }
        }
        for (int i = 0; i < m; i++) {
            int val = queries[i][0] ^ queries[i][1];
            res[i] = map.getOrDefault(val,new int[]{-1,-1});
        }
        return res;
    }
}
