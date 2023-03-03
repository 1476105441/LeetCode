package algorithm.dp;

public class No87 {
    int[][][] vis;
    char[] c1,c2;
    int n;
    public boolean isScramble(String s1, String s2) {
        c1 = s1.toCharArray();
        c2 = s2.toCharArray();
        n = c1.length;
        vis = new int[n][n][n+1];
        return dfs(0,0,n);
    }
    private boolean dfs(int i,int j,int len){
        if(vis[i][j][len] != 0){
            return vis[i][j][len] == 1;
        }
        if(check(i,j,len)){
            vis[i][j][len] = 1;
            return true;
        }
        if(!check2(i,j,len)){
            vis[i][j][len] = -1;
            return false;
        }
        for(int k = 1;k < len;k++){
            //不交换
            if(dfs(i,j,k) && dfs(i+k,j+k,len-k)){
                vis[i][j][len] = 1;
                return true;
            }
            //交换
            if(dfs(i,j+len-k,k) && dfs(i+k,j,len-k)){
                vis[i][j][len] = 1;
                return true;
            }
        }
        vis[i][j][len] = -1;
        return false;
    }
    private boolean check(int i,int j,int len){
        if(len == 0){
            return true;
        }
        int l = j;
        for(int k = i;k < i+len;k++){
            if(c1[k] != c2[l]){
                return false;
            }
            l++;
        }
        return true;
    }
    private boolean check2(int i,int j,int len){
        if(len == 0){
            return true;
        }
        int[] set = new int[26];
        int l = j;
        for(int k = i;k < i+len;k++){
            int loc1 = c1[k] - 'a';
            int loc2 = c2[l] - 'a';
            set[loc1]++;
            set[loc2]--;
            l++;
        }
        for(int k = 0;k < 26;k++){
            if(set[k] != 0){
                return false;
            }
        }
        return true;
    }
}
