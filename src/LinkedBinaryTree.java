
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 8.8, 8.9, 8.10,8.11
 */
import java.util.List;
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {


    protected static class Node<E> implements Position<E>{
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        
        /**
         * constructs a node with the given element and neighbors
         * 
         * @param e the element of the node
         * @param above the node that is parent to this node
         * @param leftChild the node that is the left child of this node
         * @param rightChild the node that is the right child of this node
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild){
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }
        
        //accsesor methods
        @Override
        public E getElement(){ return element;}
        public Node<E> getParent(){ return parent;}
        public Node<E> getLeft(){return left;}
        public Node<E> getRight(){ return right; }
        
        //mutator methods
        public void setElement(E e){ element = e;}
        public void setParent(Node<E> parentNode) { parent = parentNode; }
        public void setLeft(Node<E> leftChild) { left = leftChild; }
        public void setRight(Node<E> rightChild) { right = rightChild; }
        
        
    }
    // function to create a new node storing element e
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<>(e, parent, left, right);
    }
    
    //linkedBinaryTree instance variables
    protected Node<E> root = null;
    private int size = 0;
    
    //constructor
    public LinkedBinaryTree(){
        
    }
    
    //nonpublic utility that validates the position and returns it as a node
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException{
        if(!(p instanceof Node))
            throw new IllegalArgumentException("Not a valid position type");
        Node<E> node = (Node<E>) p;                                             //safe cast
        if(node.getParent() == node)                                            //defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
            
        return node;
    }
    
    //accessor methods
    
    /**
     * @return returns the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * @return returns the root position of the tree (null if empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * gets the parent of position p
     * @param p the position to get the parent of
     * @return returns the position of the parent of p (null if p is root)
     * @throws IllegalArgumentException 
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * gets the left child (null if no such child exists)
     * @param p the position to check for children
     * @return returns the position of the left child
     * @throws IllegalArgumentException 
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * gets the right child (null if no such child exists)
     * @param p the position to check for children
     * @return returns the position of the right child
     * @throws IllegalArgumentException 
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }
    
    /**
     * adds a root node to the tree
     * 
     * @param e the element to assign to the root
     * @return returns the root to created
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException{
        if(!isEmpty()) throw new IllegalStateException("Tree is not Empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    
    /**
     * creates a left child of a parent node
     * 
     * @param p the parent to create a left child for
     * @param e the element to assign to the child
     * @return returns the created child
     * @throws IllegalStateException when p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
        
        Node<E> parent = validate(p);
        if(parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }
    
     /**
     * creates a right child of a parent node
     * 
     * @param p the parent to create a right child for
     * @param e the element to assign to the child
     * @return returns the created child
     * @throws IllegalStateException when p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException{
        
        Node<E> parent = validate(p);
        if(parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }
    
    /**
     * sets a position to a specific element
     * 
     * @param p the position to change
     * @param e the element to replace with
     * @return returns the old element
     * @throws IllegalArgumentException 
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    /**
     * attaches t1 and t2 as a subtree of p
     * 
     * @param p the parent to attach t1 and t2 to
     * @param t1 the first tree (left)
     * @param t2 the second tree (right)
     * @throws IllegalArgumentException 
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException{
        
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        
        //attach t1 as left subtree of node
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        
        //attach t2 as right subtree of node
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }
    
    /**
     * removes a position from the tree
     * @param p the position to remove from the tree
     * @return returns the removed node
     * @throws IllegalArgumentException 
     */
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if(child != null)
            child.setParent(node.getParent());                                  //child's grandparent becomes its parent
        if(node == root)
            root = child;                                                       //child becomes the root
        else{
            Node<E> parent = node.getParent();
            if(node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        
        size--;
        E temp = node.getElement();
        node.setElement(null);                                                  //help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);                                                   //defunct node
        return temp;
        
    }
    
    //---------------- nested ElementIterator class -------------------
    // uses positions() to return elements
    private class ElementIterator implements Iterator<E>{

        Iterator<Position<E>> posIterator = positions().iterator();
        
        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }
        
        @Override
        public void remove(){
            posIterator.remove();
        }
        
    }
    //---------------- end of nested class -----------------------
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * adds positions of the subtree rooted at Position p to the given snapshot
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        for(Position<E> c : children(p)){
            preorderSubtree(c, snapshot);
        }
    }
    
    /**
     * returns an iterable collection of positions of the tree, reported in preorder
     */
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    /**
     * adds positions of the subtree at Position p to the given snapshot
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
        for(Position<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p);
    }
    
    /**
     * returns an iterable collection of positions of the tree, reported in postorder
     */
    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            postorderSubtree(root(), snapshot);
        return snapshot;
    }
    
    /**
     * adds positions of the subtree rooted at Position p to the given snapshot
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if(left(p)!= null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if(right(p) != null)
            inorderSubtree(right(p), snapshot);
    } 

    /**
     * Returns an iterable collection of positions of the tree, reported in inorder
     */
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList();
        if(!isEmpty()){
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }
    
    /**
     * Overrides positions to make inorder the default for binary trees
     */
    @Override
    public Iterable<Position<E>> positions(){
        return inorder();
    }
    
    
}
