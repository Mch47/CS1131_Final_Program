import java.util.ArrayList;

/**
 * A simple interface for binary trees.  An empty binary tree is
 * represented with the value null; a non-empty tree by its root
 * node.
 */
 public interface BinaryTreeNode<E> {

    /**
     * Returns the data stored in this node.
     */
    public E getData();

    /**
     * Modifies the data stored in this node.
     */
    public void setData(E data);

   /**
    * Returns the ancestor of this node that has no parent,
    * or returns this node if it is the root.
    */
   public BinaryTreeNode<E> getRoot();

   /**
    * Returns the parent of this node, or null if this node is a root.
    */
   public BinaryTreeNode<E> getParent();

   /**
    * Sets the parent node of the current node.
    */
   public void setParent(BinaryTreeNode<E> parent);

   /**
     * Returns the left child of this node, or null if it does
     * not have one.
     */
    public BinaryTreeNode<E> getLeft();

    /**
     * Removes child from its current parent and inserts it as the
     * left child of this node.  If this node already has a left
     * child it is removed.
     */
    public void setLeft(BinaryTreeNode<E> child);

    /**
     * Returns the right child of this node, or null if it does
     * not have one.
     */
    public BinaryTreeNode<E> getRight();

    /**
     * Removes child from its current parent and inserts it as the
     * right child of this node.  If this node already has a right
     * child it is removed.
     */
    public void setRight(BinaryTreeNode<E> child);

   /**
    * Returns true if the node has any children.
    * Otherwise, returns false.
    */
   public boolean isParent( );

   /**
    * Returns true if the node is childless.
    * Otherwise, returns false.
    */
   public boolean isLeaf( );

   /**
    * Returns true if the node has a left child
    */
   public boolean hasLeftChild( );

   /**
    * Returns true if the node has a right child
    */
   public boolean hasRightChild( );

   /**
    * Returns the number of edges in the path from the root to this node.
    */
   public int getDepth( );

   /**
    * Returns the number of edges in the path from the root
    * to the node with the maximum depth.
    */
   public int getHeight( );

   /**
    * Returns the number of nodes in the subtree rooted at this node.
    */
   public int size( );

   /**
     * Removes this node, and all its descendants, from whatever
     * tree it is in.  Does nothing if this node is a root.
     */
    public void removeFromParent();

   /**
    * Returns the path from this node to the specified descendant.
    * If no path exists, returns an empty list.
    */
   public ArrayList<BinaryTreeNode<E>> pathTo( BinaryTreeNode<E> descendant );

   /**
    * Returns the path to this node from the specified ancestor.
    * If no path exists, returns an empty list.
    */
   public ArrayList<BinaryTreeNode<E>> pathFrom( BinaryTreeNode<E> ancestor );

   /**
     * Visits the nodes in this tree in preorder.
     */
    public void traversePreorder(Visitor visitor);

    /**
     * Visits the nodes in this tree in postorder.
     */
    public void traversePostorder(Visitor visitor);

    /**
     * Visits the nodes in this tree in inorder.
     */
    public void traverseInorder(Visitor visitor);

    /**
     * Simple visitor interface.
     */
    public interface Visitor {
       public void visit(BinaryTreeNode node);
    }
}