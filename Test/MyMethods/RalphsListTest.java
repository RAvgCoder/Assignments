package MyMethods;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RalphsListTest {
    private Integer []testArray = {1,2,3,4,5,6};
    int vars = ThreadLocalRandom.current().nextInt(-2, 999999);

    @Test
    public void size() {
        ArrayList<Integer> AlistSize = new ArrayList<>(List.of(testArray));
        RalphsList<Integer> listSize = new RalphsList<>(testArray);
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            listSize.add(i);
            AlistSize.add(i);
        }
        Assert.assertArrayEquals(new Integer[]{AlistSize.size()}, new Integer[]{listSize.size()});
    }

    @Test
    public void isEmpty() {
        ArrayList<String> AlistEmpty = new ArrayList<>();
        RalphsList<String> listEmpty = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i<vars; i++){
            listEmpty.add(i+"");
            AlistEmpty.add(i+"");
        }
        Assert.assertArrayEquals(new Boolean[]{AlistEmpty.isEmpty()},new Boolean[]{listEmpty.isEmpty()});
    }

    @Test
    public void contains() {
        ArrayList<String> AlistContains = new ArrayList<>();
        RalphsList<String> listContains = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            listContains.add(i+"");
            AlistContains.add(i+"");
        }
        String x = String.valueOf(((int) vars / 2));
        Assert.assertArrayEquals(new Boolean[]{AlistContains.contains(x)}, new Boolean[]{listContains.contains(x)});
    }

    @Test
    public void add() {
        ArrayList<Integer> AlistAdd = new ArrayList<>(List.of(testArray));
        RalphsList<Integer> listAdd = new RalphsList<>(List.of(testArray));
        System.out.println(vars);
        Assert.assertArrayEquals(new Boolean[]{AlistAdd.add(vars)}, new Boolean[]{listAdd.add(vars)});
    }

    @Test
    public void remove() {
        ArrayList<Integer> AlistRemove = new ArrayList<>();
        RalphsList<Integer> listRemove = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            listRemove.add(vars);
            AlistRemove.add(vars);
        }
        // Removes from index position
        int x =  vars / 2;
        Assert.assertArrayEquals(new Integer[]{AlistRemove.remove(x)}, new Integer[]{listRemove.remove(x)});
        Assert.assertArrayEquals(new Integer[]{AlistRemove.size()},new Integer[]{listRemove.size()});

        // Removes Object from the list
        ArrayList<String> AlistRemoves = new ArrayList<>();
        RalphsList<String> listRemoves = new RalphsList<>();
        for (int i = 0; i < vars; i++) {
            listRemoves.add(vars+"");
            AlistRemoves.add(vars+"");
        }
        Assert.assertArrayEquals(new Boolean[]{AlistRemoves.remove(x+"")}, new Boolean[]{listRemoves.remove(x+"")});
        Assert.assertArrayEquals(new Integer[]{AlistRemoves.size()},new Integer[]{listRemoves.size()});
    }

    @Test
    public void addAll() {
        System.out.println(vars);

    }

    @Test
    public void testAddAll() {
        System.out.println(vars);

    }

    @Test
    public void clear() {
        System.out.println(vars);

    }

    @Test
    public void testEquals() {
        System.out.println(vars);

    }

    @Test
    public void get() {
        System.out.println(vars);

    }

    @Test
    public void set() {
        System.out.println(vars);

    }

    @Test
    public void testAdd() {
        System.out.println(vars);

    }

    @Test
    public void testRemove() {
        System.out.println(vars);

    }

    @Test
    public void indexOf() {
        System.out.println(vars);

    }

    @Test
    public void lastIndexOf() {
        System.out.println(vars);

    }

    @Test
    public void containsAll() {
        System.out.println(vars);

    }

    @Test
    public void toArray() {
        System.out.println(vars);

    }
}