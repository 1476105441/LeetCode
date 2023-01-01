package review.中级算法复习.回溯算法;

public class No1 {
    //              电话号码的字母组合

    /*List<String> cList;
    {
        cList = new ArrayList<>();
        cList.add("abc");
        cList.add("def");
        cList.add("ghi");
        cList.add("jkl");
        cList.add("mno");
        cList.add("pqrs");
        cList.add("tuv");
        cList.add("wxyz");
    }*/
    /*public List<String> letterCombinations(String digits){
        int n = digits.length(),k = 0;
        int[] a = new int[n];
        int[] b = new int[n];
        List<String> res = new ArrayList<>();
        if("".equals(digits)){
            return res;
        }
        for (int i = 0; i < n; i++) {
            a[i] = -1;
            b[i] = digits.charAt(i) - 50;
        }

        while (k > -1) {
            a[k] = a[k] + 1;
            if (k < n-1 && a[k] < cList.get(b[k]).length()) {
                k++;
            }else if(k == n-1){
                while (a[k] < cList.get(b[k]).length()) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        sb.append(cList.get(b[i]).charAt(a[i]));
                    }
                    res.add(sb.toString());
                    a[k]++;
                }
                a[k] = -1;
                k--;
            } else if(k < n-1){
                a[k] = -1;
                k--;
            }
        }

        return res;
    }*/
}
