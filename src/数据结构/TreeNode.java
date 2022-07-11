package 数据结构;

public class TreeNode{
    public TreeNode left;
    public TreeNode right;
    public int height;
    public int count;
    public int val;

    public TreeNode(int val){
        this(val,0,0,null,null);
    }
    public TreeNode(int val,int height,int count,TreeNode left,TreeNode right){
        this.val = val;
        this.height = height;
        this.count = count;
        this.left = left;
        this.right = right;
    }
}
