
/**
 * the singly linked list data structure class
 * 
 * @author David Sullivan
 * @Version 9/13/2018
 */
public class SinglyLinkedList<E> {
    
    private static class Node<E>{
        private E element;
        private Node<E> next;
        
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext(){
            return next;
        }
        
        public void setNext(Node<E> n){
            next = n;
        }
    }
           
    private Node<E> head;
    private Node<E> tail;
    private int count;
        
    public SinglyLinkedList(){
        head = null;
        tail = null;
        count = 0;
    }
    public int getCount(){
        return count;
    }
    
    public boolean isEmpty(){
        return count==0;
    }
    
    public E getFirst(){
        if(isEmpty()) return null;
        return head.getElement();
    }
    
    public E getLast(){
        if(isEmpty()) return null;
        return tail.getElement();
    }
    
    public void addFirst(E e){
        head = new Node<>(e, null);
        if(isEmpty()){
            tail = head;
        }
        count++;
    }
    
    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if(isEmpty()){
            head = newest;
        }
        else{
            tail.setNext(newest);
        }
        
        tail = newest;
        count++;
    }
    
    public E removeFirst(){
        if(isEmpty()) return null;
        
        E answer = head.getElement();
        head = head.getNext();
        count--;
        if(isEmpty()){
            tail = null;
        }
        return answer;
    }
    
    


}
