/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragments 6.11
 * 
 * An implementation of a simple LinkedQueue class.
 * */

public class LinkedQueue<E> implements Queue<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedQueue(){ }
    
    @Override
    public int size() {
        return list.getCount();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.getFirst();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
    
}
