package 复习.搜索和排序;

public class No2 {
    /**
     *      第一个错误的版本
     */

    /*public int firstBadVersion(int n){
        int left = 1,right = n,center,res = 0;
        while (left <= right) {
            center = left+(right-left)/2;
            if (isBadVersion(center)) {
                res = center;
                right = center-1;
            }else{
                left = center+1;
            }
        }
        return res;
    }*/
}
