package CusMeth;

import CusMeth.LinkedListM;

public class StacksM<T> extends LinkedListM<T> {
    T t;
    public StacksM(T data)
    {
        super(data);
    }

    public void push(T data)
    {
        super.add(data);
    }

    public T pop()
    {
        return super.removeLast();
    }
}
