package A_3110;

import java.util.HashSet;

public class A2 {
    // RAW SRC CODE
    public static boolean problem2(int[] lengths) {
        for (int a : lengths) {
            for (int b : lengths) {
                for (int c : lengths) {
                    if (a * a + b * b == c * c) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean problem3(int[] lengths) {
        HashSet<Integer> cSquares = new HashSet<>();

        for (int x : lengths) {
            cSquares.add(x * x);
        }

        for (int a : lengths) {
            for (int b : lengths) {
                if (cSquares.contains(a * a + b * b)) {
                    return true;
                }
            }
        }
        return false;
    }
}
