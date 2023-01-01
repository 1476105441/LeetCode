package contest.year2022.m7.m7d24;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Rewrite {
    //t1


    //t2
    public int equalPairs(int[][] grid) {
        int n = grid.length,res = 0;
        Map<String,Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]).append(',');
            }
            String key = sb.toString();
            map.put(key,map.getOrDefault(key,0)+1);
        }

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[j][i]).append(',');
            }
            Integer temp = map.get(sb.toString());
            if(temp != null)
                res += temp;
        }

        return res;
    }


    //t3
    class FoodRatings {
        Map<String,Integer> locMap;
        Map<String, TreeSet<Integer>> map; //每种菜系存放一个最小堆
        String[] foods,cuisines;
        int[] ratings;
        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            locMap = new HashMap<>();
            map = new HashMap<>();
            this.foods = foods;
            this.cuisines = cuisines;
            this.ratings = ratings;
            for (int i = 0; i < foods.length; i++) {
                locMap.put(foods[i],i);
                TreeSet<Integer> tree = map.get(cuisines[i]);
                if (tree == null) {
                    tree = new TreeSet<>((x,y)->{
                        if(ratings[x] != ratings[y])
                            return ratings[y] - ratings[x];
                        return foods[x].compareTo(foods[y]);
                    });
                    map.put(cuisines[i],tree);
                }
                tree.add(i);
            }
        }

        /*public void changeRating(String food, int newRating) {
            int loc = locMap.get(food);
            ratings[loc] = newRating;
            TreeSet<Integer> treeSet = map.get(cuisines[loc]);
            treeSet.remove(loc);
            treeSet.add(loc);
        }*/

        //为什么这样子就行，上面的就不行
        public void changeRating(String food, int newRating) {
            int loc = locMap.get(food);
            TreeSet<Integer> treeSet = map.get(cuisines[loc]);
            treeSet.remove(loc);
            ratings[loc] = newRating;
            treeSet.add(loc);
        }

        public String highestRated(String cuisine) {
            return foods[map.get(cuisine).first()];
        }
    }


    //t4
    public long countExcellentPairs(int[] nums, int k) {
        int n = nums.length;
        long res = 0;

        return res;
    }
}
