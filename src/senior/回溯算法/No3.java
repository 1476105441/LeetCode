package senior.回溯算法;

import java.util.*;

public class No3 {
    /**
     *          删除无效的括号
     * 给你一个由若干括号和字母组成的字符串s，删除最小数量的
     * 无效括号，使得输入的字符串有效。
     * 返回所有可能的结果，答案可以按任意顺序返回。
     *
     * 提示：
     *     1 <= s.length <= 25
     *     s 由小写英文字母以及括号 '(' 和 ')' 组成
     *     s 中至多含 20 个括号
     */

    /**
     * 想法：统计左右括号的数量，找到要删除的括号
     * 是左括号还是右括号。在进行递归时，只有当应
     * 删除的括号个数大于另一个括号时才有删除操作
     */

    //失败，思想太局限了，没有考虑到另外的情况
    /*List<String> strings;
    char[] chars;
    StringBuilder sb;
    char delete;
    char retain;
    int span;
    public List<String> removeInvalidParentheses(String s) {
        chars = s.toCharArray();
        strings = new ArrayList<>();
        sb = new StringBuilder();
        int should = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                should++;
            } else if (chars[i] == ')') {
                should--;
            }
        }
        if (should > 0) {
            delete = '(';
            retain = ')';
            span = -1;
        }else{
            delete = ')';
            retain = '(';
            should *= -1;
            span = 1;
        }

        if (span == 1) {
            dfs(0,should,0);
        }else{
            dfs(0,should,chars.length-1);
        }

        return strings;
    }

    public void dfs(int count,int should,int loc){
        if (loc == chars.length || loc < 0) {
            if (count == 0 && should == 0) {
                strings.add(sb.toString());
            }
            return;
        }

        int newLoc = loc+span;
        if (chars[loc] == delete) {
            if (count == 0) {
                dfs(count,should-1,newLoc);
            }else{
                sb.append(chars[loc]);
                dfs(count-1,should,newLoc);
                sb.deleteCharAt(sb.length()-1);
                if (newLoc == -1 || newLoc == chars.length) {
                    return;
                }
                if (chars[newLoc] != delete) {
                    dfs(count,should-1,newLoc);
                }
            }
        }else {
            sb.append(chars[loc]);
            if (chars[loc] == retain) {
                count++;
            }
            dfs(count,should,newLoc);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/


    //仍然失败
    /*List<String> strings;
    char[] chars;
    StringBuilder sb;
    public List<String> removeInvalidParentheses(String s) {
        chars = s.toCharArray();
        strings = new ArrayList<>();
        sb = new StringBuilder();
        int ls = 0,rs = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls > 0) {
                    ls--;
                }else{
                    rs++;
                }
            }
        }

        dfs(0,rs,ls,0);

        return strings;
    }

    public void dfs(int left,int rs,int ls,int loc){
        if (loc == chars.length || rs < 0 || ls < 0) {
            if (rs == 0 && ls == 0) {
                strings.add(sb.toString());
            }
            return;
        }

        if (chars[loc] == '(') {
            sb.append('(');
            dfs(left+1,rs,ls,loc+1);
            sb.deleteCharAt(sb.length()-1);
            if (ls > 0) {
                if (loc + 1 == chars.length || chars[loc + 1] != '(' || (ls > 1 && strings.isEmpty())) {
                    dfs(left,rs,ls-1,loc+1);
                }
            }
        } else if (chars[loc] == ')') {
            if (left == 0) {
                dfs(left,rs-1,ls,loc+1);
            }else{
                sb.append(')');
                dfs(left-1,rs,ls,loc+1);
                sb.deleteCharAt(sb.length()-1);
                if (rs > 0) {
                    if (loc + 1 == chars.length || chars[loc + 1] != ')') {
                        dfs(left,rs-1,ls,loc+1);
                    }
                }
            }
        }else {
            sb.append(chars[loc]);
            dfs(left,rs,ls,loc+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/


    //每一个都删除，采用剪枝方案减少时间，使用相邻两个相同的字符不需要删除
    //总算成功了，1ms，要注意删除的条件，不是任意条件下都能删除的
    /*List<String> res;
    char[] chars;
    StringBuilder sb;

    public List<String> removeInvalidParentheses(String s) {
        res = new ArrayList<>();
        chars = s.toCharArray();
        sb = new StringBuilder();

        int ls = 0, rs = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls > 0) {
                    ls--;
                } else {
                    rs++;
                }
            }
        }

        dfs(0, 0, ls, rs,0);

        return res;
    }

    public void dfs(int loc, int start, int ls, int rs,int left) {
        if (loc == chars.length) {
            if (ls == 0 && rs == 0 && left == 0) {
                res.add(sb.toString());
            }
            return;
        }
        //剩余的不够减
        if (chars.length - loc < ls + rs) {
            return;
        }

        //特殊情况不需要再重复删除

         *   为什么会有个不等于start的条件呢，因为一开始将每个都删除的时候
         * start 就是 loc，这个时候不需要判断是否有重复的
         *   start 的作用：我的理解是，start用来标记最近一次进行删除后的，
         * 被删除节点的最近一个节点

        if (loc != start && chars[loc] == chars[loc - 1]) {
            //不删除，因为当前节点的前一个节点是和它相同的，删除前一
            //个节点的作用和删除当前节点的作用是一样的，不论上一个节
            //点在本次遍历中是否删除，当前节点都不用删除，因为如果上
            //一个节点在本次遍历中没有删除，但是遍历的顺序是先删除后
            //不删除，所以此时是已经遍历过了删除上一个节点的情况，所
            //以当前节点不需要删除
            sb.append(chars[loc]);
            if (chars[loc] == '(') {
                dfs(loc+1,start,ls,rs,left+1);
            }else if(chars[loc] == ')' && left > 0){
                dfs(loc+1,start,ls,rs,left-1);
            }else{
                dfs(loc+1,start,ls,rs,left);
            }
            sb.deleteCharAt(sb.length()-1);
        } else if (chars[loc] == '(') {
            //删除
            if (ls > 0) {
                dfs(loc + 1, loc + 1, ls - 1, rs,left);
            }
            //不删除
            sb.append(chars[loc]);
            dfs(loc + 1, start, ls, rs,left+1);
            sb.deleteCharAt(sb.length() - 1);
        } else if (chars[loc] == ')') {
            //删除
            if (rs > 0) {
                dfs(loc+1,loc + 1,ls,rs-1,left);
            }
            //不删除
            sb.append(chars[loc]);
            if(left > 0){
                dfs(loc+1,start,ls,rs,left-1);
            }else{
                dfs(loc+1,start,ls,rs,left);
            }
            sb.deleteCharAt(sb.length()-1);
        } else {
            sb.append(chars[loc]);
            dfs(loc+1,start,ls,rs,left);
            sb.deleteCharAt(sb.length()-1);
        }
    }*/


    //广度优先遍历 38ms
    /*List<String> res;
    char[] chars;

    public List<String> removeInvalidParentheses(String s) {
        res = new ArrayList<>();
        chars = s.toCharArray();
        int ls = 0, rs = 0, length;
        Set<Node> set = new HashSet<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ls++;
            } else if (chars[i] == ')') {
                if (ls == 0) {
                    rs++;
                } else {
                    ls--;
                }
            }
        }
        if (chars[0] == '(') {
            set.add(new Node(chars[0] + "", ls, rs, 1));
            if (ls > 0) {
                set.add(new Node("", ls - 1, rs, 0));
            }
        } else if (chars[0] == ')') {
            set.add(new Node("", ls, rs - 1, 0));
        } else {
            set.add(new Node(chars[0] + "", ls, rs, 0));
        }

        for (int i = 1; i < chars.length; i++) {
            length = set.size();
            int j = 0;
            Set<Node> newSet = new HashSet<>();
            for (Node node : set) {
                if (j == length) {
                    break;
                }
                if (chars.length - i < node.ls + node.rs){
                    j++;
                    continue;
                }
                if (chars[i] == '(') {
                    newSet.add(new Node(node.temp+chars[i], node.ls, node.rs, node.left + 1));
                    if (node.ls > 0) {
                        newSet.add(new Node(node.temp, node.ls - 1, node.rs, node.left));
                    }
                } else if (chars[i] == ')') {
                    if (node.left > 0) {
                        newSet.add(new Node(node.temp+chars[i], node.ls, node.rs, node.left - 1));
                    }
                    if (node.rs > 0) {
                        newSet.add(new Node(node.temp, node.ls, node.rs - 1, node.left));
                    }
                }else{
                    newSet.add(new Node(node.temp+chars[i], node.ls, node.rs, node.left));
                }
                j++;
            }
            set = newSet;
        }

        Set<String> set1 = new HashSet<>();
        for (Node node : set) {
            if (node.ls == 0 && node.rs == 0 && node.left == 0) {
                if (!set1.contains(node.temp)) {
                    res.add(node.temp);
                    set1.add(node.temp);
                }
            }
        }

        return res;
    }*/


    //枚举状态子集
    /*public List<String> removeInvalidParentheses(String s) {
        int lremove = 0;
        int rremove = 0;
        //left和right存储的是字符串中对应左括号和右括号的下标
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        Set<String> cnt = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left.add(i);
                lremove++;
            } else if (s.charAt(i) == ')') {
                right.add(i);
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }

        int m = left.size();
        int n = right.size();
        List<Integer> maskArr1 = new ArrayList<Integer>();
        List<Integer> maskArr2 = new ArrayList<Integer>();
        for (int i = 0; i < (1 << m); i++) {
            if (Integer.bitCount(i) != lremove) {
                continue;
            }
            maskArr1.add(i);
        }
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) != rremove) {
                continue;
            }
            maskArr2.add(i);
        }
        for (int mask1 : maskArr1) {
            for (int mask2 : maskArr2) {
                if (checkValid(s, mask1, left, mask2, right)) {
                    cnt.add(recoverStr(s, mask1, left, mask2, right));
                }
            }
        }
        for (String v : cnt) {
            ans.add(v);
        }

        return ans;
    }

    //检查合法性
    private boolean checkValid(String str, int lmask, List<Integer> left, int rmask, List<Integer> right) {
        //pos1用于定位左括号集合
        int pos1 = 0;
        int pos2 = 0;
        int cnt = 0;

        for (int i = 0; i < str.length(); i++) {
            if (pos1 < left.size() && i == left.get(pos1)) {
                if ((lmask & (1 << pos1)) == 0) {
                    cnt++;
                }
                pos1++;
            } else if (pos2 < right.size() && i == right.get(pos2)) {
                if ((rmask & (1 << pos2)) == 0) {
                    cnt--;
                    if (cnt < 0) {
                        return false;
                    }
                }
                pos2++;
            }
        }

        return cnt == 0;
    }

    //恢复字符串？
    private String recoverStr(String str, int lmask, List<Integer> left, int rmask, List<Integer> right) {
        StringBuilder sb = new StringBuilder();
        int pos1 = 0;
        int pos2 = 0;

        for (int i = 0; i < str.length(); i++) {
            if (pos1 < left.size() && i == left.get(pos1)) {
                if ((lmask & (1 << pos1)) == 0) {
                    sb.append(str.charAt(i));
                }
                pos1++;
            } else if (pos2 < right.size() && i == right.get(pos2)) {
                if ((rmask & (1 << pos2)) == 0) {
                    sb.append(str.charAt(i));
                }
                pos2++;
            } else {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }*/


    //重新写一遍，10ms，使用set
    /*char[] c;
    Set<String> res;  //使用set去重
    public List<String> removeInvalidParentheses(String s) {
        //第一步，统计出要删除的左括号和右括号数量
        c = s.toCharArray();
        res = new HashSet<>();
        int l=0,r=0;

        for(int i = 0;i < c.length;i++){
            if(c[i] == ')'){
                if(l > 0)
                    l--;
                else
                    r++;
            }else if(c[i] == '('){
                l++;
            }
        }

        recursion(0,0,l,r,"");

        return new ArrayList<>(res);
    }
    public void recursion(int loc,int lc,int l,int r,String s){
        if(loc == c.length){
            //只有全部为0时，才是符合条件的
            if(lc == 0 && l == 0 && r == 0){
                res.add(s);
            }
            return;
        }
        //剪枝，如果剩下的元素比要删除的元素个数要少，直接返回
        if(c.length-loc < l + r){
            return;
        }

        //判断当前字符
        if(c[loc] == ')'){
            //如果前面没有左括号，必须要删除
            if(lc == 0){
                //如果不能删，则这条分支错误，直接返回
                if(r == 0)
                    return;
                //可以删除，尝试删除
                recursion(loc+1,lc,l,r-1,s);
                return;  //防止执行到后面，直接return
            }
            if(r > 0){
                //先尝试删除
                recursion(loc+1,lc,l,r-1,s);
            }
            //再尝试不删除
            recursion(loc+1,lc-1,l,r,s+c[loc]);
        }else if(c[loc] == '('){
            //先尝试不删除
            recursion(loc+1,lc+1,l,r,s+c[loc]);
            //再尝试删除
            if(l > 0){
                recursion(loc+1,lc,l-1,r,s);
            }
        }else{
            recursion(loc+1,lc,l,r,s+c[loc]);
        }
    }*/

    //换成StringBuilder，3ms，提升巨大
    /*char[] c;
    Set<String> res;  //使用set去重
    public List<String> removeInvalidParentheses(String s) {
        //第一步，统计出要删除的左括号和右括号数量
        c = s.toCharArray();
        res = new HashSet<>();
        int l=0,r=0;

        for(int i = 0;i < c.length;i++){
            if(c[i] == ')'){
                if(l > 0)
                    l--;
                else
                    r++;
            }else if(c[i] == '('){
                l++;
            }
        }

        recursion(0,0,l,r,new StringBuilder());

        return new ArrayList<>(res);
    }
    public void recursion(int loc,int lc,int l,int r,StringBuilder s){
        if(loc == c.length){
            //只有全部为0时，才是符合条件的
            if(lc == 0 && l == 0 && r == 0){
                res.add(s.toString());
            }
            return;
        }
        //剪枝，如果剩下的元素比要删除的元素个数要少，直接返回
        if(c.length-loc < l + r){
            return;
        }

        //判断当前字符
        if(c[loc] == ')'){
            //如果前面没有左括号，必须要删除
            if(lc == 0){
                //如果不能删，则这条分支错误，直接返回
                if(r == 0)
                    return;
                //可以删除，尝试删除
                recursion(loc+1,lc,l,r-1,s);
                return;  //防止执行到后面，直接return
            }
            if(r > 0){
                //先尝试删除
                recursion(loc+1,lc,l,r-1,s);
            }
            //再尝试不删除
            s.append(c[loc]);
            recursion(loc+1,lc-1,l,r,s);
            s.deleteCharAt(s.length()-1);
        }else if(c[loc] == '('){
            //先尝试不删除
            s.append(c[loc]);
            recursion(loc+1,lc+1,l,r,s);
            s.deleteCharAt(s.length()-1);
            //再尝试删除
            if(l > 0){
                recursion(loc+1,lc,l-1,r,s);
            }
        }else{
            s.append(c[loc]);
            recursion(loc+1,lc,l,r,s);
            s.deleteCharAt(s.length()-1);
        }
    }*/


    //写一个广度优先遍历，338ms
    /*public List<String> removeInvalidParentheses(String s){
        char[] c = s.toCharArray();
        int l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if(c[i] == ')'){
                if(l > 0)
                    l--;
                else
                    r++;
            }else if(c[i] == '('){
                l++;
            }
        }
        Set<String> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        if(c[0] == '('){
            queue.add(new Node(c[0]+"",l,r,1));
            if(l > 0)
                queue.add(new Node("",l-1,r,0));
        } else if (c[0] == ')') {
            queue.add(new Node("",l,r-1,0));
        }else
            queue.add(new Node(c[0]+"",l,r,0));

        for (int i = 1; i < c.length; i++) {
            int size = queue.size();

            while (size > 0) {
                Node node = queue.poll();
                if (c[i] == ')') {
                    if (node.left == 0) {
                        if (node.rs > 0) {
                            node.rs--;
                            queue.add(node);
                        }
                    }else{
                        if(node.rs > 0)
                            queue.add(new Node(node.temp,node.ls,node.rs-1,node.left));
                        node.temp += c[i];
                        node.left--;
                        queue.add(node);
                    }
                }else if(c[i] == '('){
                    if(node.ls > 0)
                        queue.add(new Node(node.temp,node.ls-1,node.rs,node.left));
                    node.temp += c[i];
                    node.left++;
                    queue.add(node);
                }else{
                    node.temp += c[i];
                    queue.add(node);
                }
                size--;
            }
        }

        for (Node node : queue){
            if(node.left == 0 && node.rs == 0 && node.ls == 0)
                set.add(node.temp);
        }

        return new ArrayList<>(set);
    }*/

    //bfs，优化了一段代码，时间就减少到了16ms
    /*public List<String> removeInvalidParentheses(String s){
        char[] c = s.toCharArray();
        int l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if(c[i] == ')'){
                if(l > 0)
                    l--;
                else
                    r++;
            }else if(c[i] == '('){
                l++;
            }
        }
        Set<String> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        if(c[0] == '('){
            queue.add(new Node(c[0]+"",l,r,1));
            if(l > 0)
                queue.add(new Node("",l-1,r,0));
        } else if (c[0] == ')') {
            queue.add(new Node("",l,r-1,0));
        }else
            queue.add(new Node(c[0]+"",l,r,0));

        for (int i = 1; i < c.length; i++) {
            int size = queue.size();

            while (size > 0) {
                Node node = queue.poll();
                //性能优化的关键，剪枝策略，直接升到16ms
                if (c.length - i < node.ls + node.rs) {
                    size--;
                    continue;
                }
                if (c[i] == ')') {
                    if (node.left == 0) {
                        if (node.rs > 0) {
                            node.rs--;
                            queue.add(node);
                        }
                    }else{
                        if(node.rs > 0)
                            queue.add(new Node(node.temp,node.ls,node.rs-1,node.left));
                        node.temp += c[i];
                        node.left--;
                        queue.add(node);
                    }
                }else if(c[i] == '('){
                    if(node.ls > 0)
                        queue.add(new Node(node.temp,node.ls-1,node.rs,node.left));
                    node.temp += c[i];
                    node.left++;
                    queue.add(node);
                }else{
                    node.temp += c[i];
                    queue.add(node);
                }
                size--;
            }
        }

        for (Node node : queue){
            if(node.left == 0 && node.rs == 0 && node.ls == 0)
                set.add(node.temp);
        }

        return new ArrayList<>(set);
    }*/

    //bfs，使用StringBuilder，性能提升不明显，11ms
    /*public List<String> removeInvalidParentheses(String s){
        char[] c = s.toCharArray();
        int l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if(c[i] == ')'){
                if(l > 0)
                    l--;
                else
                    r++;
            }else if(c[i] == '('){
                l++;
            }
        }
        Set<String> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        if(c[0] == '('){
            queue.add(new Node(new StringBuilder(c[0]+""),l,r,1));
            if(l > 0)
                queue.add(new Node(new StringBuilder(),l-1,r,0));
        } else if (c[0] == ')') {
            queue.add(new Node(new StringBuilder(),l,r-1,0));
        }else
            queue.add(new Node(new StringBuilder(c[0]+""),l,r,0));

        for (int i = 1; i < c.length; i++) {
            int size = queue.size();

            while (size > 0) {
                Node node = queue.poll();
                //性能优化的关键，剪枝策略，直接升到16ms
                if (c.length - i < node.ls + node.rs) {
                    size--;
                    continue;
                }
                if (c[i] == ')') {
                    if (node.left == 0) {
                        if (node.rs > 0) {
                            node.rs--;
                            queue.add(node);
                        }
                    }else{
                        if(node.rs > 0)
                            queue.add(new Node(new StringBuilder(node.temp.toString()),node.ls,node.rs-1,node.left));
                        node.temp.append(c[i]);
                        node.left--;
                        queue.add(node);
                    }
                }else if(c[i] == '('){
                    if(node.ls > 0)
                        queue.add(new Node(new StringBuilder(node.temp.toString()),node.ls-1,node.rs,node.left));
                    node.temp.append(c[i]);
                    node.left++;
                    queue.add(node);
                }else{
                    node.temp.append(c[i]);
                    queue.add(node);
                }
                size--;
            }
        }

        for (Node node : queue){
            if(node.left == 0 && node.rs == 0 && node.ls == 0)
                set.add(node.temp.toString());
        }

        return new ArrayList<>(set);
    }*/


    //枚举状态子集，使用StringBuilder，13ms
    /*public List<String> removeInvalidParentheses(String s){
        char[] c = s.toCharArray();
        int l = 0,r = 0,n = c.length;
        List<Integer> left = new ArrayList<>(),right = new ArrayList<>(),maskl = new ArrayList<>(),maskr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(c[i] == ')'){
                right.add(i);
                if (l > 0) {
                    l--;
                }else{
                    r++;
                }
            } else if (c[i] == '(') {
                left.add(i);
                l++;
            }
        }

        int size = 1 << left.size();
        if (l > 0) {
            for (int i = 1; i < size; i++) {
                if (Integer.bitCount(i) == l) {
                    maskl.add(i);
                }
            }
        }else
            maskl.add(0);
        size = 1 << right.size();
        if (r > 0) {
            for (int i = 1; i < size; i++) {
                if (Integer.bitCount(i) == r) {
                    maskr.add(i);
                }
            }
        }else
            maskr.add(0);

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        for (Integer lm : maskl) {
            for (Integer rm : maskr) {
                if (examine(lm, rm, left, right, c)) {
                    String restore = restore(lm, rm, left, right, c);
                    if (set.add(restore)) {
                        list.add(restore);
                    }
                }
            }
        }

        return list;
    }

    //检查当前删除的是否合法
    public boolean examine(int lm,int rm,List<Integer> left,List<Integer> right,char[] c){
        int res = 0,l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                if ((rm & (1<<r)) != 0 && i == right.get(r)) {
                    r++;
                    continue;
                }
                if (res == 0) {
                    return false;
                }
                r++;
                res--;
            } else if (c[i] == '(') {
                if ((lm & (1<<l)) != 0 && i == left.get(l)) {
                    l++;
                    continue;
                }
                res++;
                l++;
            }
        }

        return res == 0;
    }
    public String restore(int lm,int rm,List<Integer> left,List<Integer> right,char[] c){
        StringBuilder sb = new StringBuilder();
        int l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                if ((rm & (1<<r)) != 0 && i == right.get(r)) {
                    r++;
                    continue;
                }
                sb.append(c[i]);
                r++;
            } else if (c[i] == '(') {
                if ((lm & (1<<l)) != 0 && i == left.get(l)) {
                    l++;
                    continue;
                }
                sb.append(c[i]);
                l++;
            }else
                sb.append(c[i]);
        }
        return sb.toString();
    }*/

    //优化一下循环判断
    public List<String> removeInvalidParentheses(String s){
        char[] c = s.toCharArray();
        int l = 0,r = 0,n = c.length;
        List<Integer> left = new ArrayList<>(),right = new ArrayList<>(),maskl = new ArrayList<>(),maskr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(c[i] == ')'){
                right.add(i);
                if (l > 0) {
                    l--;
                }else{
                    r++;
                }
            } else if (c[i] == '(') {
                left.add(i);
                l++;
            }
        }

        int size = 1 << left.size();
        if (l > 0) {
            for (int i = 1; i < size; i++) {
                if (Integer.bitCount(i) == l) {
                    maskl.add(i);
                }
            }
        }else
            maskl.add(0);
        size = 1 << right.size();
        if (r > 0) {
            for (int i = 1; i < size; i++) {
                if (Integer.bitCount(i) == r) {
                    maskr.add(i);
                }
            }
        }else
            maskr.add(0);

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        for (Integer lm : maskl) {
            for (Integer rm : maskr) {
                String examine = examine(lm, rm, left, right, c);
                if (examine != null && set.add(examine)) {
                    list.add(examine);
                }
            }
        }

        return list;
    }

    //检查当前删除的是否合法
    /*public boolean examine(int lm,int rm,List<Integer> left,List<Integer> right,char[] c){
        int res = 0,l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                if ((rm & (1 << r)) == 0 ) {
                    if (res == 0) {
                        return false;
                    }
                    res--;
                }
                r++;
            } else if (c[i] == '(') {
                if ((lm & (1 << l)) == 0) {
                    res++;
                }
                l++;
            }
        }

        return res == 0;
    }
    public String restore(int lm,int rm,List<Integer> left,List<Integer> right,char[] c){
        StringBuilder sb = new StringBuilder();
        int l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                if ((rm & (1 << r)) == 0) {
                    sb.append(c[i]);
                }
                r++;
            } else if (c[i] == '(') {
                if ((lm & (1 << l)) == 0) {
                    sb.append(c[i]);
                }
                l++;
            } else
                sb.append(c[i]);
        }
        return sb.toString();
    }*/

    //合二为一之后发现效率并没有提高
    public String examine(int lm,int rm,List<Integer> left,List<Integer> right,char[] c){
        StringBuilder sb = new StringBuilder();
        int res = 0,l = 0,r = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                if ((rm & (1 << r)) == 0 ) {
                    if (res == 0) {
                        return null;
                    }
                    sb.append(c[i]);
                    res--;
                }
                r++;
            } else if (c[i] == '(') {
                if ((lm & (1 << l)) == 0) {
                    sb.append(c[i]);
                    res++;
                }
                l++;
            } else
                sb.append(c[i]);
        }
        if(res != 0)
            return null;
        return sb.toString();
    }
}

/*
class Node {
    String temp;
    int ls;
    int rs;
    int left;

    public Node(String temp, int ls, int rs, int left) {
        this.temp = temp;
        this.ls = ls;
        this.rs = rs;
        this.left = left;
    }
}*/
class Node {
    StringBuilder temp;
    int ls;
    int rs;
    int left;

    public Node(StringBuilder temp, int ls, int rs, int left) {
        this.temp = temp;
        this.ls = ls;
        this.rs = rs;
        this.left = left;
    }
}