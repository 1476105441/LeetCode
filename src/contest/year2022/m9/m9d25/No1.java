package contest.year2022.m9.m9d25;

public class No1 {
    /**
     *      按身高排序
     */
    String[] names;
    int[] heights;
    public String[] sortPeople(String[] names, int[] heights) {
        int n = heights.length;
        this.names = names;
        this.heights = heights;
        quickSort(0,n-1);
        return names;
    }
    public void quickSort(int l,int r){
        if (l >= r) {
            return;
        }
        int i = l,j = r;
        while (i < j) {
            while (i < j && heights[i] > heights[j]) {
                j--;
            }
            if (i < j) {
                int temp = heights[i];
                heights[i] = heights[j];
                heights[j] = temp;
                String s = names[i];
                names[i] = names[j];
                names[j] = s;
                i++;
            }
            while (i < j && heights[i] > heights[j]) {
                i++;
            }
            if (i < j) {
                int temp = heights[i];
                heights[i] = heights[j];
                heights[j] = temp;
                String s = names[i];
                names[i] = names[j];
                names[j] = s;
                j--;
            }
        }
        quickSort(l,i-1);
        quickSort(i+1,r);
    }
}
