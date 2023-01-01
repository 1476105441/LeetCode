package review.中级算法复习.数学;

public class No5 {
    //              x的平方根

    public int mySqrt(int x){
        if(x == 0){
            return 0;
        }
        if(x <= 3){
            return 1;
        }
        long center,left = 1,right = x/2,temp1,temp2;

        while (left <= right) {
            center = left + (right - left)/2;
            temp1 = center * center;
            temp2 = (center + 1) * (center + 1);
            if (temp1 < x && temp2 > x) {
                return (int)center;
            } else if (temp1 == x) {
                return (int)center;
            } else if (temp1 > x) {
                right = center - 1;
            } else if (temp2 < x) {
                left = center + 1;
            } else {
                return (int)(center + 1);
            }
        }

        return -1;
    }
}
