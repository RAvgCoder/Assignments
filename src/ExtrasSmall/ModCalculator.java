package ExtrasSmall;

import java.util.Arrays;

public class ModCalculator {
    public static void main(String[] args) {
        var start = System.nanoTime();
        int[] ans = mod("0-999999999", 999999999);
        System.out.println(String.format("Quotient: %s\nMod/Remainder: %s",ans[0],ans[1]));
        System.out.println((System.nanoTime()-start)/1000000000+"ms");
    }

    private static int[] mod(String expression, int modNum) {
        String symbol = String.join("",expression.replaceAll("\\s+","").split("[0-9]"));
        int[] number = Arrays.stream(expression.replaceAll("\\s+","")
                .split("[|x|X|+|-]"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int result = number[0];
        switch (symbol){
            case "+" -> result = number[0]+number[1];
            case "x" -> result = number[0]*number[1];
            case "-" -> result = number[0]-number[1];
        }
        if (result<=0){
            return findRUSlow(result,modNum);
        }
        return findRU(result,modNum);
    }

    private static int[] findRUSlow(int result, int modNum) {
        int startPoint = (result-(result%modNum))/modNum;
        for (int i = startPoint; i > Integer.MIN_VALUE; i--) {
            for (int j = 0; j < modNum; j++) {
                if (result == (i*modNum)+j)
                    return new int[]{i,j};
            }
        }
        return new int[] {-1,-1};
    }

    private static int[] findRU(int result, int modNum) {
        int mod = result%modNum;
        return new int[]{
                (result-mod)/modNum
                ,mod
        };
    }
}
