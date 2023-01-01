package contest.year2022.m10.m10d29;

public class No1 {
    /**
     *  差值数组不同的字符串
     */

    public String oddString(String[] words) {
        int n = words.length;
        int[] temp = null;
        char[][] chars = new char[n][];

        for (int i = 0; i < n; i++) {
            chars[i] = words[i].toCharArray();
        }

        String res = "",backup = "";
        for (int i = 0; i < n; i++) {
            int[] count = count(chars[i]);
            boolean flag = true;
            if (temp == null) {
                temp = count;
            }else{
                for (int j = 0; j < chars[i].length - 1; j++) {
                    if (temp[j] != count[j]) {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag && i != 1) {
                if ("".equals(backup)) {
                    res = words[i];
                    break;
                } else {
                    res = words[0];
                    break;
                }
            }else if(!flag)
                backup = words[i];
        }

        return "".equals(res) ? backup : res;
    }

    public int[] count(char[] chars){
        int[] temp = new int[chars.length-1];
        for (int i = 0; i < chars.length-1; i++) {
            temp[i] = chars[i+1]-chars[i];
        }
        return temp;
    }
}
