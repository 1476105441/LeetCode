package contest.year2022.m10.m10d30;

import java.util.*;

public class No2 {
    class Node {
        String id;
        int view;
        long count;
    }
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        int n = creators.length;
        long max = 0;
        List<List<String>> res = new ArrayList<>();
        Map<String,Node> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            Node node = map.get(creators[i]);
            if (node == null) {
                node = new Node();
                node.id = ids[i];
                node.view = views[i];
                node.count = views[i];
                map.put(creators[i],node);
            }else{
                node.count += views[i];
                if (node.view < views[i]) {
                    node.id = ids[i];
                    node.view = views[i];
                } else if (node.view == views[i]) {
                    //如果当前id的字典序较小，则更换
                    if (compare(node.id, ids[i])) {
                        node.id = ids[i];
                        node.view = views[i];
                    }
                }
            }
            max = Math.max(max,node.count);
        }

        Iterator<Map.Entry<String, Node>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Node> next = it.next();
            if (next.getValue().count == max) {
                List<String> list = new ArrayList<>();
                list.add(next.getKey());
                list.add(next.getValue().id);
                res.add(list);
            }
        }

        return res;
    }
    //比较后一个字符串的字典序是否小于前一个
    public boolean compare(String s,String t){
        char[] sc = s.toCharArray(),tc = t.toCharArray();
        if(tc.length < sc.length)
            return true;
        else if(tc.length > sc.length)
            return false;

        for (int i = 0; i < sc.length; i++) {
            if(sc[i] > tc[i])
                return true;
        }
        return false;
    }
}
