
/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 8.7
 */
import java.util.List;
import java.util.ArrayList;
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    @Override
    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if(parent == null) return null;                                         //p is the root
        if(p==left(parent))                                                     //p is a left child
            return right(parent);                                               //right child may be null
        else                                                                    //p is a right child
            return left(parent);                                                //left child may be null
        
    }
    
    @Override
    public int numChildren(Position<E> p){
        int count = 0;
        if(left(p) != null)
            count++;
        if(right(p) != null)
            count++;
        return count;
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p){
        List<Position<E>> snapshot = new ArrayList<>(2);                        //max capacity of 2
        if(left(p) != null){
            snapshot.add(left(p));
        }
        if(right(p) != null){
            snapshot.add(right(p));
        }
        
        return snapshot;
    }
}
