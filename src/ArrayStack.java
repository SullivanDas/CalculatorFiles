/**
 * Data Structures & Algorithms 6th Edition 
 * Goodrick, Tamassia, Goldwasser
 * Code Fragments 6.4
 * 
 * An implementation of a simple ArrayStack class.
 * */
public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;                                    //generic array capacity
    private E[] data;                                                           //generic array used for storage
    private int top = 0;                                                        //index of the top element in the stack
    
    public ArrayStack(){                                                        //constructs stack with default capacity
        this(CAPACITY);
    }
    
    public ArrayStack(int capacity){                                            //constructs stack with given capacity
        data = (E[]) new Object[capacity];                                      //safe cast compiler may give warning
    }
    
    
    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top ==0;
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if(top == data.length) throw new IllegalStateException("Stack is full");
        data[top] = e;
        top++;                                                                  //Increment t before storing new item
    }

    @Override
    public E top() {
        if(isEmpty()) return null;
        return data[top-1];
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;
        E answer = data[top-1];                                                   //Dereference to help Garbage collection
        data[top-1] = null;
        top--;
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
