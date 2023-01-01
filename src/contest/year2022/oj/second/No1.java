package contest.year2022.oj.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class No1 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        in.nextToken();
        int len = (int) in.nval;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            in.nextToken();
            int nval = (int) in.nval;
            sb.append((nval+1)/2+1).append("\n");
        }
        System.out.println(sb);
    }
}
