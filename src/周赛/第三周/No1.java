package 周赛.第三周;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class No1 {

    //使用哈希表
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();

        map.put("January",1);
        map.put("February",2);
        map.put("March",3);
        map.put("April",4);
        map.put("May",5);
        map.put("June",6);
        map.put("July",7);
        map.put("August",8);
        map.put("September",9);
        map.put("October",10);
        map.put("November",11);
        map.put("December",12);

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String input = scanner.next();

        int num = scanner.nextInt(),count = map.get(input);

        count += num;

        if (count % 12 == 0) {
            count = 12;
        }else{
            count = count % 12;
        }

        Set<Map.Entry<String,Integer>> entries = map.entrySet();
        for (Map.Entry<String,Integer> node : entries){
            if (node.getValue() == count) {
                System.out.println(node.getKey());
                break;
            }
        }
    }
}
