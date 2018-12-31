/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragments 6.9
 * 
 * An implementation of a simple queue interface.
 * */
public interface Queue<E> {
    
    /**
     * returns the number of elements in queue
     */    int size();
    
    /**
     * Tests whether the queue is empty
     */
    boolean isEmpty();
    
    /**
     * inserts an elmenet at the rear of the queue
     */
    void enqueue(E e);
    
    /**
     * returns but does not remove the first element of the queeue (null if empty)
     */
    E first();
    
    /**
     * Removes and returns the first element of the queue (null if empty)
     */
    E dequeue();
}
