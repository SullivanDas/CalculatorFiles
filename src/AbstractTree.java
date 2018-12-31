
import java.util.ArrayList;
import java.util.List;


/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 8.2, 8.3, 8.4, 8.5
 */
public abstract class AbstractTree<E> implements Tree<E>{
    /**
     * checks if a position is internal of the tree
     * 
     * @param p the position to check to see if internal
     * @return returns true if p is internal
     */
    public boolean isInternal(Position<E> p){
        return numChildren(p) > 0;
    }
    
    /**
     * checks to see if a position is external on the tree
     * 
     * @param p the position to check if external
     * @return returns true if p is external
     */
    public boolean isExternal(Position<E> p){
        return numChildren(p) ==0;
    }
    
    /**
     * checks to see if a given position is the root
     * 
     * @param p the position to check for root
     * @return returns true if p is the root
     */
    public boolean isRoot(Position<E> p){
        return p == root();
    }
    
    /**
     * checks to see if the tree is empty
     * 
     * @return returns true if the size of the tree is empty
     */
    public boolean isEmpty(){
        return size() == 0;
    }
    
    /**
     * finds the depth of the tree to the position
     * 
     * @param p the position to check depth against
     * @return returns the depth of the position p recursively
     */
    public int depth(Position<E> p){
        if(isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }
    
    /**
     * finds the height between the root and a given position p
     * 
     * @param p the position to find the height of
     * @return returns the height of p from the root
     */
    public int height(Position<E> p){
        int h = 0;
        for(Position<E> c : children(p)){
            h = Math.max(h, 1+height(c)); 
        }
        
        return h;
    }
    
        /**
     * returns an iterable collection of positions of the tree in breadth-first order
     */
    public Iterable<Position<E>> breadthFirst(){
        List<Position<E>> snapshot = new ArrayList();
        if(!isEmpty()){
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());
            
            //start with the root
            while(!fringe.isEmpty()){
                Position<E> p = fringe.dequeue(); 
                //System.out.println("dequed: " + p.getElement());//remove from front of the queue
                snapshot.add(p);
                for(Position<E> c : children(p)){
                    fringe.enqueue(c);                                          //add children to back of queue
                }
            }
            
        }
        return snapshot;    
    }
    
}
