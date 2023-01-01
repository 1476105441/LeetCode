package contest.year2022.m10.m10d29;


public class No4 {
    //想用线段树的解法，似乎不太可行

    //超出内存限制
    /*Node root;
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int i = n-1;
        root = new Node();
        for (; i >= 0; i--) {
            Node node = query(root, 0, 100001, nums[i], nums[i]);
            res[i] = node.second;
            //System.out.println(node.first);
            if(nums[i] != 0)
                update(root,0,100001,0,nums[i]-1,nums[i]);
        }

        return res;
    }
    //想法：使用线段树，存储大于当前区间的第一个值和第二个值
    class Node{
        Node left,right;
        int first,second,add;
        public Node(){
            first = -1;
            second = -1;
        }
    }
    public void update(Node node, int start, int end, int l, int r,int val){
        if(start >= l && end <= r){
            node.second = node.first;
            node.first = val;
            node.add = val;
            return;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        if(c >= l) update(node.left,start,c,l,r,val);
        if(c < r) update(node.right,c+1,end,l,r,val);
    }
    public Node query(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            return node;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        Node res = null;
        if(c >= l) res = query(node.left,start,c,l,r);
        if(c < r) res = res == null ? query(node.right,c+1,end,l,r) : res;
        return res;
    }
    public void pushDown(Node node){
        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();
        if(node.left.add != 0)
            pushDown(node.left);
        if(node.right.add != 0)
            pushDown(node.right);
        if(node.add == 0) return;
        //更新最大值和第二大值
        //System.out.println(node.first);
        node.left.second = node.left.first;
        node.right.second = node.right.first;
        node.left.first = node.first;
        node.right.first = node.add;
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;
    }*/

    //出错了，线段树的解法还是有问题，解决不了这个问题
    /*Node root;
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int i = n-1;
        root = new Node();
        for (; i >= 0; i--) {
            Node node = query(root, 0, 100001, nums[i], nums[i]);
            res[i] = node.second;
            //System.out.println(node.first);
            if(nums[i] != 0)
                update(root,0,100001,0,nums[i]-1,nums[i]);
        }

        return res;
    }
    //想法：使用线段树，存储大于当前区间的第一个值和第二个值
    class Node{
        Node left,right;
        int first,second,add;
        public Node(){
            first = -1;
            second = -1;
        }
    }
    public void update(Node node, int start, int end, int l, int r,int val){
        if(start >= l && end <= r){
            node.second = node.first;
            node.first = val;
            node.add = 1;
            return;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        if(c >= l) update(node.left,start,c,l,r,val);
        if(c < r) update(node.right,c+1,end,l,r,val);
    }
    public Node query(Node node, int start, int end, int l, int r){
        if(start >= l && end <= r){
            return node;
        }
        pushDown(node);
        int c = start+((end-start)>>1);
        Node res = null;
        if(c >= l) res = query(node.left,start,c,l,r);
        if(c < r) res = res == null ? query(node.right,c+1,end,l,r) : res;
        return res;
    }
    public void pushDown(Node node){
        if(node.left == null) node.left = new Node();
        if(node.right == null) node.right = new Node();
        if(node.add == 0) return;
        //更新最大值和第二大值
        if (node.add > 1) {
            node.left.second = node.second;
            node.right.second = node.second;
        }else{
            node.left.second = node.left.first;
            node.right.second = node.right.first;
        }
        node.left.first = node.first;
        node.right.first = node.first;
        node.left.add++;
        node.right.add++;
        node.add = 0;
    }*/


    //晚点再研究一下其他解法
    //两个单调栈解法，大佬的思维太牛逼了，135ms
    //使用两个递减单调栈，当前元素比第一个栈顶元素大时，将栈顶
    //元素转移到第二个栈，不过第二个栈也要保持单调栈
    /*public int[] secondGreaterElement(int[] nums){
        int n = nums.length;
        int[] res = new int[n],buf = new int[n];
        Stack<Integer> stack1 = new Stack<>(),stack2 = new Stack<>();

        for (int i = 0; i < n; i++) {
            res[i] = -1;
            int l = 0;
            while (!stack2.isEmpty() && nums[i] > nums[stack2.peek()]) {
                res[stack2.pop()] = nums[i];
            }
            while (!stack1.isEmpty() && nums[i] > nums[stack1.peek()]) {
                buf[l++] = stack1.pop();
            }
            stack1.push(i);
            l--;
            while (l > -1) {
                stack2.push(buf[l]);
                l--;
            }
        }

        return res;
    }*/

    //用数组模拟栈
    //6ms
    /*public int[] secondGreaterElement(int[] nums){
        int n = nums.length,l1 = 0,l2 = 0,b;
        int[] res = new int[n],s1 = new int[n],s2 = new int[n],buf = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = -1;
            b = 0;
            while (l2 > 0 && nums[i] > nums[s2[l2 - 1]]) {
                res[s2[l2 - 1]] = nums[i];
                l2--;
            }
            while (l1 > 0 && nums[i] > nums[s1[l1 - 1]]) {
                buf[b++] = s1[l1-1];
                l1--;
            }
            s1[l1++] = i;
            while (b > 0) {
                s2[l2++] = buf[b-1];
                b--;
            }
        }

        return res;
    }*/


    //将栈声明为全局变量，内存占用减少了一些，多次测量，可能减少了1-2mb
    static int[] s1 = new int[100000],s2 = new int[100000],buf = new int[100000];
    public int[] secondGreaterElement1(int[] nums){
        int n = nums.length,l1 = 0,l2 = 0,b;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = -1;
            b = 0;
            while (l2 > 0 && nums[i] > nums[s2[l2 - 1]]) {
                res[s2[l2 - 1]] = nums[i];
                l2--;
            }
            while (l1 > 0 && nums[i] > nums[s1[l1 - 1]]) {
                buf[b++] = s1[l1-1];
                l1--;
            }
            s1[l1++] = i;
            while (b > 0) {
                s2[l2++] = buf[b-1];
                b--;
            }
        }

        return res;
    }
}
