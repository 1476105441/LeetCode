package contest.year2022.team;

public class No1 {
    public int minNumBooths(String[] demand){
        int[] nums = new int[26];
        int res = 0;
        char[][] c = new char[demand.length][];
        for (int i = 0; i < c.length; i++) {
            c[i] = demand[i].toCharArray();
        }

        for (int i = 0; i < c.length; i++) {
            int[] temp = new int[26];
            for (int j = 0; j < c[i].length; j++) {
                int loc = c[i][j] - 'a';
                temp[loc]++;
            }
            for (int j = 0; j < 26; j++) {
                nums[i] = Math.max(nums[i],temp[i]);
            }
        }

        for (int i = 0; i < 26; i++) {
            res += nums[i];
        }

        return res;
    }
}
