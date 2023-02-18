package MyMethods;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author: Egbor Osebhulimen
 * @description: Resizable array which can grow based on
 * demand of a user
 * @param <R>
 */

public class RalphsList<R> implements Iterable{
    private R r; // r must be null
    private int index = -1; // When used set back to -1;
    private Object[] arrayResize = new Object[]{};   // Initializes an array
    private int elemLength =0;    // Initializes a length of array

    private Object []c;
    private final String errorArrayOutOfBounds = "Index %d out of bounds for length %d";

    /**
     * Creates a default size starting array
     */
    public RalphsList(){ this.arrayResize = new Object[10];}

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
        checkValidIndex(index);
        return (R) arrayResize[index];
    }

    /**
     * Checks Throws error when user gives index more than element length
     * @param index Integer - Position to be placed at
     */
    private void checkValidIndex(int index) throws ArrayIndexOutOfBoundsException{
        if (index>=elemLength || index<0) {
            throw new ArrayIndexOutOfBoundsException(String.format(errorArrayOutOfBounds, index, elemLength));
        }
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
     * @param length int - Length of the current array
     * @param arrayResize Object - Array you want resized;
     */
    private void grow(int length, Object[] arrayResize) {
        int grothFactor = (int) (length + (.7 * length));
        c = Arrays.copyOf(arrayResize, grothFactor);
    }

    /**
     * Checks if size is enough to fit a new batch of items
     */
    private void checkSize(){
        if (elemLength+3 >= arrayResize.length){    // Increases size of array when almost full
            grow(arrayResize.length,arrayResize);
            arrayResize = c;    // Points to object c the resized array
        }
    }

    /**
     * Searches an array for an element storing the value and index if found
     * @param o R - Object being looked for
     */
    private void searchRand(R o){
        if (this.elemLength % 2 == 0) { // For even List
            for (int i = 0; i < this.elemLength / 2; i++) {
                if (arrayResize[i].equals(o) || arrayResize[(this.elemLength - 1) - i].equals(o)){
                    if (arrayResize[i].equals(o)){
                        r = getElemAsR(i);
                        index = i;
                    }else {
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
                        if (arrayResize[i]==o){
                            r = getElemAsR(i);
                            index = i;
                        } else if (arrayResize[i+1]==o) {
                            r = getElemAsR(i+1);
                            index = i+1;
                        } else if (arrayResize[this.elemLength-2]==o) {
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
        checkValidIndex(index);

        checkSize();
        for (int i=elemLength; i>=index; i--){  // Shifts elements to make space for insertion
            arrayResize[elemLength] = arrayResize[index==0 ? 0 :elemLength-1];
        }
        arrayResize[index] = o;
        elemLength++;
        return true;
    }

    /**
     * Adds all element of an array to the list
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
        checkValidIndex(index);
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
     * Finds index of an element
     * @param o R - Object being searched for
     * @return int - Index position if found or -1 if not
     */
    // TODO Fix IndexOf broken
    public int indexOf(R o){
        for (int i = 0; i < elemLength; i++) {
            if (arrayResize[i]==o){
                return i;
            }
        }
        return -1;
    }

    // TODO Implement lastIndexOf()  return -1
    public int lastIndexOf(R o){
        for (int i=elemLength-1; i>=0; i--){
            if (arrayResize[i]==o){
                return i;
            }
        }
        return -1;
    }

    // TODO Implement sort()
    // TODO Implement growthFactor advanced





    ///////////////////////////////////////////////////////////////

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<R> iterator() {
        return new ArrIterator();
    }

    /**
     * @author Egbor Osebhulimen
     * @date 2023-02-16
     * @desciption Class used to create an Iterator----------
     */
    private class ArrIterator implements Iterator<R>{
        private int index;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return index<elemLength;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public R next() {
            return (R) arrayResize[index++];
        }

        /**
         * Performs the given action for each remaining element until all elements
         * have been processed or the action throws an exception.  Actions are
         * performed in the order of iteration, if that order is specified.
         * Exceptions thrown by the action are relayed to the caller.
         * <p>
         * The behavior of an iterator is unspecified if the action modifies the
         * collection in any way (even by calling the {@link #remove remove} method
         * or other mutator methods of {@code Iterator} subtypes),
         * unless an overriding class has specified a concurrent modification policy.
         * <p>
         * Subsequent behavior of an iterator is unspecified if the action throws an
         * exception.
         *
         * @param action The action to be performed for each element
         * @throws NullPointerException if the specified action is null
         * @implSpec <p>The default implementation behaves as if:
         * <pre>{@code
         *     while (hasNext())
         *         action.accept(next());
         * }</pre>
         * @since 1.8
         */
        @Override
        public void forEachRemaining(Consumer<? super R> action) {
            Iterator.super.forEachRemaining(action);
        }
    }


}
