package contest.year2022.m12.d11;

public class No3 {

}
/*
class Allocator {
    Map<Integer,Integer> map;
    int[] heap,len;
    int pos,n;
    public Allocator(int n) {
        map = new HashMap<>();
        heap = new int[n];
        len = new int[n];
        this.n = n;
        len[0] = n;
    }

    public int allocate(int size, int mID) {
        int res = -1;
        while (pos < n) {
            //当前块的内存足够，且没有人占领
            if (heap[pos] == 0 && len[pos] >= size) {
                int dif = len[pos] - size;
                heap[pos] = mID; //更新内存中的信息
                map.put(mID,pos); //记录块信息
                res = pos; //存储返回结果
                pos = pos + size; //移动到下一个块上
                len[pos-1] = size; //记录尾部信息
                if (heap[pos] > 0) {
                    //移动到下一个空闲位置
                    while (heap[pos] > 0) {
                        pos += len[pos];
                    }
                }else{
                    len[pos] = dif;
                }
                break;
            }
            pos += len[pos];
        }

        return res;
    }

    //回收之后还要合并
    public int free(int mID) {
        int res = 0;
        Integer loc = map.get(mID);
        if (loc != null) {
            heap[loc] = 0;
            res = len[loc];
            int pre = loc - len[loc-1],next = loc + len[loc],newLoc = loc;
            if (heap[pre] == 0) {
                newLoc = pre;
                len[pre + len[pre] -1] = 0; //清除原尾部信息
                len[pre] += len[loc];
                len[loc+len[loc]-1] = len[pre]; //更新尾部信息
                len[loc] = 0;
            }
            if (heap[next] == 0) {
                len[loc + len[loc] - 1] = 0;
                len[loc] += len[next];
                len[next + len[next] -1] = len[loc];
                len[next] = 0;
            }
            loc = newLoc;
            if (loc < pos) {
                pos = loc;
            }
        }
        return res;
    }
}*/
class Allocator {
    int[] heap;
    int n;
    public Allocator(int n) {
        this.n = n;
        heap = new int[n];
    }

    public int allocate(int size, int mID) {
        int l = 0,r = l,c = 0,res = -1;
        while (r < n) {
            if (heap[r] != 0) {
                l = r+1;
                c = 0;
            } else {
                c++;
                if (c == size) {
                    for (int i = l; i <= r; i++) {
                        heap[i] = mID;
                    }
                    res = l;
                    break;
                }
            }
            r++;
        }
        return res;
    }

    public int free(int mID) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (heap[i] == mID) {
                heap[i] = 0;
                res++;
            }
        }
        return res;
    }
}