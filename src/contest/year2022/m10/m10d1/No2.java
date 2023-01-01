package contest.year2022.m10.m10d1;

public class No2 {
    /**
     *      最长上传前缀
     */

    class LUPrefix {
        private int[] set;
        private int size;
        public LUPrefix(int n) {
            set = new int[n+1];
        }

        public void upload(int video) {
            set[video] = 1;
        }

        public int longest() {
            while(size < set.length-1 && set[size+1] == 1)
                size++;
            return size;
        }
    }
}
