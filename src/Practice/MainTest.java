package Practice;

import MyMethods.RalphsList;

import java.util.ArrayList;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) {
        RalphsList<String> ralphsList = new RalphsList<>(new String[]{"1","2"});
        for (int i = 0; i < 10000; i++) {
            ralphsList.add(i+"");
        }
        System.out.println(ralphsList.remove(20));
        System.out.println(ralphsList.remove(20));
        System.out.println(ralphsList.remove(20));
        System.out.println(ralphsList.remove(20));
        System.out.println(ralphsList.remove(20));
        System.out.println(ralphsList.remove(20));
//        System.out.println(ralphsList.get(1)+"---------------------");
//        System.out.println(Arrays.toString(ralphsList.toArray()));
        System.out.println(ralphsList.size());
//        ArrayList c = new ArrayList<>();
//        c.get(2);



    }
}
