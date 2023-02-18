package MyMethods;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RalphsListTest {
    private Integer []testArray = {1,2,3,4,5,6};
    private int bound = Math.min(2,9999999);
    int vars = ThreadLocalRandom.current().nextInt(-bound, bound);

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
        Assert.assertArrayEquals(new Integer[]{AlistRemove.remove(x)}
                , new Integer[]{listRemove.remove(x)});
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
        ArrayList<Integer> AlistAddAll  = new ArrayList<>();
        RalphsList<Integer> listAddAll = new RalphsList<>();
        System.out.println(vars);
        AlistAddAll.addAll(List.of(testArray));
        listAddAll.addAll(testArray);
        Assert.assertArrayEquals(new Boolean[]{AlistAddAll.addAll(List.of(testArray))},new Boolean[]{listAddAll.addAll(testArray)});
        Assert.assertArrayEquals(new Integer[]{AlistAddAll.size()},new Integer[]{listAddAll.size()});
    }

    @Test
    public void clear() {
        ArrayList<Integer> AlistClear = new ArrayList<>(List.of(testArray));
        RalphsList<Integer> listClear = new RalphsList<>(testArray);
        System.out.println(vars);
        for (int i=0; i<vars; i++){
            AlistClear.add(vars);
            listClear.add(vars);
        }
        AlistClear.clear();
        listClear.clear();
        Assert.assertArrayEquals(new Integer[]{AlistClear.size()},new Integer[]{listClear.size()});
    }

    @Test
    public void trimToSize(){
        ArrayList<Boolean> AlistTrim = new ArrayList<>();
        RalphsList<Boolean> listTrim = new RalphsList<>();
        System.out.println(vars);
        for (int i=0; i<vars; i++){
            AlistTrim.add(vars>Math.random());
            listTrim.add(vars>Math.random());
        }
        AlistTrim.trimToSize();
        listTrim.trimToSize();
        Assert.assertArrayEquals(new Integer[]{AlistTrim.size()},new Integer[]{listTrim.size()});
    }

    @Test
    public void indexOf() {
        ArrayList<Integer> AlistIndexOf = new ArrayList<>();
        RalphsList<Integer> listIndexOf = new RalphsList<>();
        System.out.println(vars);
        vars = 44;
        for (int i = 0; i < vars; i++) {
            AlistIndexOf.add(i);
            listIndexOf.add(i);
        }
        Integer x = vars-1;
        Assert.assertArrayEquals(new Integer[]{AlistIndexOf.indexOf(x)},new Integer[]{listIndexOf.indexOf(x)});
    }

    @Test
    public void lastIndexOf() {
        ArrayList<Integer> AlistLastIndexOf = new ArrayList<>();
        RalphsList<Integer> listLastIndexOf = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            AlistLastIndexOf.add(i);
            listLastIndexOf.add(i);
        }
        Assert.assertArrayEquals(new Integer[]{AlistLastIndexOf.lastIndexOf(0)}, new Integer[]{listLastIndexOf.lastIndexOf(0)});
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
    public void containsAll() {
        System.out.println(vars);

    }

    @Test
    public void toArray() {
        System.out.println(vars);

    }
}