package contest.year2022.m8.m8d21;

public class No3 {
    /**
     * 感染二叉树需要的总时间
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //建立完全二叉树数组，然后再计算，超出内存限制
    /*TreeNode target;
    int val;
    public int amountOfTime(TreeNode root, int start) {
        int res,i = -1,c = 1;
        boolean flag = true;
        List<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        list.add(root);
        while (!queue.isEmpty() && flag) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                i++;
                if (node != null) {
                    if (node.val == start) {
                        flag = false;
                        break;
                    }
                    list.add(node.left);
                    queue.offer(node.left);

                    list.add(node.right);
                    queue.offer(node.right);
                }else{
                    list.add(null);
                    list.add(null);
                    queue.offer(null);
                    queue.offer(null);
                }

                size--;
            }
        }

        target = list.get(i);
        res = Math.max(find(target.left),find(target.right));
        for (; i > 0;) {
            int j = (i-1) >> 1;
            find(list.get(j));
            int temp = val;
            if(temp + c > res)
                res = temp + c;
            c++;
            i = j;
        }

        return res;
    }
    public int find(TreeNode node){
        if(node == null)
            return 0;
        if (node == target) {
            return -1;
        }

        int l,r;
        l = find(node.left);
        r = find(node.right);
        val = Math.max(l,r);
        if (l == -1 || r == -1) {
            return -1;
        }

        return val+1;
    }*/

    //建立图模型，187ms，太慢了
    /*Map<TreeNode,List<TreeNode>> map;
    TreeNode target;
    int t;
    public int amountOfTime(TreeNode root, int start){
        int res = -1;
        t = start;
        map = new HashMap<>();
        dfs(root,null);

        Set<Integer> set = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        set.add(start);
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                List<TreeNode> list = map.get(node);
                for (TreeNode treeNode : list) {
                    //要做记忆化搜索
                    if (set.add(treeNode.val)) {
                        queue.offer(treeNode);
                    }
                }
                size--;
            }
        }

        return res;
    }
    public void dfs(TreeNode node,TreeNode parent){
        if(node.val == t){
            target = node;
        }

        List<TreeNode> list = new ArrayList<>();

        if(parent != null)
            list.add(parent);
        if (node.left != null) {
            list.add(node.left);
            dfs(node.left,node);
        }
        if (node.right != null) {
            list.add(node.right);
            dfs(node.right,node);
        }
        map.put(node,list);
    }*/

    //记录父节点和目标节点之间的距离，然后dfs目标节点、父节点，计算最大长度
    //40-50ms，击败百分之70
    /*Map<TreeNode,Integer> map;
    TreeNode target;
    int c;
    public int amountOfTime(TreeNode root, int start){
        int res = 0;
        map = new HashMap<>();

        find(root,start);
        map.put(target,0);
        res = dfs(target) - 1;

        Set<TreeNode> set = map.keySet();
        for (TreeNode node : set) {
            int t = dfs(node) - 1 + map.get(node);
            if(t > res)
                res = t;
        }

        return res;
    }
    public void find(TreeNode node,int start){
        if(node == null)
            return;

        if(node.val == start){
            target = node;
            c++;
            return;
        }

        find(node.left,start);

        if (target != null) {
            map.put(node,c);
            c++;
            return;
        }

        find(node.right,start);
        if (target != null) {
            map.put(node,c);
            c++;
        }
    }
    public int dfs(TreeNode node){
        if(node == null)
            return 0;

        int l = 0,r = 0;
        if(!map.containsKey(node.left))
            l = dfs(node.left);
        if(!map.containsKey(node.right))
            r = dfs(node.right);

        return Math.max(l,r) + 1;
    }*/

    //上一解法的改进版，11ms，已达最优解
    /*boolean flag;
    int res,c;
    public int amountOfTime(TreeNode root, int start){
        find(root,start);
        return res;
    }

    public void find(TreeNode node,int start){
        if (node == null)
            return;

        if (node.val == start) {
            flag = true;
            res = dfs(node)-1;
            c++;
            return;
        }

        find(node.left,start);
        if (flag) {
            res = Math.max(dfs(node.right)+c,res);
            c++;
            return;
        }
        find(node.right,start);
        if (flag) {
            res = Math.max(dfs(node.left)+c,res);
            c++;
        }
    }
    public int dfs(TreeNode node){
        if(node == null)
            return 0;
        int l,r;
        l = dfs(node.left);
        r = dfs(node.right);

        return Math.max(l,r) + 1;
    }*/

    //大神解法，观摩一下，相当的简洁明了
    /*int ans = 0;    // 最短用时
    int depth = -1; // 起始节点的高度

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, 0, start);
        return ans;
    }

    int dfs(TreeNode root, int level, int start) {
        if (root == null) return 0;
        if (root.val == start) depth = level;                       // 当前节点即起始节点
        int l = dfs(root.left, level + 1, start);                   // 左子树的树高
        boolean inLeft = depth != -1;                               // 起始节点是否在左子树上
        int r = dfs(root.right, level + 1, start);                  // 右子树的树高
        if (root.val == start) ans = Math.max(ans, Math.max(l, r)); // 情况1：感染以 start 为根结点的树所需时间
        if (inLeft) ans = Math.max(ans, depth - level + r);         // 情况2：感染以 root 为根结点的树所需时间
        else ans = Math.max(ans, depth - level + l);
        return Math.max(l, r) + 1;                                  // 返回树高
    }*/

    //根据大神的写一个自己的版本
    int res,depth = -1;
    public int amountOfTime(TreeNode root, int start){
        find(root,0,start);
        return res;
    }
    public int find(TreeNode node,int d,int start){
        if(node == null)
            return 0;

        if(node.val == start)
            depth = d;
        int r,l;
        l = find(node.left,d+1,start);
        boolean flag = depth != -1;
        r = find(node.right,d+1,start);

        if(node.val == start)
            res = Math.max(l,r);
        else if(flag)
            res = Math.max(res,depth - d + r);
        else
            res = Math.max(res,depth - d + l);

        return Math.max(l,r) + 1;
    }
}
