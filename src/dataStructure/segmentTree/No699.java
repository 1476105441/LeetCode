package dataStructure.segmentTree;

import java.util.ArrayList;
import java.util.List;

public class No699 {
    //失败，无法更新到其他应该更新的节点
    /*Node root;

    public List<Integer> fallingSquares(int[][] positions) {
        root = new Node();
        int start = 0,end = 1001000000;
        List<Integer> list = new ArrayList<>();
        for (int[] ints : positions) {
            int x = ints[0],len = ints[1];
            update(root,start,end,x+1,x+len,len);
            list.add(root.val);
        }
        return list;
    }

    class Node {
        Node left,right;
        int val,add;
    }
    private void update(Node node,int left,int right,int start,int end,int val){
        //边界重合时不管
        if (start <= left && right <= end) {
            node.val += val;
            node.add = node.val;
            return;
        }
        pushDown(node);
        int c = left + ((right - left) >> 1);
        if (c >= start) {
            update(node.left,left,c,start,end,val);
        }
        if (c < end) {
            update(node.right,c+1,right,start,end,val);
        }
        node.val = Math.max(node.left.val,node.right.val);
    }
    private void pushDown(Node node){
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.add == 0) {
            return;
        }
        node.left.val = node.left.add = node.add;
        node.right.val = node.right.add = node.add;
        node.add = 0;
    }*/

    //直接覆盖掉就行了，死脑筋了
    Node root;

    public List<Integer> fallingSquares(int[][] positions) {
        root = new Node();
        int start = 0,end = 1001000000;
        List<Integer> list = new ArrayList<>();
        for (int[] ints : positions) {
            int x = ints[0],len = ints[1];
            //先查出来
            int query = query(root, start, end, x+1, x + len);
            //更新时进行覆盖，加上之前查出来的大小
            update(root,start,end,x+1,x+len,len + query);
            list.add(root.val);
        }
        return list;
    }

    class Node {
        Node left,right;
        int val,add;
    }
    private void update(Node node,int left,int right,int start,int end,int val){
        //边界重合时不管
        if (start <= left && right <= end) {
            node.val = val;
            node.add = node.val;
            return;
        }
        pushDown(node);
        int c = left + ((right - left) >> 1);
        if (c >= start) {
            update(node.left,left,c,start,end,val);
        }
        if (c < end) {
            update(node.right,c+1,right,start,end,val);
        }
        node.val = Math.max(node.left.val,node.right.val);
    }
    private int query(Node node,int left,int right,int start,int end){
        if (start <= left && right <= end) {
            return node.val;
        }
        pushDown(node);
        int c = left + ((right - left) >> 1),res = 0;
        if (c >= start) {
            res = query(node.left,left,c,start,end);
        }
        if (c < end) {
            res = Math.max(res,query(node.right,c+1,right,start,end));
        }
        return res;
    }
    private void pushDown(Node node){
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.add == 0) {
            return;
        }
        node.left.val = node.left.add = node.add;
        node.right.val = node.right.add = node.add;
        node.add = 0;
    }

    public static void main(String[] args) {
        No699 no699 = new No699();
        List<Integer> list = no699.fallingSquares(new int[][]{new int[]{9, 7}, new int[]{1, 9}, new int[]{3, 1}});
        list.forEach(System.out::println);
    }
}
