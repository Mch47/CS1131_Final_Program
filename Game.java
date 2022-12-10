public interface Game {
	/**
	 * Loads a BinaryTree file that has been saved as a preorder traversal.
	 * Interior nodes are prefixed by "Q:".
	 * Leaf nodes are prefixed by "G:"
	 * @param filename
	 * @return the root node
	 */
	public BinaryTreeNode<String> loadTree( String filename );

	/**
	 * Saves the tree to the specified file.
	 * The tree is saved in a preorder fashion.
	 * Interior nodes are prefixed by "Q:".
	 * Leaf nodes are prefixed by "G:"
	 * @param filename
	 */
	public void saveTree( String filename );

	/**
	 * Returns the root of the game tree.
	 * @return the root node of the tree
	 */
	public BinaryTreeNode<String> getRoot( );

	/**
	 * Starts the guessing game.
	 */
	public void play( );
}
