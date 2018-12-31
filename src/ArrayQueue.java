/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragments 6.10
 * 
 * An implementation of a simple ArrayQueue class.
 * */
public class ArrayQueue<E> implements Queue<E> {

    public static final int CAPACITY = 1000;   
    private E[] data;
    private int front = 0;
    private int size = 0;
    
    public ArrayQueue(){
        this(CAPACITY);
    }
    public ArrayQueue(int capacity){
        data = (E[]) new Object[capacity];
        
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void enqueue(E e) throws IllegalStateException{
        if (size == data.length) throw new IllegalStateException("Queue is full");
        int avail = (front + size) % data.length;
        data[avail] = e;
        size++;
        
    }

    @Override
    public E first() {
        if(isEmpty()) return null;
        return data[front];
    }

    @Override
    public E dequeue() {
        if(isEmpty()) return null;
        E answer = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return answer;
    }
    
    @Override
    public String toString(){
        String contents = "";
        for(E e : data){
            contents += e + " ";
        }
        
        return getClass().getName() + contents;
    }
    
}
