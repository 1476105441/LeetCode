package 早先做的一些题目;

public class LeetCode10 {
    public static void main(String[] args) {
        /*String s = "mississippi";
        String p = "mis*is*ip*.";

        System.out.println(l.isMatch(s,p));*/
    }

    public boolean isMatch(String s,String p){
        //sΪ��ʱ
        if (s == null) {
            if (p == null) {
                return true;
            }
            return false;
        }

        //s��Ϊ��ʱ
        if (p == null) {
            return false;
        }

        int i=0,j=0;

        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();

        while (i<sChar.length&&j<pChar.length) {
            if(pChar[j]=='.'||sChar[i]==pChar[j]){
                i++;
                j++;
            }
            else{
                //���p�ĵ�ǰ�ַ���*
                if (pChar[j] == '*') {
                    //ǰ��û����Ч�ַ�
                    if (j - 1 == -1) {
                        return false;
                    }
                    //ǰ������Ч�ַ�ʱ���бȽ�
                    if(pChar[j-1]=='.'||sChar[i]==pChar[j-1]){
                        i++;
                    }else j++;
                }
                //p�ĵ�ǰ�ַ�����*��Ҳ��s��ǰ�ַ������
                else if(j< pChar.length-1&&pChar[j+1]=='*'){
                  j++;
                }
                else return false;
            }
        }

        System.out.println("++++++++++");
        System.out.println(i< sChar.length);
        System.out.println(j<pChar.length-1);
        System.out.println(j == pChar.length-1&&pChar[j]!='*');
        //ѭ��������s��û��ƥ����
        if(i< sChar.length||j<pChar.length-1||(j == pChar.length-1&&pChar[j]!='*')) return false;

        return true;
    }

    public static int m1()throws RuntimeException{
        int i=100;
        try {
            return i;
        }finally {
            i++;
            System.out.println(i);
        }
    }
}
