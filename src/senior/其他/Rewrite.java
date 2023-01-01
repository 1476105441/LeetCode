package senior.其他;

import java.util.*;

public class Rewrite {
    //根据身高重建队列

    //根据身高排序
    //解法一：从低到高排序，先放入身高低的
    public int[][] reconstructQueue1(int[][] people) {
        int n = people.length;
        int[][] res = new int[n][2];

        //核心点也在于排序，将较小的元素排在前面，如果元素的值相等，则按照位置降序排序
        Arrays.sort(people, (x, y) -> {
            if (x[0] != y[0])
                return x[0] - y[0];
            return y[1] - x[1];
        });

        for (int i = 0; i < n; i++) {
            res[i][0] = -1;
        }
        for (int i = 0; i < n; i++) {
            int count = people[i][1] + 1;
            for (int j = 0; j < n; j++) {
                if (res[j][0] == -1) {
                    count--;
                    if (count == 0) {
                        res[j][0] = people[i][0];
                        res[j][1] = people[i][1];
                        break;
                    }
                }
            }
        }

        return res;
    }


    //解法二：从高到低排序，采用插入的方法
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        List<int[]> res = new ArrayList<>(n);

        Arrays.sort(people, (x, y) -> {
            if (x[0] != y[0])
                return y[0] - x[0];
            return x[1] - y[1];
        });

        for (int i = 0; i < n; i++) {
            res.add(people[i][1], people[i]);
        }

        return res.toArray(new int[n][]);
    }


    //接雨水

    //解法一：预处理，1ms，还不是最快的解法
    public int trap1(int[] height) {
        int n = height.length, res = 0;
        int[] left = new int[n], right = new int[n]; // left和right中存储的分别是当前元素左边最大的节点和右边最大的节点
        left[0] = height[0];
        right[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - 1 - i] = Math.max(right[n - i], height[n - 1 - i]);
        }

        for (int i = 1; i < n - 1; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }

        return res;
    }

    //解法二：单调栈，成功，1ms
    public static int trap2(int[] height) {
        int n = height.length, res = 0, l = 0;
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            int floor = 0;
            //使用单调栈，单调递减
            //每个循环的逻辑是：以当前栈顶为底
            while (l > 0 && height[i] >= height[stack[l - 1]]) {
                int j = stack[l - 1], dis;
                floor = height[j];
                l--;
                if (l != 0) {
                    dis = i - stack[l - 1] - 1; //计算和非栈顶元素之间的长
                    res += dis * (Math.min(height[stack[l - 1]], height[i]) - floor); //长*宽，宽=左右两边最小的值-floor
                }
            }
            stack[l++] = i;
        }

        return res;
    }


    //解法三：双指针，贪心思想
    //维护几个信息：左、右指针，左、右最大值
    public static int trap(int[] height) {
        int n = height.length, res = 0, l = 0, r = n - 1, lm = height[0], rm = height[n - 1];

        while (l < r) {
            if (height[r] > height[l]) {
                //更新结果
                res += lm - height[l];
                l++;
                //更新最值
                if (lm < height[l])
                    lm = height[l];
            } else {
                res += rm - height[r];
                r--;
                if (rm < height[r])
                    rm = height[r];
            }
        }

        return res;
    }


    //天际线问题
    /*public List<List<Integer>> getSkyline1(int[][] buildings) {
        int n = buildings.length, l = 0;
        //梳理一下，一共分为几步
        //1、将所有可能的关键点的横坐标存储起来，并且按照大小顺序存储起来
        int[] temp = new int[n << 1];
        for (int i = 0; i < n; i++) {
            temp[l++] = buildings[i][0];
            temp[l++] = buildings[i][1];
        }
        Arrays.sort(temp);

        //2、遍历集合中的可能关键点，并维护优先队列，队列中存放的是元素的右边界和高度，并且按照高度形成大根堆
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        int loc = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            //先将可能的边界加入优先队列（堆）中
            while (loc < n && buildings[loc][0] <= temp[i]) {
                queue.offer(new int[]{buildings[loc][1], buildings[loc][2]});
                loc++;
            }
            //去掉不符合条件的堆顶，范围的右边界小于当前关键点的横坐标时，去除这个范围
            while (!queue.isEmpty() && queue.peek()[0] <= temp[i])
                queue.poll();
            //更新结果，注意要判断是否与前一个结果相同，这样可以去掉不是关键点的结果
            int[] peek = queue.peek();
            List<Integer> node = new ArrayList<>();
            node.add(temp[i]);
            if (peek != null)
                node.add(peek[1]);
            else
                node.add(0);
            if (res.isEmpty() || !res.get(res.size() - 1).get(1).equals(node.get(1)))
                res.add(node);
        }

        return res;
    }*/


    //重新写一下天际线问题，经过观察发现，所有的关键点的横坐标都会出
    //现在建筑物的左端点和右端点上，所以我们只需要考察建筑物的左右端
    //点即可，关键点的纵坐标由经过它的建筑物的高度的最大值决定，在这
    //里，经过的含义是不包括相等的情况的（有点抽象）
    //使用优先队列（最大堆）
    public List<List<Integer>> getSkyline(int[][] buildings){
        int n = buildings.length,loc = 0,m = n << 1;
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = new int[m];
        for (int i = 0; i < buildings.length; i++) {
            nums[loc++] = buildings[i][0];
            nums[loc++] = buildings[i][1];
        }
        Arrays.sort(nums);

        PriorityQueue<int[]> dump = new PriorityQueue<>((x,y)->{
            return y[1]-x[1];
        });

        loc = 0;
        for (int i = 0; i < m; i++) {
            int x = nums[i],y;
            //对于每一个可能的关键点横坐标，先将包含它的建筑物加入有限队列中
            while (loc < n && buildings[loc][0] <= x) {
                dump.offer(new int[]{buildings[loc][1],buildings[loc][2]});
                loc++;
            }
            //取出堆顶的元素，并去除'过时'的元素
            while(!dump.isEmpty() && dump.peek()[0] <= x)
                dump.poll();

            if(dump.peek() == null)
                y = 0;
            else
                y = dump.peek()[1];

            if (res.size() == 0 || !res.get(res.size() - 1).get(1).equals(y)) {
                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(y);
                res.add(list);
            }
        }

        return res;
    }



    //柱状图中最大的矩形
    //想法：使用单调队列，但时间复杂度可能是O(n)
    //想的太简单了，失败
    /*public int largestRectangleArea(int[] heights) {
        int n = heights.length,res = 0,l = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            //队列是单调非递减的，首先将大于当前元素的队尾出队
            while(!queue.isEmpty() && heights[queue.getLast()] > heights[i])
                queue.pollLast();
            //然后取出队列中所有的元素，计算矩形的大小
            for (Integer num : queue) {
                res = Math.max(res,Math.min(heights[i],heights[num]) * (i-num+1));
            }
            res = Math.max(res,heights[i]);
            queue.addLast(i);
        }

        return res;
    }*/


    //改进刚刚的代码
    //果然是超时
    /*public int largestRectangleArea(int[] heights){
        int n = heights.length,res = 0,l = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        //维护一个数组，统计在此元素之前还有多少元素是大于等于它的
        int[] len = new int[n];

        for (int i = 0; i < n; i++) {
            //队列是单调非递减的，首先将大于当前元素的队尾出队
            int c = 0;
            while (!queue.isEmpty() && heights[queue.getLast()] >= heights[i]) {
                c += len[queue.getLast()]+1;
                queue.pollLast();
            }

            //然后取出队列中所有的元素，计算矩形的大小
            for (Integer num : queue) {
                int x,y = 0;
                if (heights[num] > heights[i]) {
                    x = heights[i];
                } else {
                    x = heights[num];
                    y = len[num];
                }
                res = Math.max(res,x * (i-num+1+y));
            }
            len[i] = c;
            res = Math.max(res,heights[i] * (c+1));
            queue.addLast(i);
        }

        return res;
    }*/


    //预处理，失败，这样写根本不行
    /*public int largestRectangleArea(int[] heights){
        int n = heights.length,res = 0;
        int[] left = new int[n],right = new int[n];

        for (int i = 1; i < n; i++) {
            left[i] = heights[i-1] >= heights[i] ? left[i-1]+1 : 0;
            right[n-1-i] = heights[n-i] >= heights[n-1-i] ? right[n-i]+1 : 0;
        }

        res = heights[0] * (left[0] + right[0]+1);
        for (int i = 1; i < n; i++) {
            if(heights[i] == 0 || heights[i] == heights[i-1])
                continue;
            int len = left[i] + right[i] + 1;
            res = Math.max(res,heights[i] * len);
        }

        return res;
    }*/


    //使用单调栈记录数组中每个元素的左端点和右端点，存放单调递增的元素
    public int largestRectangleArea(int[] heights){
        int n = heights.length,res = 0,len = 0; //len 是单调栈的指针
        int[] stack = new int[n],left = new int[n],right = new int[n];
        for (int i = 0; i < n; i++) {
            while (len != 0 && heights[stack[len - 1]] > heights[i]) {
                right[stack[len-1]] = i;
                len--;
            }
            //当前栈中剩余的元素就是当前元素的左端点，因为比当前元素要小
            if(len == 0)
                left[i] = -1;
            else
                left[i] = stack[len-1];

            //将当前元素入栈
            stack[len++] = i;
        }
        while (len != 0) {
            right[stack[len-1]] = n;
            len--;
        }

        for (int i = 0; i < n; i++) {
            res = Math.max(res,(right[i] - left[i] - 1) * heights[i]);
        }

        return res;
    }
}