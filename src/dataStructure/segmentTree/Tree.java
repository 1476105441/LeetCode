package dataStructure.segmentTree;

public class Tree {
    Tree left,right;
    int val,add;
}
class Manager{
    Tree root;

    public void update(Tree node,int s,int e,int l,int r,int val){
        if (s >= l && e <= r) {
            node.val = val;
            node.add = val;
            return;
        }
        pushDown(node);
        int c = s + (e-s)/2;
        if(c >= l) update(node.left,s,c,l,r,val);
        if(c < r) update(node.right,c+1,e,l,r,val);
    }
    public void pushDown(Tree node){
        if(node.left == null)
            node.left = new Tree();
        if(node.right == null)
            node.right = new Tree();
        if(node.val == 0)
            return;

        node.left.val = node.add;
        node.left.add = node.add;
        node.right.val = node.add;
        node.right.add = node.add;
        node.add = 0;
    }
}
