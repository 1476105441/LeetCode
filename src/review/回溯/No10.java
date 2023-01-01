package review.回溯;

public class No10 {
    /**
     *      删除无效的括号
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     */

    //失败，无法解决重复的问题
    /*List<String> res;
    String s;
    char[] chars;
    int l,r;
    public List<String> removeInvalidParentheses(String s){
        res = new ArrayList<>();
        this.s = s;
        chars = s.toCharArray();
        int ls = 0,rs = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls == 0) {
                    rs++;
                }else{
                    ls--;
                }
            }
        }
        l = ls;
        r = rs;
        find(0,ls,rs,0,0,new StringBuilder());

        return res;
    }

    public void find(int loc,int ls,int rs,int left,int count,StringBuilder sb){
        if (loc == chars.length) {
            if (ls == 0 && rs == 0 && left == 0) {
                res.add(sb.toString());
            }
            return;
        }
        if(loc != 0 && chars[loc] == chars[loc-1]){
            count++;
        }else
            count=1;
        if (chars[loc] == ')') {
            //此次回溯是失败的
            if (left == 0 && rs == 0) {
                return;
            }
            //选择删除，加上剪枝策略
            if (rs != 0 && (loc == 0 || count <= r)) {
                find(loc+1,ls,rs-1,left,count,sb);
            }
            if (left != 0) {
                sb.append(chars[loc]);
                find(loc+1,ls,rs,left-1,count,sb);
                sb.deleteCharAt(sb.length()-1);
            }
        } else if (chars[loc] == '(') {
            if (ls != 0 && (loc == 0 || count <= l)) {
                find(loc+1,ls-1,rs,left,count,sb);
            }

            sb.append(chars[loc]);
            find(loc+1,ls,rs,left+1,count,sb);
            sb.deleteCharAt(sb.length()-1);
        }else{
            sb.append(chars[loc]);
            find(loc+1,ls,rs,left,count,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/

    //失败
    /*List<String> res;
    String s;
    char[] chars;
    public List<String> removeInvalidParentheses(String s){
        res = new ArrayList<>();
        this.s = s;
        chars = s.toCharArray();
        int ls = 0,rs = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls == 0) {
                    rs++;
                }else{
                    ls--;
                }
            }
        }

        find(0,ls,rs,0,"");

        return res;
    }

    public void find(int loc,int ls,int rs,int left,String pre){
        if (loc == chars.length) {
            if (ls == 0 && rs == 0 && left == 0) {
                res.add(pre);
            }
            return;
        }

        for (int i = loc; i < chars.length; i++) {
            if (loc != i && chars[i] == chars[i - 1]) {
                continue;
            }
            if (chars[i] == ')') {
                //此次回溯是失败的
                if (left == 0 && rs == 0) {
                    return;
                }
                if (left != 0) {
                    find(i+1,ls,rs,left-1,pre+chars[i]);
                }
                if (rs != 0) {
                    rs--;
                }else{
                    pre = pre+chars[i];
                }
            } else if (chars[i] == '(') {
                find(i+1,ls,rs,left+1,pre+chars[i]);
                if (ls != 0) {
                    ls--;
                }else{
                    pre = pre+chars[i];
                }
            }else{
                pre = pre+chars[i];
                find(i+1,ls,rs,left,pre);
            }
        }
    }*/

    //经历了非常折磨的过程之
    //后，终于成功了
    /*List<String> res;
    char[] chars;
    StringBuilder sb;
    public List<String> removeInvalidParentheses(String s){
        res = new ArrayList<>();
        chars = s.toCharArray();
        sb = new StringBuilder();

        int ls = 0,rs = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls == 0) {
                    rs++;
                }else{
                    ls--;
                }
            }
        }
        find(0,ls,rs,0,0);
        return res;
    }

    public void find(int loc,int lr,int rr,int left,int start){
        if (loc == chars.length) {
            if (lr == 0 && rr == 0 && left == 0) {
                res.add(sb.toString());
            }
            return;
        }
        if (loc != start && chars[loc] == chars[loc - 1]) {
            sb.append(chars[loc]);
            if (chars[loc] == '(') {
                find(loc+1,lr,rr,left+1,start);
            }else if(chars[loc] == ')' && left > 0){
                find(loc+1,lr,rr,left-1,start);
            }else{
                find(loc+1,lr,rr,left,start);
            }
            sb.deleteCharAt(sb.length()-1);
        }else{
            if (chars[loc] == '(') {
                if (lr > 0) {
                    find(loc+1,lr-1,rr,left,loc+1);
                }
                sb.append('(');
                find(loc+1,lr,rr,left+1,start);
                sb.deleteCharAt(sb.length()-1);
            } else if (chars[loc] == ')') {
                if (left == 0 && rr == 0) {
                    return;
                }
                if (rr > 0) {
                    find(loc+1,lr,rr-1,left,loc+1);
                }
                if (left != 0) {
                    sb.append(')');
                    find(loc+1,lr,rr,left-1,start);
                    sb.deleteCharAt(sb.length()-1);
                }
            }else{
                sb.append(chars[loc]);
                find(loc+1,lr,rr,left,start);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }*/

    //再重新理一下思路
    /*List<String> res;
    StringBuilder sb;
    char[] chars;
    public List<String> removeInvalidParentheses(String s){
        res = new ArrayList<>();
        sb = new StringBuilder();
        int ls = 0,rs = 0;
        chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls > 0) {
                    ls--;
                }else
                    rs++;
            }
        }

        dfs(0,0,ls,rs,0);

        return res;
    }
    public void dfs(int loc,int start,int ls,int rs,int left){
        if (loc == chars.length) {
            if (ls == 0 && rs == 0 && left == 0) {
                res.add(sb.toString());
            }
            return;
        }

        if (loc != start && chars[loc] == chars[loc - 1]) {
            //此种情况选择不删除
            sb.append(chars[loc]);
            if (chars[loc] == '(') {
                dfs(loc+1,start,ls,rs,left+1);
            } else if (chars[loc] == ')' && left > 0) {
                dfs(loc+1,start,ls,rs,left-1);
            }else{
                dfs(loc+1,start,ls,rs,left);
            }
            sb.deleteCharAt(sb.length()-1);
        } else if (chars[loc] == '(') {
            if (ls > 0) {
                //尝试删除
                dfs(loc+1,loc+1,ls-1,rs,left);
            }
            sb.append(chars[loc]);
            dfs(loc+1,start,ls,rs,left+1);
            sb.deleteCharAt(sb.length()-1);
        } else if (chars[loc] == ')') {
            if (rs > 0) {
                dfs(loc+1,loc+1,ls,rs-1,left);
            }
            if(left > 0){
                sb.append(')');
                dfs(loc+1,start,ls,rs,left-1);
                sb.deleteCharAt(sb.length()-1);
            }
        }else{
            sb.append(chars[loc]);
            dfs(loc+1,start,ls,rs,left);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/

    //bfs解法，其实就是每次删除一个括号，然后
    //判断当前是否有符合条件的括号，有的话就是
    //我们需要结果
    /*public List<String> removeInvalidParentheses(String s){
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.add(s);
        while(true){
            for (String s1 : set) {
                if (examine(s1)) {
                    res.add(s1);
                }
            }
            if (!res.isEmpty()) {
                return res;
            }
            Set<String> newSet = new HashSet<>();
            for (String temp : set) {
                for (int i = 0; i < temp.length(); i++) {
                    if (i > 0 && temp.charAt(i) == temp.charAt(i-1)) {
                        continue;
                    }
                    newSet.add(temp.substring(0, i) + temp.substring(i + 1));
                }
            }
            set = newSet;
        }
    }
    public boolean examine(String s){
        char[] chars = s.toCharArray();
        int c = 0;
        for (int i = 0; i < chars.length; i++) {
            if (c < 0) {
                return false;
            }
            if (chars[i] == '(') {
                c++;
            } else if (chars[i] == ')') {
                c--;
            }
        }
        return c == 0;
    }*/

    //枚举状态子集，249ms
    /*List<String> res;
    char[] chars;
    String temp;
    public List<String> removeInvalidParentheses(String s){
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        //msk是要删掉的下标集合，采用状态压缩方法，每个二进制位表示集合中的一个元素
        List<Integer> msk1 = new ArrayList<>();
        List<Integer> msk2 = new ArrayList<>();
        Set<String> set = new HashSet<>();
        chars = s.toCharArray();
        int ls = 0,rs = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
                left.add(i);
            } else if (chars[i] == ')') {
                if (ls == 0) {
                    rs++;
                }else{
                    ls--;
                }
                right.add(i);
            }
        }

        int m = left.size(),n = right.size();
        for (int i = 0; i < (1 << m); i++) {
            if(countBit(i) == ls){
                msk1.add(i);
            }
        }
        for (int i = 0; i < (1 << n); i++) {
            if(countBit(i) == rs){
                msk2.add(i);
            }
        }

        for (Integer i : msk1) {
            for (Integer j : msk2){
                if (examine(i, j,left,right)) {
                    set.add(temp);
                }
            }
        }
        res = new ArrayList<>(set);

        return res;
    }

    //统计二进制位为1的个数
    public int countBit(int num){
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }

    public boolean examine(int left,int right,List<Integer> lList,List<Integer> rList){
        int l,r;
        Set<Integer> set = new HashSet<>();
        while (left != 0 || right != 0) {
            l = left & (-left);
            r = right & (-right);
            if (l != 0) {
                set.add(lList.get((int)(Math.log(l)/Math.log(2))));
            }
            if (r != 0) {
                set.add(rList.get((int)(Math.log(r)/Math.log(2))));
            }
            left -= l;
            right -= r;
        }

        l = 0;
        r = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (!set.contains(i)) {
                sb.append(chars[i]);
                if (chars[i] == '(') {
                    l++;
                } else if (chars[i] == ')') {
                    if (l > 0) {
                        l--;
                    }else{
                        r++;
                    }
                }
            }
        }
        temp = sb.toString();
        return l == 0 && r == 0;
    }*/
}
