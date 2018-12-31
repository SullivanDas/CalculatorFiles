

/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 8.6
 */
public interface BinaryTree<E> extends Tree<E> {
    
    Position<E> left(Position<E> p) throws IllegalArgumentException;
    
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
