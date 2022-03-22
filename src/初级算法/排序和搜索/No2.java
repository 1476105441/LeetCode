package 初级算法.排序和搜索;

public class No2 {
    //                   第一个错误的版本
    //  你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
    //假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
    //你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

    //------------------------------------------------------------------
    //            想法：二分查找
    /*
    傻逼才用非递归
    public int firstBadVersion(int n) {
        if (n == 1) {
            return n;
        }

        int left = 1,right = n,center = right / 2;

        while (center <= right && center >= left) {
            //是一个坏的版本
            if (isBadVersion(center)) {
                //找到了
                if (center > 1 && !isBadVersion(center-1)) {
                    return center;
                } else if (center == left) {
                    return center;
                } else {
                    //没找到，在左半边找
                    //更改左边界
                    right = center;
                    center =left + (center - left) / 2;
                }
            }else{
                //不是坏的版本，就在右半边找
                left = center;
                center = (center + right) / 2;
                if (center == left) {
                    center++;
                }
            }
        }
        return -1;
    }*/

    //-------------------------------------------------------------------------------
    //                                   超出时间限制了
    /*
    public int firstBadVersion(int n){
        return recursion(1,n);
    }

    public int recursion(int left,int right) {

        if (left == right) {
            return left;
        }

        int center = left + (right - left)/2;

        if (center == left) {
            if (isBadVersion(center)) {
                return center;
            } else
                return right;
        }

        //如果是坏版本的话
        if (isBadVersion(center)) {
            //对比前一个，如果前一个也是，就在左半边找
            if (isBadVersion(center - 1)) {
                return recursion(left, center);
            } else
                return center;
        } else {
            return recursion(center, right);
        }
    }*/
    //-----------------------------------------------------------------------------------

    //重新想一个非递归的二分查找
    /*
    public int firstBadVersion(int n){
        if (n == 1) {
            return n;
        }

        //因为前面用的center = (left + right) / 2 这个值会超出int类型的范围，不能使用两个相加
        int left = 1,right = n,center = left + (right - left)/2;

        //一直到左边等于右边，缩小范围至一个点（与快速排序类同），该点就是要找的版本
        while (left < right) {
            if (isBadVersion(center)) {
                //在左边找
                right = center;
            }else{
                //在左边找的时候要包括center点，但是在右边找的时候不用包括，因为center点已经不是badVersion了
                left = center + 1;
            }
            center = left + (right - left) / 2;
        }
        return left;
    }*/
}
