package test;

import 数据结构.AVLTree;
import 数据结构.TreeNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.IntStream;

public class Test1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.remove(0);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }

    public static int transInt(int n,int num){
        int res = 0;
        while (num != 0) {
            int temp = num % 10 + 5;
            temp = temp % 10;
            res *= 10;
            res += temp;
            num /= 10;
        }
        return res;
    }

    static int c = 0;
    static String add(String a, String b) {
        // Please fill this blank
        char[] aChar = a.toCharArray(),bChar = b.toCharArray();
        int i = aChar.length-1,j = bChar.length-1,len;

        if(i > j){
            len = i+1;
        }else{
            len = j+1;
        }
        char[] res = new char[len];
        int temp1,temp2,temp,k=len-1;

        while(i >= 0 && j >= 0){
            temp1 = transNum(aChar[i]);
            temp2 = transNum(bChar[j]);

            temp = temp1 + temp2 + c;
            if (temp >= 36) {
                c = 1;
                temp -= 36;
            }else{
                c = 0;
            }
            if(temp > 47 && temp < 58){
                temp += 48;
            }else
                temp += 87;
            res[k--] = (char)temp;
            i--;
            j--;
        }

        while (i >= 0) {
            res[k--] = transform(aChar,i);
            i--;
        }
        while (j >= 0) {
            res[k--] = transform(bChar,j);
            j--;
        }

        return new String(res);
    }

    public static char transform(char[] chars,int loc){
        int temp1,temp;
        temp1 = transNum(chars[loc]);
        temp = temp1 + c;
        if (temp >= 36) {
            c = 1;
            temp -= 36;
        }else{
            c = 0;
        }
        if(temp > 47 && temp < 58){
            temp += 48;
        }else
            temp += 87;

        return (char)temp;
    }

    public static int transNum(char c){
        if(c > 47 && c < 58){
            c -= 48;
        }else{
            c -= 87;
        }
        return c;
    }


    //============================================================
    static int solve(int[] arr, int n, int m) {
        // 请添加具体实现
        java.util.Set<Integer> set = new java.util.HashSet<>();
        int res = 0;

        for (int i = 0; i < n; i++) {
            if (set.contains(arr[i])) {
                res++;
            }else{
                set.add(m-arr[i]);
            }
        }
        return res;
    }
}