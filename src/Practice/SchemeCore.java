package Practice;

import java.util.Map;

public class SchemeCore {
    /**
     * Represents the empty pair, also known as the empty list.
     */
    public static final Pair nil = new Pair(null, null);

    /**
     * Returns an instance of the {@link ca.dal.csci3137.Symbol} class with the given name.
     *
     * @param name The name of the symbol to be returned.
     * @return an instance of the {@link ca.dal.csci3137.Symbol} class with the given name.
     */
    public static Symbol symbol(String name) {
        return new Symbol(name);
    }

    /**
     * Returns an instance of the {@link ca.dal.csci3137.Pair} class with the given ar and dr.
     *
     * @param ar The first element of the pair. (Called the address part of the register for historical reasons.)
     * @param dr The second element of the pair. (Called the decrement part of the register for historical reasons.)
     * @return an instance of the {@link ca.dal.csci3137.Pair} class with the given ar and dr.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#cons">Scheme's cons function</a>
     */
    public static Pair cons(Object ar, Object dr) {
        return new Pair(ar, dr);
    }

    /**
     * Returns the contents of the address part of the register for the given pair.
     * <p>
     * Throws an exception when given the empty pair {@link ca.dal.csci3137.SchemeCore#nil} or an instance of any class
     * other than {@link ca.dal.csci3137.Pair}
     *
     * @param pair the given pair (should be an instance of {@link ca.dal.csci3137.Pair})
     * @return the contents of the address part of the register for the given pair.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#car">Scheme's car function</a>
     */
    public static Object car(Object pair) {
        if (pair instanceof Pair p) {
            if (p == nil || p.ar == nil) {
                throw new NullPointerException("Object passed in was nill");
            }
            return p.ar;
        } else {
            throw new IllegalArgumentException("Object passed in was not an instance of pair");
        }
    }

    /**
     * Returns the contents of the decrement part of the register for the given pair.
     * <p>
     * Throws an exception when given the empty pair {@link ca.dal.csci3137.SchemeCore#nil} or an instance of any class
     * other than {@link ca.dal.csci3137.Pair}
     *
     * @param pair the given pair (should be an instance of {@link ca.dal.csci3137.Pair})
     * @return the contents of the decrement part of the register for the given pair.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#cdr">Scheme's cdr function</a>
     */
    public static Object cdr(Object pair) {
        if (pair instanceof Pair p) {
            if (p == nil || p.dr == nil) {
                throw new NullPointerException("Object passed in was nill");
            }
            return p.dr;
        } else {
            throw new IllegalArgumentException("Object passed in was not an instance of pair");
        }
    }

    /**
     * Returns a proper list constructed from the given items.
     * <p>
     * A proper list is either the empty pair {@link ca.dal.csci3137.SchemeCore#nil} (sometimes called the empty list)
     * or a pair whose car is the first element of the list (i.e. head) and whose cdr is itself a proper list containing
     * the remaining elements of the list (i.e. the tail).
     *
     * @param items the array of items to be included in the proper list.
     * @return a proper list constructed from the given items.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#list">Scheme's list function</a>
     */
    public static Pair list(Object... items) {
        if (items.length == 0) return SchemeCore.nil;
        final Object[] remainingItems = new Object[items.length - 1];
        System.arraycopy(items, 1, remainingItems, 0, items.length - 1);
        return new Pair(items[0], list(remainingItems));
    }

    /**
     * A predicate that determines if its argument is a proper list (see {@link ca.dal.csci3137.SchemeCore#list(Object...)}.
     *
     * @param o the object to be tested for proper list-hood.
     * @return true if o is a proper list, false otherwise.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#proper-list-p">Scheme's proper-list? function</a>
     */
    public static boolean isList(Object o) {
        if (o instanceof Pair p) {
            if (p == nil) return true;
            if (p.ar == nil) {
                return false;
            }
            if (p.dr == nil) {
                return true;
            }
            return isList(p.dr);
        } else return false;

    }

    /**
     * Returns the number of elements in a proper list (see {@link ca.dal.csci3137.SchemeCore#list(Object...)}.
     * <p>
     * Throws an exception if the given list is not a proper list.
     *
     * @param list the given list.
     * @return the number of elements in the given list.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#length">Scheme's length function</a>
     */
    public static int length(Object list) {
        if (!isList(list))
            throw new AssertionError("Object passed in is not  a list");

        return lengthHelp((Pair) list);
    }

    private static int lengthHelp(Pair list) {
        if (list == nil) return 0;
        return 1 + lengthHelp((Pair) list.dr);
    }

    /**
     * Performs the zip operation on a given array of proper lists.
     * <p>
     * If zip is given <i>n</i> lists, it returns a list as long as the shortest of these lists, each element of which
     * is itself, an <i>n</i>-element list comprised of the corresponding elements from the given <i>n</i> lists.
     *
     * @param lists the array of lists to be zipped.
     * @return the result of the zip operation.
     * @see <a href="https://srfi.schemers.org/srfi-1/srfi-1.html#zip">Scheme's zip function</a>
     */
    public static Pair zip(Object... lists) {
        Pair zippedHead = zipHeadT(0, lists);
        if (zippedHead == null) return nil;

        Object[] zippedTail = new Object[lists.length];
        zipTail(0, zippedTail, lists);
        return new Pair(zippedHead, zip(zippedTail));
    }

    private static void zipTail(int idx, Object[] zipTail, Object... items) {
        if (idx == zipTail.length) return;
        zipTail[idx] = ((Pair) items[idx]).dr;
        zipTail(idx + 1, zipTail, items);
    }

    private static Pair zipHeadT(int idx, Object... items) {
        if (idx == items.length) return nil;
        Pair pair = (Pair) items[idx];
        if (pair == nil) return null; // Un-EvenLengths
        Pair innerPair = zipHeadT(idx + 1, items);
        if (innerPair == null) return null; // Stop zips for un-even lengths pairs
        return new Pair(pair.ar, innerPair);
    }

    public static Object eval(Object expression, Map<Symbol, Object> referencingEnvironment) {
        boolean bypass_procedure = false;
        return eval_bypass(expression, referencingEnvironment, bypass_procedure);
    }

    static Object eval_bypass(Object expression, Map<Symbol, Object> referencingEnvironment, boolean bypass_procedure) {
        if (expression instanceof Symbol s) {
            Object res = referencingEnvironment.get(expression);
            if (res != null) return res;
            throw new IllegalArgumentException("Symbol: " + s + " is not in the referencing environment");
        } else if (expression instanceof Pair pair) {
            Object eval = eval(pair.ar, referencingEnvironment);
            // DEFINE
            if (eval == null) {
                return eval(getAR_Dr(pair), referencingEnvironment);
            }
            if ((eval instanceof Procedure procedure)) {
                return procedure.apply((Pair) pair.dr, referencingEnvironment);
            }
            if (bypass_procedure) {
                return eval;
            }
            throw new IllegalStateException("Evaluating ar: " + pair.ar + " did not result in a procedure");
        } else return expression;
    }

    public static Object getAR_Dr(Pair arguments) {
        return ((Pair) arguments.dr).ar;
    }
}
