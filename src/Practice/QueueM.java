package Practice;

public class QueueM<T> extends LinkedListM<T>{
    private T t;
    public QueueM(T data)
    {
        super(data);
    }

    public void add(T data)
    {
        super.append(data);
    }

    public T poll()
    {
        return super.removeFirst();
    }

    public T peek()
    {
        return super.getFirst();
    }

}
