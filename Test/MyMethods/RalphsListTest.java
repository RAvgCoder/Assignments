package MyMethods;

import TeestLogFiles.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RalphsListTest {
    private Integer []testArray = {1,2,3,4,5,6};
    private int bound = Math.min(999999999,9999999);
    int vars = ThreadLocalRandom.current().nextInt(2, bound);

     /////////////   LOGGERS START   //////////////////////
    Logger logger;
    /////////////   LOGGERS END     //////////////////////


    @Test
    public void size() {
        logger = new Logger();
        ArrayList<Integer> AlistSize = new ArrayList<>(List.of(testArray));
        RalphsList<Integer> listSize = new RalphsList<>(testArray);
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            listSize.add(i);
            AlistSize.add(i);
//            logger.reading(listSize,"listSize",listSize)
        }
        Assert.assertEquals(AlistSize.size(), listSize.size());
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
        Assert.assertEquals(AlistEmpty.isEmpty(),listEmpty.isEmpty());
    }

    @Test
    public void contains() {
        logger = new Logger();
        ArrayList<String> AlistContains = new ArrayList<>();
        RalphsList<String> listContains = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i <= vars; i++) {
            listContains.add(i+"");
            AlistContains.add(i+"");
            logger.running(listContains,"listContains",listContains.action,new double[]{listContains.start,listContains.end,listContains.timeElapsed});
            listContains.action =false;
        }
        String x = String.valueOf((vars/2));
        logger.endLog(listContains.totoalTimeSpent,listContains.count);
        Assert.assertEquals(AlistContains.contains(x), listContains.contains(x));
    }

    @Test
    public void add() {
        ArrayList<Integer> AlistAdd = new ArrayList<>(List.of(testArray));
        RalphsList<Integer> listAdd = new RalphsList<>(List.of(testArray));
        System.out.println(vars);
        Assert.assertEquals(AlistAdd.add(vars),listAdd.add(vars));
    }

    @Test
    public void remove() {
        ArrayList<Integer> AlistRemove = new ArrayList<>();
        RalphsList<Integer> listRemove = new RalphsList<>();
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
        ArrayList<String> AlistRemoves = new ArrayList<>();
        RalphsList<String> listRemoves = new RalphsList<>();
        for (int i = 0; i < vars; i++) {
            listRemoves.add(i+"");
            AlistRemoves.add(i+"");
        }
        Assert.assertEquals(AlistRemoves.remove(x+""), listRemoves.remove(x+""));
        Assert.assertEquals(AlistRemoves.size(),listRemoves.size());
    }

    @Test
    public void addAll() {
        ArrayList<Integer> AlistAddAll  = new ArrayList<>();
        RalphsList<Integer> listAddAll = new RalphsList<>();
        System.out.println(vars);
        AlistAddAll.addAll(List.of(testArray));
        listAddAll.addAll(testArray);
        Assert.assertEquals(AlistAddAll.addAll(List.of(testArray)),listAddAll.addAll(List.of(testArray)));
        Assert.assertEquals(AlistAddAll.size(),listAddAll.size());
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
        Assert.assertEquals(new Integer[]{AlistClear.size()},new Integer[]{listClear.size()});
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
        Assert.assertEquals(AlistTrim.size(),listTrim.size());
    }

    @Test
    public void indexOf() {
        ArrayList<Integer> AlistIndexOf = new ArrayList<>();
        RalphsList<Integer> listIndexOf = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            AlistIndexOf.add(i);
            listIndexOf.add(i);
        }

        Integer x = vars/2;
        Assert.assertEquals(AlistIndexOf.indexOf(x),listIndexOf.indexOf(x,vars-1));
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
        Integer x = (int) (vars/Math.random());
        Assert.assertEquals(AlistLastIndexOf.lastIndexOf(x),listLastIndexOf.lastIndexOf(x));
    }

    @Test
    public void get() {
        ArrayList<Integer> AlistGet = new ArrayList<>();
        RalphsList<Integer> listGet = new RalphsList<>();
        System.out.println(vars);
        for (int i = 0; i < vars; i++) {
            AlistGet.add(i);
            listGet.add(i);
        }
        Integer x = (int) (vars/4);
        Assert.assertEquals(AlistGet.get(x),listGet.get(x));
    }

    @Test
    public void toArray() {
        System.out.println(vars);

    }

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
}