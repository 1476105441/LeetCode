package 周赛.m7d24;

import java.util.*;

public class No3 {
    /**
     *      设计食物评分系统
     */

    class FoodRatings {
        Map<String,Node> name;
        Map<String,TreeSet<Node>> cu;
        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            name = new HashMap<>();
            cu = new HashMap<>();

            for (int i = 0; i < foods.length; i++) {
                Node node = new Node(foods[i],cuisines[i],ratings[i]);
                name.put(foods[i],node);
                TreeSet<Node> nodes = cu.get(cuisines[i]);
                if (nodes == null) {
                    nodes = new TreeSet<>();
                    cu.put(cuisines[i],nodes);
                }
                nodes.add(node);
            }
        }

        public void changeRating(String food, int newRating) {
            Node node = name.get(food);
            TreeSet<Node> nodes = cu.get(node.cuisine);
            nodes.remove(node);
            node.score = newRating;
            nodes.add(node);
        }

        public String highestRated(String cuisine) {
            TreeSet<Node> nodes = cu.get(cuisine);
            return nodes.first().name;
        }
    }
    class Node implements Comparable<Node>{
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return score == node.score && name.equals(node.name) && cuisine.equals(node.cuisine);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, cuisine, score);
        }

        public Node(String name, String cuisine, int score) {
            this.name = name;
            this.cuisine = cuisine;
            this.score = score;
        }

        String name;
        String cuisine;
        int score;

        @Override
        public int compareTo(Node o) {
            if (this.score != o.score) {
                return o.score - this.score;
            }else
                return this.name.compareTo(o.name);
        }
    }
}
