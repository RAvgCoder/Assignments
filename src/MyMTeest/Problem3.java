package MyMTeest;
import MyMethods.*;

import java.util.*;
import java.util.Arrays;

public class Problem3 {
    public static void main(String[] args) {
//        List<String> s = new ArrayList<>();
        RalphsList<Integer> rolist = new RalphsList<>(new Integer[]{1,2,3,4,5,6});
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        rolist.add(3);
        System.out.printf("%1$s %2$s %1$s\n","-".repeat(16),rolist.getClass());
        System.out.println("Contains: "+rolist.contains(1));
        System.out.println("Size: "+rolist.size());
        System.out.println("Contents: "+Arrays.toString(rolist.toArray()));

        System.out.printf("\n%1$s %2$s %1$s\n","-".repeat(16),"Second");
        RalphsList ro = new RalphsList();
        System.out.println(Arrays.toString(ro.toArray()));
    }

}

