package contest.year2022.autumn;

public class No4 {
    /**
     * 二叉树灯饰
     */

    //动态规划：把节点值修改，失败，错误理解了其中一种操作
    /*public int closeLampInTree(TreeNode root) {
        int[] res = dfs(root);
        return res[0];
    }

    public int[] dfs(TreeNode node){
        if(node == null)
            return null;
        int[] left= dfs(node.left),right= dfs(node.right),res = new int[2];

        if (left != null && right != null) {
            if (node.val == 0) {
                res[0] = left[0]+right[0];
                res[0] = Math.min(res[0],left[1]+right[1]);
                res[1] = 1+left[1]+right[1];
                res[1] = Math.min(res[1],1+left[0]+right[0]);
            }else{
                res[0] = 1+left[0]+right[0];
                res[0] = Math.min(res[0],1+left[1]+right[1]);
                res[1] = left[1]+right[1];
                res[1] = Math.min(res[1],1+left[0]+right[0]);
            }
        } else if (left != null) {
            if (node.val == 0) {
                res[0] = left[0];
                res[0] = Math.min(res[0],1+left[1]);
                res[1] = 1+left[1];
                res[1] = Math.min(res[1],1+left[0]);
            }else{
                res[0] = 1+left[0];
                res[0] = Math.min(res[0],1+left[1]);
                res[1] = left[1];
                res[1] = Math.min(res[1],1+left[0]);
            }
        } else if (right != null) {
            if (node.val == 0) {
                res[0] = right[0];
                res[0] = Math.min(res[0],1+right[1]);
                res[1] = 1+right[1];
                res[1] = Math.min(res[1],1+right[0]);
            }else{
                res[0] = 1+right[0];
                res[0] = Math.min(res[0],1+right[1]);
                res[1] = right[1];
                res[1] = Math.min(res[1],1+right[0]);
            }
        }else{
            if (node.val == 0) {
                res[1] = 1;
            }else{
                res[0] = 1;
            }
        }
        return res;
    }*/

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //任意一个节点
    //会受到的影响
    //其祖先节点开关2 的切换次数 偶数 为 不切换 奇数为切换
    //其父节点是否是否切换了开关3
    /*HashMap<TreeNode, int[][]> map;

    public int closeLampInTree(TreeNode root) {

        map = new HashMap<>();
        return dfs(root, false, false);
    }


    public int dfs(TreeNode node, boolean switch2Stat, boolean switch3Stat){

        if(node == null) return 0;

        int x = switch2Stat ? 1 : 0;
        int y = switch3Stat ? 1 : 0;
        int[][] val = new int[2][2];
        if(map.containsKey(node)){

            val = map.get(node);

            if(val[x][y] > 0) return val[x][y];
        }else{

            map.put(node, val);
        }

        int result = 0;
        //灯 开 的情况 , 对于开关 2 和 开关 3 可以相互抵消 , 最终还是 开
        //灯 关 的情况 , 对于开关 2 和 开关 3 可以无法抵消 , 最终还是 开

        if((node.val == 1) == (switch2Stat == switch3Stat)){
            //枚举打开一个开关 或者打开三个开关的情况
            result = dfs(node.left, switch2Stat, false) +
                    dfs(node.right, switch2Stat, false) + 1;

            int res2 = dfs(node.left, !switch2Stat, false) +
                    dfs(node.right, !switch2Stat, false) + 1;
            result = Math.min(res2, result);

            int res3 = dfs(node.left, switch2Stat, true) +
                    dfs(node.right, switch2Stat, true) + 1;
            result = Math.min(res3, result);

            int res123 = dfs(node.left, !switch2Stat, true) +
                    dfs(node.right, !switch2Stat, true) + 3;
            result = Math.min(result, res123);

            val[x][y] = result;
        }else{
            //枚举一个都不开 或 打开两个开关
            result = dfs(node.left, switch2Stat, false) +
                    dfs(node.right, switch2Stat, false);

            int res12 = dfs(node.left, !switch2Stat, false) +
                    dfs(node.right, !switch2Stat, false) + 2;
            result = Math.min(res12, result);

            int res13 = dfs(node.left, switch2Stat, true) +
                    dfs(node.right, switch2Stat, true) + 2;
            result = Math.min(result, res13);

            int res23 = dfs(node.left, !switch2Stat, true) +
                    dfs(node.right, !switch2Stat, true) + 2;
            result = Math.min(result, res23);

            val[x][y] = result;
        }
        return  val[x][y];
    }*/


    //四种状态：全部为1，全部为0，只有根为1，只有根为0
    /*class Info {
        int allClose;// close all
        int allOpen;// open all
        int rootOpen;//
        int rootClose;
    }

    public int closeLampInTree(TreeNode root) {
        Info ans = this.f(root);
        return ans.allClose;
    }

    private Info f(TreeNode root) {
        if (root == null) {
            return new Info();
        }
        Info ans = new Info();
        Info left = f(root.left);
        Info right = f(root.right);
        ans.allClose = closeAll(root.val, left, right);
        ans.allOpen = openAll(root.val, left, right);
        ans.rootOpen = rootOpen(root.val, left, right);
        ans.rootClose = rootClose(root.val, left, right);
        return ans;
    }

    //只有根是关闭的
    private int rootClose(int val, Info left, Info right) {
        int ans1;
        int ans2;
        int ans3;
        int ans4;
        if (val == 1) {// 当前是开启v
            ans1 = left.allOpen + right.allOpen + 1;
            ans2 = left.allClose + right.allClose + 1;
            ans3 = left.rootOpen + right.rootOpen + 3;
            ans4 = left.rootClose + right.rootClose + 1;
        } else {// 当前是关闭
            ans1 = left.allOpen + right.allOpen;
            ans2 = left.allClose + right.allClose + 2;
            ans3 = left.rootOpen + right.rootOpen + 2;
            ans4 = left.rootClose + right.rootClose + 2;
        }
        return Math.min(Math.min(ans4, ans3), Math.min(ans1, ans2));
    }

    //只有根是打开的
    private int rootOpen(int status, Info left, Info right) {
        int ans1;
        int ans2;
        int ans3;
        int ans4;
        if (status == 1) {// 当前是开启
            ans1 = left.allOpen + right.allOpen + 2;
            ans2 = left.allClose + right.allClose;
            ans3 = left.rootOpen + right.rootOpen + 2;
            ans4 = left.rootClose + right.rootClose + 2;
        } else {// 当前是关闭x
            ans1 = left.allOpen + right.allOpen + 1;
            ans2 = left.allClose + right.allClose + 1;
            ans3 = left.rootOpen + right.rootOpen + 1;
            ans4 = left.rootClose + right.rootClose + 3;
        }
        return Math.min(Math.min(ans4, ans3), Math.min(ans1, ans2));
    }

    //打开全部
    private int openAll(int status, Info left, Info right) {
        int ans1;
        int ans2;
        int ans3;
        int ans4;
        if (status == 1) {// 当前是开启
            ans1 = left.allOpen + right.allOpen;
            ans2 = left.allClose + right.allClose + 2;
            ans3 = left.rootOpen + right.rootOpen + 2;
            ans4 = left.rootClose + right.rootClose + 2;
        } else {// 当前是关闭x
            ans1 = left.allOpen + right.allOpen + 1;
            ans2 = left.allClose + right.allClose + 1;
            ans3 = left.rootOpen + right.rootOpen + 3;
            ans4 = left.rootClose + right.rootClose + 1;
        }
        return Math.min(Math.min(ans4, ans3), Math.min(ans1, ans2));
    }

    //关闭全部
    private int closeAll(int status, Info left, Info right) {
        int ans1;
        int ans2;
        int ans3;
        int ans4;
        if (status == 1) {// 当前是开启 v
            ans1 = left.allOpen + right.allOpen + 1;
            ans2 = left.allClose + right.allClose + 1;
            ans3 = left.rootOpen + right.rootOpen + 1;
            ans4 = left.rootClose + right.rootClose + 3;
        } else {// 当前是关闭 x
            ans1 = left.allOpen + right.allOpen + 2;
            ans2 = left.allClose + right.allClose;
            ans3 = left.rootOpen + right.rootOpen + 2;
            ans4 = left.rootClose + right.rootClose + 2;
        }
        return Math.min(Math.min(ans4, ans3), Math.min(ans1, ans2));
    }*/

    class Info{
        int closeAll;
        int openAll;
        int rootClose;
        int rootOpen;
    }

    //状态转移太复杂
    public int closeLampInTree(TreeNode root){
        return dfs(root).closeAll;
    }
    public Info dfs(TreeNode node){
        if(node == null)
            return new Info();
        Info now = new Info(),left = dfs(node.left),right = dfs(node.right);
        now.openAll = openAll(node.val,left,right);
        now.closeAll = closeAll(node.val,left,right);
        now.rootOpen = rootOpen(node.val,left,right);
        now.rootClose = rootClose(node.val,left,right);
        return now;
    }

    //open == 1，close == 0
    public int closeAll(int val,Info l,Info r){
        int t1,t2,t3,t4;
        if (val == 1) {
            t1 = l.closeAll+r.closeAll+1;
            t2 = l.openAll+r.openAll+1;
            t3 = l.rootOpen+r.rootOpen+1;
            t4 = l.rootClose+r.rootClose+3;
        }else{
            t1 = l.closeAll+r.closeAll;
            t2 = l.openAll+r.openAll+2;
            t3 = l.rootClose+r.rootClose+2;
            t4 = l.rootOpen+r.rootOpen+2;
        }
        return Math.min(Math.min(t1,t2),Math.min(t3,t4));
    }

    public int openAll(int val, Info l, Info r) {
        int t1,t2,t3,t4;
        if (val == 1) {
            t1 = l.closeAll+r.closeAll+2;
            t2 = l.openAll+r.openAll;
            t3 = l.rootOpen+r.rootOpen+2;
            t4 = l.rootClose+r.rootClose+2;
        }else{
            t1 = l.closeAll+r.closeAll+1;
            t2 = l.openAll+r.openAll+1;
            t3 = l.rootClose+r.rootClose+1;
            t4 = l.rootOpen+r.rootOpen+3;
        }
        return Math.min(Math.min(t1,t2),Math.min(t3,t4));
    }

    public int rootClose(int val, Info l, Info r) {
        int t1,t2,t3,t4;
        if (val == 1) {
            t1 = l.closeAll+r.closeAll+1;
            t2 = l.openAll+r.openAll+1;
            t3 = l.rootOpen+r.rootOpen+3;
            t4 = l.rootClose+r.rootClose+1;
        }else{
            t1 = l.closeAll+r.closeAll+2;
            t2 = l.openAll+r.openAll;
            t3 = l.rootClose+r.rootClose+2;
            t4 = l.rootOpen+r.rootOpen+2;
        }
        return Math.min(Math.min(t1,t2),Math.min(t3,t4));
    }

    public int rootOpen(int val, Info l, Info r) {
        int t1,t2,t3,t4;
        if (val == 1) {
            t1 = l.closeAll+r.closeAll;
            t2 = l.openAll+r.openAll+2;
            t3 = l.rootOpen+r.rootOpen+2;
            t4 = l.rootClose+r.rootClose+2;
        }else{
            t1 = l.closeAll+r.closeAll+1;
            t2 = l.openAll+r.openAll+1;
            t3 = l.rootClose+r.rootClose+3;
            t4 = l.rootOpen+r.rootOpen+1;
        }
        return Math.min(Math.min(t1,t2),Math.min(t3,t4));
    }
}
