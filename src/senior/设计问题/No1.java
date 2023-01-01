package senior.设计问题;

import java.util.HashMap;
import java.util.Map;

public class No1 {
    /**
     * LRU缓存机制
     */

    //失败
    /*class LRUCache {
        private int limit, size;
        private Node[] set;

        public LRUCache(int capacity) {
            limit = capacity;
            set = new Node[23];
            size = 0;
        }

        public int get(int key) {
            if (size == 0)
                return -1;

            for (int i = 0; i < 23; i++) {
                Node node = set[i];
                while (node != null) {
                    //命中
                    if (key == node.key) {
                        update(node.count);
                        node.count = 0;
                        return node.val;
                    }
                    node = node.next;
                }
            }

            return -1;
        }

        public void put(int key, int value) {
            Node node1 = find(key, value);
            if (node1 != null) {
                update(node1.count);
                node1.count = 0;
                return;
            }
            if (size >= limit) {
                int max = Integer.MIN_VALUE, preLoc = -1;
                Node pre = null,node;
                for (int i = 0; i < 23; i++) {
                    node = set[i];
                    if (node != null) {
                        if (node.count > max) {
                            max = node.count;
                            preLoc = i;
                        }
                        while (node.next != null) {
                            if (node.next.count > max) {
                                pre = node;
                                max = node.next.count;
                                preLoc = -1;
                            }
                            node = node.next;
                        }
                    }
                }

                if (preLoc != -1) {
                    //找到的元素在数组上
                    if (set[preLoc].next == null) {
                        set[preLoc] = null;
                    }else
                        set[preLoc].next = set[preLoc].next.next;
                } else {
                    //找到的节点在数组的链表上
                    pre.next = pre.next.next;
                }
                size--;
            }
            int loc = key % 23;
            Node temp = new Node();
            temp.key = key;
            temp.val = value;
            Node node = set[loc];
            size++;

            //要插入的链表上没有元素
            if (node == null) {
                set[loc] = temp;
                node = set[loc];
            }else{
                while (node.next != null) {
                    node = node.next;
                }
                node.next = temp;
            }
            update(node.count);
            node.count = 0;
        }
        private Node find(int key,int val){
            for (int i = 0; i < 23; i++) {
                Node node = set[i];
                while (node != null) {
                    if (node.key == key) {
                        node.val = val;
                        return node;
                    }
                    node = node.next;
                }
            }
            return null;
        }
        private void update(int count) {
            for (int i = 0; i < 23; i++) {
                Node node = set[i];
                while (node != null) {
                    if (node.count <= count)
                        node.count++;
                    node = node.next;
                }
            }
        }
    }

    class Node {
        Node next;
        int key, val, count;
    }*/

    //仍然会超时
    /*class LRUCache {
        Map<Integer, Integer> map;
        MyList list;
        int limit, size;

        public LRUCache(int capacity) {
            limit = capacity;
            size = 0;
            map = new HashMap<>();
            list = new MyList();
        }

        public int get(int key) {
            if (size == 0) {
                return -1;
            }
            Integer val = map.get(key);
            list.updateSeq(key);
            return val == null ? -1 : val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.put(key, value);
                list.updateSeq(key);
                return;
            }
            if (size == limit) {
                //删除最近最久没使用的元素
                int dKey = list.delete();
                map.remove(dKey);
                size--;
            }
            map.put(key, value);
            list.add(key);
            size++;
        }
    }

    class MyList {
        Node front, tail;

        public MyList() {
            front = new Node();
            front.key = -1;
            tail = new Node();
            tail.key = -1;
            front.next = tail;
            tail.pre = front;
        }

        public void updateSeq(int key) {
            Node node = front.next, pre = null;
            while (node != null) {
                if (node.key == key) {
                    break;
                }
                pre = node;
                node = node.next;
            }
            //判断是否在队头
            if (pre != null && node != null) {
                pre.next = node.next;
                if(node.next != null)
                    node.next.pre = pre;

                node.pre = front;
                node.next = front.next;
                front.next.pre = node;
                front.next = node;
            }
        }

        public void add(int key) {
            Node node = new Node();
            node.key = key;
            front.next.pre = node;
            node.next = front.next;
            node.pre = front;
            front.next = node;
        }

        public int delete() {
            Node node = tail.pre;

            tail.pre = node.pre;
            node.pre.next = tail;
            node.pre = null;
            node.next = null;

            return node.key;
        }
    }*/

    class Node {
        Node next, pre;
        int key,val;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    //成功，但是时间为58ms左右，比官方题解慢
    //了很多，不知道问题出在哪里
    /*class LRUCache {
        Map<Integer, Node> map;
        MyList list;
        int limit, size;

        public LRUCache(int capacity) {
            limit = capacity;
            size = 0;
            map = new HashMap<>();
            list = new MyList();
        }

        public int get(int key) {
            Node node = map.get(key);
            if(node == null)
                return -1;
            list.updateSeq(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.val = value;
                list.updateSeq(node);
                return;
            }
            node = new Node(key,value);
            if (size == limit) {
                //删除最近最久没使用的元素
                int dKey = list.delete();
                map.remove(dKey);
                size--;
            }
            map.put(key, node);
            list.add(node);
            size++;
        }
    }

    class MyList {
        Node front, tail;

        public MyList() {
            front = new Node();
            front.key = -1;
            tail = new Node();
            tail.key = -1;
            front.next = tail;
            tail.pre = front;
        }

        public void updateSeq(Node node) {
            //把当前节点的后一个节点和前一个节点接上
            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.next = front.next;
            node.pre = front;
            front.next.pre = node;
            front.next = node;
        }

        public void add(Node node) {
            front.next.pre = node;
            node.next = front.next;
            node.pre = front;
            front.next = node;
        }

        public int delete() {
            Node node = tail.pre;

            tail.pre = node.pre;
            node.pre.next = tail;
            node.pre = null;
            node.next = null;

            return node.key;
        }
    }*/

    //这样就有43ms了
    class LRUCache {
        Map<Integer, Node> map;
        int limit, size;
        Node front, tail;

        public void updateSeq(Node node) {
            //把当前节点的后一个节点和前一个节点接上
            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.next = front.next;
            node.pre = front;
            front.next.pre = node;
            front.next = node;
        }

        public void add(Node node) {
            front.next.pre = node;
            node.next = front.next;
            node.pre = front;
            front.next = node;
        }

        public int delete() {
            Node node = tail.pre;

            tail.pre = node.pre;
            node.pre.next = tail;
            node.pre = null;
            node.next = null;

            return node.key;
        }

        public LRUCache(int capacity) {
            limit = capacity;
            size = 0;
            map = new HashMap<>();
            front = new Node();
            front.key = -1;
            tail = new Node();
            tail.key = -1;
            front.next = tail;
            tail.pre = front;
        }

        public int get(int key) {
            Node node = map.get(key);
            if(node == null)
                return -1;
            updateSeq(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.val = value;
                updateSeq(node);
                return;
            }
            node = new Node(key,value);
            if (size == limit) {
                //删除最近最久没使用的元素
                int dKey = delete();
                map.remove(dKey);
                size--;
            }
            map.put(key, node);
            add(node);
            size++;
        }
    }
}