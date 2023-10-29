package CusMeth;

import TestLogFiles.TxtLogger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class RalphsListTest {
    private final Integer []testArray = {1,2,3,4,5,6};
    private final int bound = Math.min(99999999,9999999);
    private int vars = ThreadLocalRandom.current().nextInt(-23, bound);

    @Test
    public void size() {
        java.util.ArrayList<Integer> AlistSize = new java.util.ArrayList<>(List.of(testArray));
        ArrayList<Integer> listSize = new ArrayList<>(testArray);
        System.out.println(vars);
        TxtLogger loggers = TxtLogger.getInstance();
        loggers.startLog("Thyson",true);
        for (int i = 0; i < vars; i++) {
            listSize.add(i);
            AlistSize.add(i);
        }
        loggers.endLog();
        Assert.assertEquals(AlistSize.size(), listSize.size());
    }

    @Test
    public void isEmpty() {
        ArrayList<String> listEmpty = new ArrayList<>();
        System.out.println(vars);
        for (int i = 0; i<vars; i++){
            listEmpty.add(i+"");
        }
        Assert.assertFalse(listEmpty.isEmpty());
    }

    @Test
    public void contains() {
        java.util.ArrayList<String> AlistContains = new java.util.ArrayList<>();
        ArrayList<String> listContains = new ArrayList<>();
        System.out.println(vars);
        for (int i = 0; i <= vars; i++) {
            listContains.add(i+"");
            AlistContains.add(i+"");
        }
        String x = String.valueOf((vars/2));
        Assert.assertEquals(AlistContains.contains(x), listContains.contains(x));
    }

    @Test
    public void add() {
        java.util.ArrayList<Integer> AlistAdd = new java.util.ArrayList<>(List.of(testArray));
        ArrayList<Integer> listAdd = new ArrayList<>(List.of(testArray));
        System.out.println(vars);
        Assert.assertEquals(AlistAdd.add(vars),listAdd.add(vars));
    }

    @Test
    public void remove() {
        java.util.ArrayList<Integer> AlistRemove = new java.util.ArrayList<>();
        ArrayList<Integer> listRemove = new ArrayList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            listRemove.add(i);
            AlistRemove.add(i);
        }

        // Removes from index position
        int x =  vars / 2;
        Assert.assertArrayEquals(new Integer[]{AlistRemove.remove(x)}
                , new Integer[]{listRemove.remove(x)});
        Assert.assertEquals(AlistRemove.size(), listRemove.size());

        // Removes Object from the list
        java.util.ArrayList<String> AlistRemoves = new java.util.ArrayList<>();
        ArrayList<String> listRemoves = new ArrayList<>();
        for (int i = 0; i < vars; i++) {
            listRemoves.add(i+"");
            AlistRemoves.add(i+"");
        }
        Assert.assertEquals(AlistRemoves.remove(x+""), listRemoves.remove(x+""));
        Assert.assertEquals(AlistRemoves.size(),listRemoves.size());
    }

    @Test
    public void addAll() {
        java.util.ArrayList<Integer> AlistAddAll  = new java.util.ArrayList<>();
        ArrayList<Integer> listAddAll = new ArrayList<>();
        System.out.println(vars);
        AlistAddAll.addAll(List.of(testArray));
        listAddAll.addAll(testArray);
        Assert.assertEquals(AlistAddAll.addAll(List.of(testArray)),listAddAll.addAll(List.of(testArray)));
        Assert.assertEquals(AlistAddAll.size(),listAddAll.size());
    }

    @Test
    public void clear() {
        java.util.ArrayList<Integer> AlistClear = new java.util.ArrayList<>(List.of(testArray));
        ArrayList<Integer> listClear = new ArrayList<>(testArray);
        System.out.println(vars);
        for (int i=0; i<vars; i++){
            AlistClear.add(vars);
            listClear.add(vars);
        }
        AlistClear.clear();
        listClear.clear();
        Assert.assertEquals(AlistClear.size(),listClear.size());
    }

    @Test
    public void trimToSize(){
        java.util.ArrayList<Boolean> AlistTrim = new java.util.ArrayList<>();
        ArrayList<Boolean> listTrim = new ArrayList<>();
        System.out.println(vars);
        for (int i=0; i<vars; i++){
            AlistTrim.add(vars>Math.random());
            listTrim.add(vars>Math.random());
        }
        AlistTrim.trimToSize();
        listTrim.trimToSize();
        Assert.assertEquals(AlistTrim.size(),listTrim.size());
    }

    @Test
    public void indexOf() {
        java.util.ArrayList<Integer> AlistIndexOf = new java.util.ArrayList<>();
        ArrayList<Integer> listIndexOf = new ArrayList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            AlistIndexOf.add(i);
            listIndexOf.add(i);
        }

        Integer x = vars/2;
        Assert.assertEquals(AlistIndexOf.indexOf(x),listIndexOf.indexOf(x));
    }

    @Test
    public void lastIndexOf() {
        java.util.ArrayList<Integer> AlistLastIndexOf = new java.util.ArrayList<>();
        ArrayList<Integer> listLastIndexOf = new ArrayList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            AlistLastIndexOf.add(i);
            listLastIndexOf.add(i);
        }
        Integer x = (int) (vars/Math.random());
        Assert.assertEquals(AlistLastIndexOf.lastIndexOf(x),listLastIndexOf.lastIndexOf(x));
    }

    @Test
    public void get() {
        java.util.ArrayList<Integer> AlistGet = new java.util.ArrayList<>();
        ArrayList<Integer> listGet = new ArrayList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            AlistGet.add(i);
            listGet.add(i);
        }
        Integer x = (vars/4);
        Assert.assertEquals(AlistGet.get(x),listGet.get(x));
    }

    @Test
    public void sort() {
        ArrayList<Integer> list = new ArrayList<>();
        java.util.ArrayList<Integer> Alist = new java.util.ArrayList<>();
        for (int i = 0; i < vars; i++) {
            int x = (int) (i%Math.random());
            list.add(x);
            Alist.add(x);
        }
        Alist.sort(Comparator.naturalOrder());

    }

    @Test
    public void toArray() {
        System.out.println(vars);

    } //TODO create test for toArray()

    @Test
    public void testEquals() {
        System.out.println(vars);

    }

    @Test
    public void set() {
        System.out.println(vars);

    }

    @Test
    public void containsAll() {
        System.out.println(vars);

    }

    @Test
    public void isNull(){
        ArrayList<String> arrayList = null;
        ArrayList<String> arrayList2 = new ArrayList<>();
        Assert.assertNull(arrayList);
        Assert.assertNotNull(arrayList2);
    }
}