package MyMethods;

import TestLogFiles.Logger;

import java.util.*;

/**
 * @author: Egbor Osebhulimen
 * @description: Resizable array which can grow based on
 * demand of a user
 * @param <R>
 */

//public class RalphsList<R> implements List<R>{
public class RalphsList<R>{
    private R r; // r must be null
    private int index = -1; // When used set back to -1;
    private Object[] arrayResize = new Object[]{};   // Initializes an array
    private int elemLength =0;    // Initializes a length of array
    private Object []c;

    /////////////   LOGGERS START   //////////////////////
    Logger logger = Logger.LoggerInstance();

    /////////////   LOGGERS END     //////////////////////

    /**
     * Creates an array with your defined length
     * @param length Length on the array created
     */
    public RalphsList(int length){  // Creates  a resizable array with given length
        if (length>0) {
            this.arrayResize = new Object[length];
            this.elemLength = length;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+length);
        }
    }

    /**
     * Creates a default size starting array
     */
    public RalphsList(){ this.arrayResize = new Object[10];}

    /**
     * Creates a resizable array with an array given
     * @param c An array given
     */
    public RalphsList(R[] c){
        if (c.length!=0)
            this.arrayResize = c.clone();
        this.elemLength = c.length;
    }

    /**
     * Creates another resizable list from a List
     * @param list The list you want copied
     */
    public RalphsList(List<R> list){
        this.arrayResize =  list.toArray().clone();
        this.elemLength = list.toArray().length;
    }

    /**
     * Casts an array of Objects to type R
     * @return R[] - Array of type R
     */
    private R[] getArrayAsR(){
        return (R[]) arrayResize;
    }

    /**
     * Gets an element of type R
     * @return R - Element of type R
     */
    private R getElemAsR(int index){
        Objects.checkIndex(index,elemLength);
        return (R) arrayResize[index];
    }

    /**
     * Resets key elements. Used after search
     */
    private void reset(){
        r =null;
        this.index =-1;
    }

    /**
     * Resizes the array by 70%
     */
    private void grow() {
        logger.startTimer();
        int grothFactor = (int)(arrayResize.length +(.7*arrayResize.length));
        c = Arrays.copyOf(arrayResize, grothFactor);
        logger.endTimer();
    }

    // TODO Implement growthFactor advanced
    private int growthFactor(){
        long start = System.nanoTime();
        int length =0;

        long end = System.nanoTime();
        return length;
    }

    /**
     * Checks if size is enough to fit a new batch of items
     */
    private void checkSize(){
        if (elemLength+3 >= arrayResize.length){    // Increases size of array when almost full
            grow();
            arrayResize = c;    // Points to object c the resized array
        }
    }

    /**
     * Searches an array for an element storing the value and index if found
     * @param o R - Object being looked for
     */
    private void searchRand(R o){   // Custom random search
        if (this.elemLength % 2 == 0) { // For even List
            for (int i = 0; i < this.elemLength / 2; i++) {
                if (arrayResize[i].equals(o) || arrayResize[(this.elemLength - 1) - i].equals(o)){
                    if (arrayResize[i].equals(o)){
                        r = getElemAsR(i);
                        index = i;
                    }
                    else {
                        r = getElemAsR((this.elemLength - 1) - i);
                        index = (this.elemLength - 1) - i;
                    }
                    return;
                }
            }
        }
        else { // For an odd List
            for (int i = 0; i < ((this.elemLength ==1) ? elemLength : (this.elemLength /2)); i++) {
                if ((arrayResize[i].equals(o) || arrayResize[i+1].equals(o)) || (arrayResize[this.elemLength -2].equals(o) || arrayResize[(this.elemLength - 1) - i].equals(o))){
                    if (arrayResize[i].equals(o)){
                        r = getElemAsR(i);
                        index = i;
                    } else if (arrayResize[i+1].equals(o)) {
                        r = getElemAsR(i+1);
                        index = i+1;
                    } else if (arrayResize[this.elemLength-2].equals(o)) {
                        r = getElemAsR(this.elemLength-2);
                        index = this.elemLength-2;
                    }else {
                        r = getElemAsR((this.elemLength - 1) - i);
                        index = (this.elemLength-1)-i;
                    }
                    return;
                }
            }
        }
    }

    /**
     * Searches array if element is contained in it
     * @param o Object - Element whose presence in this list is to be tested
     * @return Boolean - True if found
     */

    public boolean contains(R o) {
        searchRand(o);
        if (r!=null){
            reset();
            return true;
        }
        reset();
        return false;
    }

    /**
     * Adds elements to the end of an array
     * @param o R - Element you want to add to the list
     * @return boolean - True if successfully added
     */
    public boolean add(R o) {
        checkSize();
        for (int i = elemLength; i < arrayResize.length; i++) {
            if (arrayResize[i] == null){
                arrayResize[i] = o;
                elemLength++;
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an object to an index position in a list
     * @param index Integer - Defining what position you want your element placed
     * @param o Object - Element you want to add to the list
     * @return Boolean - True if insertion successful
     */
    public boolean add(int index,R o){
        // Throws error when user gives index more than element length
        Objects.checkIndex(index,elemLength);

        checkSize();
        for (int i=elemLength; i>=index; i--){  // Shifts elements to make space for insertion
            arrayResize[elemLength] = arrayResize[index==0 ? 0 : elemLength-1];
        }
        arrayResize[index] = o;
        elemLength++;
        return true;
    }

    /**
     * Adds all element of a given array to the array
     * @param array R - Array you want added to the list
     * @return boolean - True if added successfully
     *
     */
    public boolean addAll(R[] array){
        for (R arrays: array){
            add(arrays);
        }
        return true;
    }

    /**
     * Adds all element of a given List to the array
     * @param list R - Array you want added to the list
     * @return boolean - True if added successfully
     *
     */
    public boolean addAll(List<R> list){
        for (R lists: list){
            add(lists);
        }
        return true;
    }


    /**
     * Returns the size of the list given
     * @return Int - Length of list
     */
    public int size() { return this.elemLength;}

    /**
     * Returns the list in form of an array
     * @return Array - From the list created
     */
    public R[] toArray() {
        return Arrays.copyOf(getArrayAsR(),size());
    }

    /**
     * Finds if the list is empty or not
     * @return Boolean - false if not empty
     */
    public boolean isEmpty() {
        return elemLength == 0;
    }

    /**
     * Returns an element from the list
     * @param index int - Position you want to find an element
     * @return R - The element object
     */
    public R get(int index){
        return getElemAsR(index);
    }

    /**
     * Removes element from  a list
     * @param index int - Position you want deleted
     * @return R - Object that was deleted
     */
    public R remove(int index){
        Objects.checkIndex(index,elemLength);
        r = getElemAsR(index);
       for (int i = index; i < this.elemLength - 1; i++) {
            arrayResize[i] = arrayResize[i+1];
        }
        arrayResize[this.elemLength - 1] = null;
        elemLength--;
        return r;
    }

    /**
     * Removes an element from a list if It's contained in it
     * @param o R - Object you want removed
     * @return boolean - Returns a boolean denoting if the element was found or not
     */
    public boolean remove(R o){
        searchRand(o);
        if (r!=null){
            System.arraycopy(arrayResize, index + 1, arrayResize, index, elemLength - index - 1);
            arrayResize[--elemLength] = null;
            reset();
            return true;
        }else {
            return false;
        }
    }

    /**
     * Clears all elements from the list and resets to default size
     */
    public void clear(){
        elemLength = 0;
        c = new Object[10];
        arrayResize = c;
    }

    /**
     * Trims array to fit data to save space
     */
    public void trimToSize(){
        arrayResize = Arrays.copyOf(arrayResize,elemLength);
    }

    /**
     * Finds index of the first appearance of an element
     * @param o R - Object being searched for
     * @return int - Index position if found or -1 if not
     */
    public int indexOf(R o){
        for (int i = 0; i < elemLength; i++) {
            if (arrayResize[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    // TODO Implement lastIndexOf()  return -1
    /**
     * Finds the first appearance of an element
     * from the end of the array
     * @param o R -The object its searching for.
     * @return int - Index where the object was found
     */
    public int lastIndexOf(R o){
        for (int i=elemLength-1; i>=0; i--){
            if (arrayResize[i].equals(o)){
                return i;
            }
        }
        return -1;
    }



    // TODO Implement sort()
    // TODO Implement removeIf()
    // TODO Implement testEquals()
    // TODO Implement stream()
    // Replaces the element at the specified position in this list with the
    ////     * specified element (optional operation).
    // TODO Implement set()
    //public boolean addAll(int index, Collection<? extends R> c) {
    // TODO Implement addAll()
    // TODO Implement containsAll()
    // TODO Implement subList() Mini -list {new RalphList()}






    ///////////////////////////////////////////////////////////////


}