package contest.year2022.m11.d13;

public class No1 {
    public static double[] convertTemperature(double celsius) {
        double[] res = new double[2];

        res[0] = celsius + 273.15;
        res[1] = celsius * 1.8 + 32.00;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(1%5);
    }
}
