package contest.year2023.m3.d18;

public class No4 {
    //能力值高的员工修理数量较少的汽车
    //最终时间取决于修理时间最久的员工
    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;
        long res = 0;
        long l = 0,r = 100000000000001L;
        while(l <= r){
            long c = l+((r-l)>>1);
            if(check(ranks,c,cars)){
                res = c;
                r = c-1;
            } else {
                l = c+1;
            }
        }
        return res;
    }
    private boolean check(int[] ranks,long val,long cars){
        int n = ranks.length;
        for(int i=0;i < n;i++){
            long t = val / ranks[i];
            long t2 = (long)Math.sqrt(t);
            cars -= t2;
        }
        return cars <= 0;
    }
}
