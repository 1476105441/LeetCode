package senior.设计问题;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class No3 {
    /**
     * 扁平化嵌套列表迭代器
     */

    //4ms，才击败了百分之十，被打爆了
    /*public class NestedIterator implements Iterator<Integer> {
        List<Integer> list;
        int loc;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = new ArrayList<>(10000);
            initList(nestedList);
            loc = 0;
        }

        @Override
        public Integer next() {
            Integer res = list.get(loc);
            loc++;
            return res;
        }

        @Override
        public boolean hasNext() {
            return loc < list.size();
        }

        private void initList(List<NestedInteger> nestedList){
            for (int i = 0; i < nestedList.size(); i++) {
                NestedInteger nestedInteger = nestedList.get(i);
                if(nestedInteger.isInteger())
                    list.add(nestedInteger.getInteger());
                else
                    initList(nestedInteger.getList());
            }
        }
    }*/

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {
        Stack<Node> stack;
        List<NestedInteger> current;
        int loc;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            current = nestedList;
            loc = 0;
        }

        @Override
        public Integer next() {
            NestedInteger nestedInteger = current.get(loc);
            loc++;
            return nestedInteger.getInteger();
        }

        @Override
        public boolean hasNext() {
            update();
            if(loc == current.size())
                return false;
            NestedInteger nestedInteger = current.get(loc);
            while(!nestedInteger.isInteger()){
                loc++;
                Node node = new Node(current,loc);
                stack.push(node);
                current = nestedInteger.getList();
                loc = 0;
                if(current.size() == 0) {
                    update();
                    if(loc == current.size())
                        return false;
                }
                nestedInteger = current.get(loc);
            }
            return loc < current.size() || !stack.isEmpty();
        }

        private void update(){
            while(loc == current.size() && !stack.isEmpty()){
                Node node = stack.pop();
                current = node.list;
                loc = node.loc;
            }
        }
    }
    class Node{
        List<NestedInteger> list;
        int loc;

        public Node(List<NestedInteger> list, int loc) {
            this.list = list;
            this.loc = loc;
        }
    }
}
