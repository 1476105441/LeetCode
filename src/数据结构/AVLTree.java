package 数据结构;

public class AVLTree {
    public TreeNode root;

    public AVLTree(int[] nums){
        root = init(nums,0,nums.length-1);
    }

    private TreeNode init(int[] nums,int left,int right){
        if (left > right) {
            return null;
        }
        int center = left + ((right-left)>>1);
        TreeNode node = new TreeNode(nums[center]);

        node.left = init(nums,left,center-1);
        node.right = init(nums,center+1,right);
        recompute(node);
        return node;
    }

    public void delete(int val){
        if (!remove(root, null,val)) {
            System.out.println("删除失败");
        }
    }
    public boolean remove(TreeNode node,TreeNode parent,int val){
        if (node == null) {
            return false;
        }
        boolean flag = false;
        if(node.val > val){
            flag = remove(node.left,node,val);
        } else if (node.val < val) {
            flag = remove(node.right,node,val);
        } else{
            if (node.left == null) {
                //判断是否为根节点，根节点特殊处理
                if (parent != null) {
                    if (node == parent.left) {
                        parent.left = node.right;
                    }else{
                        parent.right = node.right;
                    }
                }else{
                    root = node.right;
                }
                node = node.right;
                flag = true;
            } else if (node.right == null) {
                if (parent != null) {
                    if (node == parent.left) {
                        parent.left = node.left;
                    }else{
                        parent.right = node.left;
                    }
                }else{
                    root = node.left;
                }
                node = node.left;
                flag = true;
            } else{
                //统一找右子树中最左边的节点
                TreeNode r = findLeft(node.right);
                node.val = r.val;
                flag = remove(node.right,node,r.val);
            }
        }
        recompute(node);
        if (!isBalance(node)) {
            rotate(node,parent);
        }
        return flag;
    }

    //增加的方法
    public void insert(int val){
        if (root == null) {
            root = new TreeNode(val);
            recompute(root);
            return;
        }
        update(root,null,val);
    }

    //递归的寻找插入节点，插入后就进行自平衡调整
    //为了便于找到位置后进行插入操作，将父节点作为参数传入
    private void update(TreeNode node,TreeNode parent,int val){
        if (node == null) {
            node = new TreeNode(val);
            if (val > parent.val) {
                parent.right = node;
            }else{
                parent.left = node;
            }
        } else if (node.val == val) {
            //统一将新节点接到右子树上
            TreeNode treeNode = new TreeNode(val);
            treeNode.right = node.right;
            node.right = treeNode;
            recompute(treeNode);
        } else if (node.val > val) {
            update(node.left, node, val);
        } else {
            update(node.right, node, val);
        }
        recompute(node);
        if (!isBalance(node)) {
            rotate(node,parent);
        }
    }

    public void rotate(TreeNode node,TreeNode parent){
        //判断是需要左旋还是右旋，单旋还是双旋
        if (getHeight(node.left) > getHeight(node.right)) {
            if (getHeight(node.left) > getHeight(node.left.right)) {
                //右单旋
                rightRotate(node,parent);
            }else{
                //右双旋
                rightDRotate(node.left,node,parent);
            }
        }else{
            if (getHeight(node.right.right) > getHeight(node.right.left)) {
                //左单旋
                leftRotate(node,parent);
            }else{
                //左双旋
                leftDRotate(node.right,node,parent);
            }
        }
    }

    //右单旋
    public void rightRotate(TreeNode parent,TreeNode gParent){
        TreeNode child = parent.left;
        parent.left = child.right;
        child.right = parent;
        //根节点特殊处理
        if (gParent == null) {
            root = child;
        }else{
            boolean flag;
            flag = parent == gParent.left;
            if (flag) {
                gParent.left = child;
            }else{
                gParent.right = child;
            }
        }
        recompute(parent);
        recompute(child);
    }
    //右双旋
    public void rightDRotate(TreeNode node,TreeNode parent,TreeNode gParent){
        leftRotate(node,parent);
        rightRotate(parent,gParent);
    }
    //左单旋
    public void leftRotate(TreeNode parent,TreeNode gParent){
        TreeNode child = parent.right;
        if (gParent == null) {
            root = child;
            parent.right = child.left;
            child.left = parent;
        }else {
            boolean flag = gParent.right == parent;
            parent.right = child.left;
            child.left = parent;
            if (flag) {
                gParent.right = child;
            }else{
                gParent.left = child;
            }
        }
        recompute(parent);
        recompute(child);
    }
    //左双旋
    public void leftDRotate(TreeNode node,TreeNode parent,TreeNode gParent){
        rightRotate(node,parent);
        leftRotate(parent,gParent);
    }

    public TreeNode findLeft(TreeNode node){
        TreeNode pre = null;
        while (node != null) {
            pre = node;
            node = node.left;
        }
        return pre;
    }
    public TreeNode findRight(TreeNode node){
        TreeNode pre = null;
        while (node != null) {
            pre = node;
            node = node.right;
        }
        return pre;
    }

    public boolean isBalance(TreeNode node){
        if (node == null) {
            return true;
        }
        return Math.abs(getHeight(node.left) - getHeight(node.right)) <= 1;
    }
    private void recompute(TreeNode node){
        if (node == null) {
            return;
        }
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        node.count = getCount(node.left) + getCount(node.right) + 1;
    }
    private int getHeight(TreeNode node){
        return node == null ? 0 : node.height;
    }
    private int getCount(TreeNode node){
        return node == null ? 0 : node.count;
    }
}