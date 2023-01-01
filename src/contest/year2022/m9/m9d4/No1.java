package contest.year2022.m9.m9d4;

public class No1 {
    /**
     *      检查相同字母间的距离
     */

    public static boolean checkDistances(String s, int[] distance) {
        int[] set = new int[26];
        char[] cs = s.toCharArray();
        for (int i = 0; i < 26; i++) {
            set[i] = -1;
        }

        for (int i = 0; i < cs.length; i++) {
            int loc = cs[i] - 'a';
            if(set[loc] != -1){
                if(distance[loc] != i - set[i]-1){
                    System.out.println(distance[loc]);
                    System.out.println(i-set[loc]-1);
                    return false;
                }
            }else
                set[loc] = i;
        }
        return true;
    }
}
