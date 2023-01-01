package contest.year2022.oj.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No3 {
    /*public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s1 = reader.readLine().split(" ");
        String[] s2 = reader.readLine().split(" ");

        Map<Integer,Integer> map = new HashMap<>(),map1 = new HashMap<>(),map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int temp1 = Integer.parseInt(s1[i]),temp2 = Integer.parseInt(s2[i]);
            map1.put(temp1,map1.getOrDefault(temp1,0)+1);
            map2.put(temp2,map2.getOrDefault(temp2,0)+1);
        }

        Iterator<Map.Entry<Integer, Integer>> it1 = map1.entrySet().iterator(),it2;
        while (it1.hasNext()) {
            Map.Entry<Integer, Integer> node1 = it1.next(),node2;
            it2 = map2.entrySet().iterator();
            while (it2.hasNext()) {
                node2 = it2.next();
                int temp = node1.getKey() + node2.getKey();
                map.put(temp,map.getOrDefault(temp,0)+node1.getValue()* node2.getValue());
            }
        }

        int res = 0;
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = it.next();
            if((next.getValue() & 1) == 1)
                res ^= next.getKey();
        }

        System.out.println(res);
    }*/

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s1 = reader.readLine().split(" ");
        String[] s2 = reader.readLine().split(" ");


    }
}
