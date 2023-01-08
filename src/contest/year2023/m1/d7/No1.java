package contest.year2023.m1.d7;

public class No1 {
    public String categorizeBox(int length, int width, int height, int mass) {
        long v = (long)length * width * height;
        if (length >= 10000 || width >= 10000 || height >= 10000 || mass >= 10000 || v >= 1000000000) {
            if (mass >= 100) {
                return "Both";
            }
            return "Bulky";
        }
        if (mass >= 100) {
            return "Heavy";
        }
        return "Neither";
    }
}
