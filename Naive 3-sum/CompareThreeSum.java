import java.util.Random;

class NaiveThreeSum {
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    if (i < j && j < k)
                        if (a[i] + a[j] + a[k] == 0)
                            count++;
        return count;
    }
}

class ThreeSum {
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
        return count;
    }
}

class Stopwatch {
    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000000.0;
    }
}

final class StdRandom {
    static Random random;
    static long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    StdRandom() {
    }

    public static int uniform(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("argument must be positive" + n);
        return random.nextInt(n);
    }

    public static int uniform(int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform(b - a);
    }

}

public class CompareThreeSum {

    // Naive n^3
    public static double timeTrialNaive(int N) {
        int MAX = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        NaiveThreeSum.count(a);
        return timer.elapsedTime();
    }

    // better 3 sum
    public static double timeTrial(int N) {
        int MAX = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        double time1, time2;
        double ratio = 0.0;
        System.out.println("      N  Naive3Sum  ThreeSum   Ratio ");

        for (int N = 1; true; N += N) {
            time1 = timeTrialNaive(N);
            time2 = timeTrial(N);
            ratio = time1 / time2;
            System.out.printf("%7d  %5.6f  %5.6f  %f \n", N, time1, time2, ratio);
        }
    }
}
