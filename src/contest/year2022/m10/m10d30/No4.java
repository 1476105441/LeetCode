package contest.year2022.m10.m10d30;

public class No4 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //解法一：通过另外构造数据结构管理父子类关系，方便遍历
    /*class Node{
        Node parent,left,right;
        int height;
    }
    Node nodeRoot;
    public int[] treeQueries1(TreeNode root, int[] queries) {
        int m = queries.length;
        int[] res = new int[m];
        Node[] bucket = new Node[100001];
        nodeRoot = new Node();

        dfs1(root,null,bucket);
        //System.out.println(nodeRoot.height);

        for (int i = 0; i < m; i++) {
            res[i] = count(bucket[queries[i]]);
        }

        return res;
    }
    public Node dfs1(TreeNode now,Node parent,Node[] bucket){
        if(now == null)
            return null;
        Node node = new Node();
        node.left = dfs1(now.left,node,bucket);
        node.right = dfs1(now.right,node,bucket);
        if(parent == null)
            nodeRoot = node;
        else
            node.parent = parent;
        node.height = getHeight(node);
        bucket[now.val] = node;

        return node;
    }
    public int getHeight(Node node){
        int res = 0;
        if(node.left != null){
            res = node.left.height+1;
        }
        if (node.right != null) {
            res = Math.max(node.right.height+1,res);
        }
        return res;
    }*/
    //1600ms的计算
    /*public int count(Node node){
        int prec = -1;
        Node pre = node;
        Node now = node.parent;
        while (now != null) {
            int temp = -1;
            if(now.left != null && now.left != pre)
                temp = now.left.height;
            if(now.right != null && now.right != pre)
                temp = now.right.height;
            prec = Math.max(prec,temp)+1;
            pre = now;
            now = now.parent;
        }
        return prec;
    }*/
    //1200ms的计算
    /*public int count(Node node){
        int res = -1,prec = node.height;
        Node pre = node;
        Node now = node.parent;
        while (now != null) {
            int temp = -1;
            if(now.left != null && now.left != pre)
                temp = now.left.height;
            if(now.right != null && now.right != pre)
                temp = now.right.height;
            if (temp > prec) {
                res = nodeRoot.height;
                break;
            }
            res = Math.max(res,temp)+1;
            pre = now;
            prec = pre.height;
            now = now.parent;
        }
        return res;
    }*/


    //解法二：两次dfs，19ms
    // 第一次计算每个节点的长度，第二次计算删掉当前节点后的最大高度
    /*private static int[] map = new int[100001],temp = new int[100001];
    public int[] treeQueries(TreeNode root, int[] queries){
        int n = queries.length;
        int[] res = new int[n];
        height(root);

        dfs(root,-1,0);
        for (int i = 0; i < n; i++) {
            res[i] = temp[queries[i]];
        }

        return res;
    }
    //计算深度的方法
    public int height(TreeNode node){
        if(node == null)
            return 0;
        int h = Math.max(height(node.left),height(node.right)) + 1;
        //每个节点的值不同，所以直接使用值充当HashMap的key
        map[node.val] = h;

        return h;
    }
    //depth是到当前节点的深度，count是删除当前节点之后，树的最大深度
    public void dfs(TreeNode node,int depth,int count){
        if(node == null)
            return;
        //同map一样，使用节点的值来定位每个节点存储信息的位置
        temp[node.val] = count;
        depth++;
        int x1 = node.right == null ? 0 : map[node.right.val];
        int x2 = node.left == null ? 0 : map[node.left.val];
        //左子树节点删除后的最大深度，要么是经过父节点的右子树，要么是不经过父节点，走别的分支了
        dfs(node.left,depth,Math.max(count,depth+x1));
        //同左子树一样的道理
        dfs(node.right,depth,Math.max(count,depth+x2));
    }*/


    //解法三：dfs序，17ms
    //首先，dfs遍历，按照遍历顺序统计每个节点的深度
    /*static int m = 100002;
    //不能声明为static的，会报错，l用于存储一个元素所代表区间的左端点，r用于存储右端点，lm是计算区间0~i的最大值，rm是计算i~(n-1)区间的最大值
    private int[] depth = new int[m],l = new int[m],r = new int[m],lm = new int[m],rm = new int[m];
    int cnt;
    public int[] treeQueries(TreeNode root, int[] queries){
        cnt = 0;
        int n = queries.length;
        int[] res = new int[n];

        dfs(root,0);
        for (int i = 1; i <= cnt; i++) {
            lm[i] = Math.max(depth[i],lm[i-1]);
        }
        for (int i = cnt; i >= 0; i--) {
            rm[i] = Math.max(depth[i],rm[i+1]);
        }
        for (int i = 0; i < n; i++) {
            res[i] = Math.max(lm[l[queries[i]]-1],rm[r[queries[i]]+1]);
        }

        return res;
    }
    public void dfs(TreeNode node,int d){
        if(node == null)
            return;
        depth[++cnt] = d;
        //通过节点值来定位，因为后续使用时只知道要删除节点的值，而不知道要删除节点的序列号
        l[node.val] = cnt;
        dfs(node.left,d+1);
        dfs(node.right,d+1);
        r[node.val] = cnt;
    }*/


    //重新写一下解法三，dfs序，加深印象
    static int m = 100002;
    int[] depth = new int[m],l = new int[m],r = new int[m],lm = new int[m],rm = new int[m];
    int cnt;
    public int[] treeQueries(TreeNode root, int[] queries) {
        cnt = 0;
        int n = queries.length;
        int[] res = new int[n];
        dfs(root,0);

        for (int i = 1; i <= cnt; i++) {
            lm[i] = Math.max(lm[i-1],depth[i]);
        }
        for (int i = cnt; i > 0; i--) {
            rm[i] = Math.max(rm[i+1],depth[i]);
        }
        for (int i = 0; i < n; i++) {
            res[i] = Math.max(lm[l[queries[i]]-1],rm[r[queries[i]]+1]);
        }

        return res;
    }
    public void dfs(TreeNode node,int d){
        if(node == null)
            return;
        //使用序号存储当前节点的深度，序号代表当前节点
        depth[++cnt] = d;
        //使用节点值为索引，存储当前节点所管辖的范围
        l[node.val] = cnt;
        dfs(node.left,d+1);
        dfs(node.right,d+1);
        r[node.val] = cnt;
    }
}

