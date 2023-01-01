package contest.year2022.m10.m10d9;

public class No3 {
    /**
     *      使用机器人打印字典序最小的字符串
     */

    /*public String robotWithString(String s) {
        char[] c = s.toCharArray();
        int n = c.length,up = 0,l = 0;
        int[] set = new int[26],dp = new int[n];
        char[] stack = new char[n];
        set[c[0]-'a']++;
        dp[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            int loc = c[i]-'a';
            set[loc]++;
            loc--;
            while (loc > -1) {
                dp[i] += set[loc];
                loc--;
            }
        }
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            while(set[l] == 0)
                l++;
            int loc = c[i] - 'a';

            if (loc == l) {
                sb.append(c[i]);
                set[l]--;
            } else {
                if (dp[i] == 0) {
                    while (up > 0 && stack[up - 1] - 'a' < c[i]) {
                        sb.append(stack[up-1]);
                        set[stack[up-1] - 'a']--;
                        up--;
                    }
                }
                while (up > 0 && stack[up-1]-'a' == l) {
                    sb.append(stack[up-1]);
                    set[l]--;
                    while(set[l] == 0)
                        l++;
                    up--;
                }
                stack[up++] = c[i];
            }
        }
        while (up > 0) {
            sb.append(stack[up-1]);
            up--;
        }

        return sb.toString();
    }*/


    /*public String robotWithString(String s){
        char[] c = s.toCharArray();
        int n = c.length,up = 0,l = 0;
        char[] stack = new char[n];
        int[] set = new int[26],dp = new int[n];
        StringBuilder sb = new StringBuilder(n);
        set[c[n-1]-'a']++;
        dp[n-1] = Integer.MAX_VALUE;
        for (int i = n-2; i > -1; i--) {
            int loc = c[i]-'a';
            dp[i] = Integer.MAX_VALUE;
            set[loc]++;
            loc--;
            while (loc > -1) {
                if(set[loc] > 0)
                    dp[i] = Math.min(dp[i],loc);
                loc--;
            }
        }

        for (int i = 0; i < n; i++) {
            while (up > 0 && stack[up-1] - 'a' == l) {
                sb.append(stack[up-1]);
                set[l]--;
                up--;
            }
            while(set[l] == 0)
                l++;
            int loc = c[i] - 'a';

            if (loc == l) {
                sb.append(c[i]);
                set[l]--;
            }else{
                if (dp[i] > l) {
                    while (up > 0 && stack[up-1] - 'a' <= dp[i]) {
                        sb.append(stack[up-1]);
                        set[stack[up-1]-'a']--;
                        up--;
                    }
                    while(set[l] == 0)
                        l++;
                }

                stack[up++] = c[i];
            }
        }
        while (up > 0) {
            sb.append(stack[up-1]);
            up--;
        }

        return sb.toString();
    }*/


    //成功了，26ms
    public String robotWithString(String s) {
        char[] c = s.toCharArray();
        int n = c.length,up = 0,l = 0;
        StringBuilder sb = new StringBuilder();
        int[] set = new int[26];
        char[] stack = new char[n];

        for (int i = 0; i < n; i++) {
            set[c[i]-'a']++;
        }

        for (int i = 0; i < n; i++) {
            while(set[l] == 0)
                l++;

            int loc = c[i] - 'a';
            while (up > 0 && stack[up - 1] - 'a' <= l) {
                sb.append(stack[up-1]);
                up--;
            }
            if (loc == l) {
                sb.append(c[i]);
                set[l]--;
            }else{
                stack[up++] = c[i];
                set[loc]--;
            }
        }

        while (up > 0) {
            sb.append(stack[up-1]);
            up--;
        }

        return sb.toString();
    }
}
