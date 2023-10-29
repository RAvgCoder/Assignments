package Assignment3C2110;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T>
{
	//attributes
	private Node<T> front;
	private int count;
	
	//constructor
	public LinkedList()
	{
		front = null;
		count=0;
	}
	public Node<T> getFront()
	{
		return front;
	}
	
	//adds an item to the front of the linked list
	public void add(T item)
	{
		Node<T> newNode = new Node<T>(item, front);
		front = newNode;
		count++;
	}
	
	//returns the current size of the linked list
	public int size()
	{
		return count;
	}
	
	//clears the linked list
	public void clear()
	{
		front = null;
		count=0;
	}
	
	//returns true if the linked list is empty
	public boolean isEmpty()
	{
		return (count==0);
	}
	
	//scans the linked list and prints the data
	public void enumerate()
	{
		Node<T> curr = front;
	
		while (curr!=null)
		{
			System.out.print(curr.getData() + "--> ");
			curr = curr.getNext();
		}
		
	}
	
	//returns the data at a given index
	public T getAt(int index)
	{
		Node<T> curr = front;
		if (index<0||index>=count)
		{
			System.out.println("Error. Index out of bounds");
			return null;
		}
		else
		{
			
			for(int i=0; i<index; i++)
				curr = curr.getNext();
			return curr.getData();
		}			

	}
	
	//inserts an item at a given index
	public void insertAt(T item, int index)
	{
		if (index<0||index>count)
		{
			System.out.println("Can't insert. Index out of bounds.");
			System.exit(0);
		}
		else
		{
			if (index==0)
			{
				add(item);
				return;
			}
			Node<T> prev = front;
			for(int i=0;i<index-1;i++)
				prev = prev.getNext();
			Node<T> itemnode = new Node<T>(item,prev.getNext());
			prev.setNext(itemnode);
			count++;
		}
	}
	
	//sets the data at a node at a given index
	public void setAt(T item, int index)
	{
		if (index<0||index>=count)
		{
			System.out.println("Can't set. Index out of bounds");
			System.exit(0);
		}
		else
		{
			Node<T> curr = front;
			for(int i=0;i<index;i++)
				curr = curr.getNext();
			curr.setData(item);
		}
	}
	
	//returns the index of the first occurrence of a given item, -1 if not found
	public int indexOf(T item)
	{
		Node<T> curr = front;
		for(int i=0;i<count;i++)
		{
			if (item.equals(curr.getData()))
				return i;
			curr = curr.getNext();
		}
		return -1;
	}
	
	//removes the node at a given index
	public T removeAt(int index)
	{
		T result = null;
		if (index<0||index>=count)
		{
			System.out.println("Can't remove. Index out of bounds");
			System.exit(0);
		}
		else
		{
			
			if (index==0)
			{
				result = front.getData();
				front = front.getNext();
			}
			else
			{
				Node<T> prev = front;
				for(int i=0;i<index-1;i++)
					prev = prev.getNext();
				result=prev.getNext().getData();
				prev.setNext(prev.getNext().getNext());
					
			}
			count--;
		}
		return result;
	}
	
	//removes the node containing the first occurrence of a given item
	public void remove (T item)
	{
		int i = indexOf(item);
		if (i==-1)
		{
			System.out.println("No such item");
			System.exit(0);
		}
		else
			removeAt(i);
	}
	
	//Removes all nodes containing a given item
	//Does it in one scan (O(n))
	public void removeAll(T item)
	{
		
		Node<T> curr=front, prev=null;
		
		while (curr!=null)
		{
			if (front.getData().equals(item))
			{
				front = front.getNext();
				curr = curr.getNext();
				count--;
			}
			else if (curr == front)
			{
				prev = curr;
				curr = curr.getNext();
			}
			else
			{
				if (curr!=null)
				{
					if (curr.getData().equals(item))
					{
						prev.setNext(curr.getNext());
						curr= prev.getNext();
						count--;
					}
					else
					{
					prev = curr;
					curr = curr.getNext();
					}
				}
			}
		}
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@NotNull
	@Override
	public Iterator<T> iterator() {
		return new LinkedIterator();
	}

	/**
	 * Performs the given action for each element of the {@code Iterable}
	 * until all elements have been processed or the action throws an
	 * exception.  Actions are performed in the order of iteration, if that
	 * order is specified.  Exceptions thrown by the action are relayed to the
	 * caller.
	 * <p>
	 * The behavior of this method is unspecified if the action performs
	 * side-effects that modify the underlying source of elements, unless an
	 * overriding class has specified a concurrent modification policy.
	 *
	 * @param action The action to be performed for each element
	 * @throws NullPointerException if the specified action is null
	 * @implSpec <p>The default implementation behaves as if:
	 * <pre>{@code
	 *     for (T t : this)
	 *         action.accept(t);
	 * }</pre>
	 * @since 1.8
	 */
	@Override
	public void forEach(Consumer<? super T> action) {
		Iterable.super.forEach(action);
	}

	final class LinkedIterator implements Iterator<T> {
		Node<T> curr = front;

		/**
		 * Returns {@code true} if the iteration has more elements.
		 * (In other words, returns {@code true} if {@link #next} would
		 * return an element rather than throwing an exception.)
		 *
		 * @return {@code true} if the iteration has more elements
		 */
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		/**
		 * Returns the next element in the iteration.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iteration has no more elements
		 */
		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException("Reached end of iteration");
			Node<T> ret = curr;
			curr = curr.getNext();
			return ret.getData();
		}

		/**
		 * Removes from the underlying collection the last element returned
		 * by this iterator (optional operation).  This method can be called
		 * only once per call to {@link #next}.
		 * <p>
		 * The behavior of an iterator is unspecified if the underlying collection
		 * is modified while the iteration is in progress in any way other than by
		 * calling this method, unless an overriding class has specified a
		 * concurrent modification policy.
		 * <p>
		 * The behavior of an iterator is unspecified if this method is called
		 * after a call to the {@link #forEachRemaining forEachRemaining} method.
		 *
		 * @throws UnsupportedOperationException if the {@code remove}
		 *                                       operation is not supported by this iterator
		 * @throws IllegalStateException         if the {@code next} method has not
		 *                                       yet been called, or the {@code remove} method has already
		 *                                       been called after the last call to the {@code next}
		 *                                       method
		 * @implSpec The default implementation throws an instance of
		 * {@link UnsupportedOperationException} and performs no other action.
		 */
		@Override
		public void remove() {
			Iterator.super.remove();
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
		public void forEachRemaining(Consumer<? super T> action) {
			while (hasNext()) {
            	action.accept(next());
        	}
		}
	}

}
