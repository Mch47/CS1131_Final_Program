import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class LinkedBinaryTreeNodeTest {

   private boolean fuzzyEquals( double a, double b ) {
      return ( a - Math.ulp( a ) ) <= b && b <= ( a + Math.ulp( a ) ) ||
           ( ( ( int ) Math.round( b * 100000.0 ) ) == ( ( int ) Math.round( a * 100000.0 ) ) )
//           || ( ( ( int ) b ) == ( ( int ) a ) )
           ;
   }

   @Test
   public void testInterface( ) {
      LinkedBinaryTreeNode< String > node = new LinkedBinaryTreeNode< String >( "test" );
      if ( !( node instanceof BinaryTreeNode ) ) {
         fail( String.format( "LinkedBinaryTreeNode did not implement the BinaryTreeNode interface" ) );
      }
   }

   @Test
   public void testGetData( ) throws Exception {
      String solution = "Beware the Shrem!";
      LinkedBinaryTreeNode< String > node = new LinkedBinaryTreeNode< String >( solution );
      if ( !solution.equals( node.getData( ) ) ) {
         fail( String.format( "(%s).getData() returned \"%s\" should be \"%s\"", node, node.getData( ), solution ) );
      }
      double solution1 = 3.1415926;
      LinkedBinaryTreeNode< Double > node1 = new LinkedBinaryTreeNode< Double >( solution1 );
      if ( !fuzzyEquals( solution1, node1.getData( ) ) ) {
         fail( String.format( "(%s).getData() returned %s should be %s", node1, node1.getData( ), solution ) );
      }
      int solution2 = 123456;
      LinkedBinaryTreeNode< Integer > node2 = new LinkedBinaryTreeNode< Integer >( solution2 );
      if ( solution2 != node2.getData( ) ) {
         fail( String.format( "(%s).getData() returned %s should be %s", node2, node2.getData( ), solution ) );
      }
   }

   @Test
   public void testSetData( ) throws Exception {
      String solution = "Beware the Shrem!";
      LinkedBinaryTreeNode< String > node = new LinkedBinaryTreeNode< String >( "" );
      node.setData( solution );
      if ( !solution.equals( node.getData( ) ) ) {
         fail( String.format( "(%s).getData() returned \"%s\" should be \"%s\"", node, node.getData( ), solution ) );
      }
      double solution1 = 3.1415926;
      LinkedBinaryTreeNode< Double > node1 = new LinkedBinaryTreeNode< Double >( 0.0 );
      node1.setData( solution1 );
      if ( !fuzzyEquals( solution1, node1.getData( ) ) ) {
         fail( String.format( "(%s).getData() returned %s should be %s", node1, node1.getData( ), solution ) );
      }
      int solution2 = 123456;
      LinkedBinaryTreeNode< Integer > node2 = new LinkedBinaryTreeNode< Integer >( 0 );
      node2.setData( solution2 );
      if ( solution2 != node2.getData( ) ) {
         fail( String.format( "(%s).getData() returned %s should be %s", node2, node2.getData( ), solution ) );
      }
   }

   @Test
   public void testIsParent( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > child = new LinkedBinaryTreeNode< String >( "Child" );
      LinkedBinaryTreeNode< String > grandChild = new LinkedBinaryTreeNode< String >( "Grandchild" );
      if ( parent.isParent( ) ) {
         fail( String.format( "(%s).isParent() returned true before adding any children", parent ) );
      }
      parent.setLeft( child );
      if ( !parent.isParent( ) ) {
         fail( String.format( "(%s).isParent() returned false left child should be %s", parent, child ) );
      }
      if ( child.isParent( ) ) {
         fail( String.format( "(%s).isParent() returned true before adding any children", child ) );
      }
      child.setRight( grandChild );
      if ( !parent.isParent( ) ) {
         fail( String.format( "(%s).isParent() returned false left child should be %s", parent, child ) );
      }
      if ( !child.isParent( ) ) {
         fail( String.format( "(%s).isParent() returned false right child should be %s", child, grandChild ) );
      }
      if ( grandChild.isParent( ) ) {
         fail( String.format( "(%s).isParent() returned true before adding any children", grandChild ) );
      }
   }

   @Test
   public void testHasLeftChild( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > child = new LinkedBinaryTreeNode< String >( "Child" );
      LinkedBinaryTreeNode< String > grandChild = new LinkedBinaryTreeNode< String >( "Grandchild" );
      if ( parent.hasLeftChild( ) ) {
         fail( String.format( "(%s).hasLeftChild() returned true before adding any children", parent ) );
      }
      parent.setLeft( child );
      if ( !parent.hasLeftChild( ) ) {
         fail( String.format( "(%s).hasLeftChild() returned false left child should be %s", parent, child ) );
      }
      if ( child.hasLeftChild( ) ) {
         fail( String.format( "(%s).hasLeftChild() returned true before adding any children", child ) );
      }
      child.setRight( grandChild );
      if ( !parent.hasLeftChild( ) ) {
         fail( String.format( "(%s).hasLeftChild() returned false left child should be %s", parent, child ) );
      }
      if ( child.hasLeftChild( ) ) {
         fail( String.format( "(%s).hasLeftChild() returned true but node has no left child", child ) );
      }
      if ( grandChild.hasLeftChild( ) ) {
         fail( String.format( "(%s).hasLeftChild() returned true before adding any children", grandChild ) );
      }
   }

   @Test
   public void testHasRightChild( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > child = new LinkedBinaryTreeNode< String >( "Child" );
      LinkedBinaryTreeNode< String > grandChild = new LinkedBinaryTreeNode< String >( "Grandchild" );
      if ( parent.hasRightChild( ) ) {
         fail( String.format( "(%s).hasRightChild() returned true before adding any children", parent ) );
      }
      parent.setRight( child );
      if ( !parent.hasRightChild( ) ) {
         fail( String.format( "(%s).hasRightChild() returned false right child should be %s", parent, child ) );
      }
      if ( child.hasRightChild( ) ) {
         fail( String.format( "(%s).hasRightChild() returned true before adding any children", child ) );
      }
      child.setLeft( grandChild );
      if ( !parent.hasRightChild( ) ) {
         fail( String.format( "(%s).hasRightChild() returned false right child should be %s", parent, child ) );
      }
      if ( child.hasRightChild( ) ) {
         fail( String.format( "(%s).hasRightChild() returned true but node has no right child", child ) );
      }
      if ( grandChild.hasRightChild( ) ) {
         fail( String.format( "(%s).hasRightChild() returned true before adding any children", grandChild ) );
      }
   }

   @Test
   public void testIsLeaf( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > child = new LinkedBinaryTreeNode< String >( "Child" );
      LinkedBinaryTreeNode< String > grandChild = new LinkedBinaryTreeNode< String >( "Grandchild" );
      if ( !parent.isLeaf( ) ) {
         fail( String.format( "(%s).isLeaf() returned false before adding any children", parent ) );
      }
      parent.setRight( child );
      if ( parent.isLeaf( ) ) {
         fail( String.format( "(%s).isLeaf() returned true but it has a child %s", parent, child ) );
      }
      if ( !child.isLeaf( ) ) {
         fail( String.format( "(%s).isLeaf() returned false before adding any children", child ) );
      }
      child.setLeft( grandChild );
      if ( parent.isLeaf( ) ) {
         fail( String.format( "(%s).isLeaf() returned true but it has a child %s", parent, child ) );
      }
      if ( child.isLeaf( ) ) {
         fail( String.format( "(%s).isLeaf() returned true but it has a child %s", child, grandChild ) );
      }
      if ( !grandChild.isLeaf( ) ) {
         fail( String.format( "(%s).isLeaf() returned false before adding any children", grandChild ) );
      }
   }

   @Test
   public void testPathTo( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      ArrayList< BinaryTreeNode< String > > path1 = parent.pathTo( greatGrandChild );
      if ( path1.size( ) != 4 ) {
         fail( String.format( "(%s).pathTo(%s) does not contain 4 nodes, returned %s", parent, greatGrandChild, path1 ) );
      }
      if ( !path1.get( 0 ).equals( parent ) ) {
         fail( String.format( "(%s).pathTo(%s).get(0) returned %s should be %s from path %s", parent, greatGrandChild, path1.get( 0 ), parent, path1 ) );
      }
      if ( !path1.get( 1 ).equals( rightChild ) ) {
         fail( String.format( "(%s).pathTo(%s).get(1) returned %s should be %s from path %s", parent, greatGrandChild, path1.get( 1 ), rightChild, path1 ) );
      }
      if ( !path1.get( 2 ).equals( grandChildC ) ) {
         fail( String.format( "(%s).pathTo(%s).get(2) returned %s should be %s from path %s", parent, greatGrandChild, path1.get( 2 ), grandChildC, path1 ) );
      }
      if ( !path1.get( 3 ).equals( greatGrandChild ) ) {
         fail( String.format( "(%s).pathTo(%s).get(3) returned %s should be %s from path %s", parent, greatGrandChild, path1.get( 3 ), greatGrandChild, path1 ) );
      }
      ArrayList< BinaryTreeNode< String > > path2 = leftChild.pathTo( grandChildA );
      if ( path2.size( ) != 2 ) {
         fail( String.format( "(%s).pathTo(%s) does not contain 2 nodes, returned %s", leftChild, grandChildA, path2 ) );
      }
      if ( !path2.get( 0 ).equals( leftChild ) ) {
         fail( String.format( "(%s).pathTo(%s).get(0) returned %s should be %s from path %s", leftChild, grandChildA, path2.get( 0 ), leftChild, path2 ) );
      }
      if ( !path2.get( 1 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).pathTo(%s).get(1) returned %s should be %s from path %s", leftChild, grandChildA, path2.get( 1 ), grandChildA, path2 ) );
      }
      ArrayList< BinaryTreeNode< String > > path3 = rightChild.pathTo( leftChild );
      if ( path3.size( ) != 0 ) {
         fail( String.format( "(%s).pathTo(%s) should be empty, returned %s", rightChild, leftChild, path3 ) );
      }
   }

   @Test
   public void testPathFrom( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      ArrayList< BinaryTreeNode< String > > path1 = greatGrandChild.pathFrom( parent );
      if ( path1.size( ) != 4 ) {
         fail( String.format( "(%s).pathFrom(%s) does not contain 4 nodes, returned %s", greatGrandChild, parent, path1 ) );
      }
      if ( !path1.get( 3 ).equals( parent ) ) {
         fail( String.format( "(%s).pathFrom(%s).get(0) returned %s should be %s from path %s", greatGrandChild, parent, path1.get( 3 ), parent, path1 ) );
      }
      if ( !path1.get( 2 ).equals( rightChild ) ) {
         fail( String.format( "(%s).pathFrom(%s).get(1) returned %s should be %s from path %s", greatGrandChild, parent, path1.get( 2 ), rightChild, path1 ) );
      }
      if ( !path1.get( 1 ).equals( grandChildC ) ) {
         fail( String.format( "(%s).pathFrom(%s).get(2) returned %s should be %s from path %s", greatGrandChild, parent, path1.get( 1 ), grandChildC, path1 ) );
      }
      if ( !path1.get( 0 ).equals( greatGrandChild ) ) {
         fail( String.format( "(%s).pathFrom(%s).get(3) returned %s should be %s from path %s", greatGrandChild, parent, path1.get( 0 ), greatGrandChild, path1 ) );
      }
      ArrayList< BinaryTreeNode< String > > path2 =  grandChildA.pathFrom( leftChild );
      if ( path2.size( ) != 2 ) {
         fail( String.format( "(%s).pathFrom(%s) does not contain 2 nodes, returned %s", grandChildA, leftChild, path2 ) );
      }
      if ( !path2.get( 1 ).equals( leftChild ) ) {
         fail( String.format( "(%s).pathFrom(%s).get(0) returned %s should be %s from path %s", grandChildA, leftChild, path2.get( 1 ), leftChild, path2 ) );
      }
      if ( !path2.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).pathFrom(%s).get(1) returned %s should be %s from path %s", grandChildA, leftChild, path2.get( 0 ), grandChildA, path2 ) );
      }
      ArrayList< BinaryTreeNode< String > > path3 =  rightChild.pathFrom( leftChild );
      if ( path3.size( ) != 0 ) {
         fail( String.format( "(%s).pathFrom(%s) should be empty, returned %s", rightChild, leftChild, path1 ) );
      }
   }

   @Test
   public void testGetDepth( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      int depth = parent.getDepth( );
      if ( depth != 0 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", parent, depth, 0 ) );
      }
      depth = leftChild.getDepth( );
      if ( depth != 1 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", leftChild, depth, 1 ) );
      }
      depth = rightChild.getDepth( );
      if ( depth != 1 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", rightChild, depth, 1 ) );
      }
      depth = grandChildA.getDepth( );
      if ( depth != 2 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", grandChildA, depth, 2 ) );
      }
      depth = grandChildB.getDepth( );
      if ( depth != 2 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", grandChildB, depth, 2 ) );
      }
      depth = grandChildC.getDepth( );
      if ( depth != 2 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", grandChildC, depth, 2 ) );
      }
      depth = greatGrandChild.getDepth( );
      if ( depth != 3 ) {
         fail( String.format( "(%s).getDepth returned %d, should be %d.", greatGrandChild, depth, 2 ) );
      }
   }

   @Test
   public void testGetHeight( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      int height = parent.getHeight( );
      if ( height != 0 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", parent, height, 0 ) );
      }
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      height = parent.getHeight( );
      if ( height != 1 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", parent, height, 1 ) );
      }
      height = leftChild.getHeight( );
      if ( height != 1 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", leftChild, height, 1 ) );
      }
      height = rightChild.getHeight( );
      if ( height != 1 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", rightChild, height, 1 ) );
      }
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      height = parent.getHeight( );
      if ( height != 2 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", parent, height, 2 ) );
      }
      height = leftChild.getHeight( );
      if ( height != 2 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", leftChild, height, 2 ) );
      }
      height = rightChild.getHeight( );
      if ( height != 2 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", rightChild, height, 2 ) );
      }
      height = grandChildA.getHeight( );
      if ( height != 2 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", grandChildA, height, 2 ) );
      }
      height = grandChildB.getHeight( );
      if ( height != 2 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", grandChildB, height, 2 ) );
      }
      height = grandChildC.getHeight( );
      if ( height != 2 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", grandChildC, height, 2 ) );
      }
      grandChildC.setRight( greatGrandChild );
      height = greatGrandChild.getHeight( );
      if ( height != 3 ) {
         fail( String.format( "(%s).getHeight() returned %d, should be %d.", greatGrandChild, height, 3 ) );
      }
   }

   @Test
   public void testGetRoot( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      BinaryTreeNode< String > root = parent.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", parent, root, parent ) );
      }
      root = leftChild.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", leftChild, root, parent ) );
      }
      root = rightChild.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", rightChild, root, parent ) );
      }
      root = grandChildA.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", grandChildA, root, parent ) );
      }
      root = grandChildB.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", grandChildB, root, parent ) );
      }
      root = grandChildC.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", grandChildC, root, parent ) );
      }
      root = greatGrandChild.getRoot( );
      if ( !parent.equals( root ) ) {
         fail( String.format( "(%s).getRoot( ) returned %s, should be %s.", greatGrandChild, root, parent ) );
      }
   }

   @Test
   public void testGetParent( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      BinaryTreeNode< String > node = parent.getParent( );
      if ( node != null ) {
         fail( String.format( "(%s).getParent( ) returned %s, should be %s.", parent, node, null ) );
      }
      node = leftChild.getParent( );
      if ( !parent.equals( node ) ) {
         fail( String.format( "(%s).getParent( ) returned %s, should be %s.", leftChild, node, parent ) );
      }
      node = rightChild.getParent( );
      if ( !parent.equals( node ) ) {
         fail( String.format( "(%s).getParent( ) returned %s, should be %s.", rightChild, node, parent ) );
      }
   }

   @Test
   public void testGetLeft( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      BinaryTreeNode< String > node = parent.getLeft( );
      if ( !leftChild.equals( node ) ) {
         fail( String.format( "(%s).getLeft( ) returned %s, should be %s.", parent, node, leftChild ) );
      }
      node = leftChild.getLeft( );
      if ( !grandChildA.equals( node ) ) {
         fail( String.format( "(%s).getLeft( ) returned %s, should be %s.", leftChild, node, grandChildA ) );
      }
      node = grandChildA.getLeft( );
      if ( node != null ) {
         fail( String.format( "(%s).getLeft( ) returned %s, should be %s.", grandChildA, node, null ) );
      }
   }

   @Test
   public void testGetRight( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      BinaryTreeNode< String > node = parent.getRight( );
      if ( !rightChild.equals( node ) ) {
         fail( String.format( "(%s).getRight( ) returned %s, should be %s.", parent, node, rightChild ) );
      }
      node = rightChild.getRight( );
      if ( node != null ) {
         fail( String.format( "(%s).getRight( ) returned %s, should be %s.", rightChild, node, null ) );
      }
      node = grandChildC.getRight( );
      if ( !greatGrandChild.equals( node ) ) {
         fail( String.format( "(%s).getRight( ) returned %s, should be %s.", grandChildC, node, greatGrandChild ) );
      }
   }

   @Test
   public void testRemoveFromParent( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      leftChild.removeFromParent( );
      BinaryTreeNode< String > node = leftChild.getParent( );
      if ( node != null ) {
         fail( String.format( "After (%s).removeFromParent( ), (%s).getParent( ) returned %s, should be %s.", leftChild, leftChild, node, null ) );
      }
      node = parent.getLeft( );
      if ( node != null ) {
         fail( String.format( "After (%s).removeFromParent( ), (%s).getLeft( ) returned %s, should be %s.", leftChild, parent, node, null ) );
      }
   }

   @Test
   public void testTraversePreorder( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      final ArrayList< BinaryTreeNode< String > > list1 = new ArrayList< BinaryTreeNode< String > >( );
      parent.traversePreorder( node -> {
         list1.add( node );
      } );
      if ( list1.size( ) != 7 ) {
         fail( String.format( "(%s).traversePreorder(...) only visited nodes: %s", parent, list1 ) );
      }
      if ( !list1.get( 0 ).equals( parent ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 0 ), 0, parent, list1 ) );
      }
      if ( !list1.get( 1 ).equals( leftChild ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 1 ), 1, leftChild, list1 ) );
      }
      if ( !list1.get( 2 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 2 ), 2, grandChildA, list1 ) );
      }
      if ( !list1.get( 3 ).equals( grandChildB ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 3 ), 3, grandChildB, list1 ) );
      }
      if ( !list1.get( 4 ).equals( rightChild ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 4 ), 4, rightChild, list1 ) );
      }
      if ( !list1.get( 5 ).equals( grandChildC ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 5 ), 5, grandChildC, list1 ) );
      }
      if ( !list1.get( 6 ).equals( greatGrandChild ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 6 ), 6, greatGrandChild, list1 ) );
      }
      final ArrayList< BinaryTreeNode< String > > list2 = new ArrayList< BinaryTreeNode< String > >( );
      leftChild.traversePreorder( node -> {
         list2.add( node );
      } );
      if ( list2.size( ) != 3 ) {
         fail( String.format( "(%s).traversePreorder(...) only visited nodes: %s", leftChild, list2 ) );
      }
      if ( !list2.get( 0 ).equals( leftChild ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 0 ), 0, leftChild, list2 ) );
      }
      if ( !list2.get( 1 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 1 ), 1, grandChildA, list2 ) );
      }
      if ( !list2.get( 2 ).equals( grandChildB ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 2 ), 2, grandChildB, list2 ) );
      }
      final ArrayList< BinaryTreeNode< String > > list3 = new ArrayList< BinaryTreeNode< String > >( );
      grandChildA.traversePreorder( node -> {
         list3.add( node );
      } );
      if ( list3.size( ) != 1 ) {
         fail( String.format( "(%s).traversePreorder(...) only visited nodes: %s", grandChildA, list3 ) );
      }
      if ( !list3.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traversePreorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", grandChildA, list3.get( 0 ), 0, grandChildA, list3 ) );
      }
   }

   @Test
   public void testTraversePostorder( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      final ArrayList< BinaryTreeNode< String > > list1 = new ArrayList< BinaryTreeNode< String > >( );
      parent.traversePostorder( node -> {
         list1.add( node );
      } );
      if ( list1.size( ) != 7 ) {
         fail( String.format( "(%s).traversePostorder(...) only visited nodes: %s", parent, list1 ) );
      }
      if ( !list1.get( 6 ).equals( parent ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 6 ), 6, parent, list1 ) );
      }
      if ( !list1.get( 2 ).equals( leftChild ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 2 ), 2, leftChild, list1 ) );
      }
      if ( !list1.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 0 ), 0, grandChildA, list1 ) );
      }
      if ( !list1.get( 1 ).equals( grandChildB ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 1 ), 1, grandChildB, list1 ) );
      }
      if ( !list1.get( 5 ).equals( rightChild ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 5 ), 5, rightChild, list1 ) );
      }
      if ( !list1.get( 4 ).equals( grandChildC ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 4 ), 4, grandChildC, list1 ) );
      }
      if ( !list1.get( 3 ).equals( greatGrandChild ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 3 ), 3, greatGrandChild, list1 ) );
      }
      final ArrayList< BinaryTreeNode< String > > list2 = new ArrayList< BinaryTreeNode< String > >( );
      leftChild.traversePostorder( node -> {
         list2.add( node );
      } );
      if ( list2.size( ) != 3 ) {
         fail( String.format( "(%s).traversePostorder(...) only visited nodes: %s", leftChild, list2 ) );
      }
      if ( !list2.get( 2 ).equals( leftChild ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 2 ), 2, leftChild, list2 ) );
      }
      if ( !list2.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 0 ), 0, grandChildA, list2 ) );
      }
      if ( !list2.get( 1 ).equals( grandChildB ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 1 ), 1, grandChildB, list2 ) );
      }
      final ArrayList< BinaryTreeNode< String > > list3 = new ArrayList< BinaryTreeNode< String > >( );
      grandChildA.traversePostorder( node -> {
         list3.add( node );
      } );
      if ( list3.size( ) != 1 ) {
         fail( String.format( "(%s).traversePostorder(...) only visited nodes: %s", grandChildA, list3 ) );
      }
      if ( !list3.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traversePostorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", grandChildA, list3.get( 0 ), 0, grandChildA, list3 ) );
      }
   }

   @Test
   public void testTraverseInorder( ) throws Exception {
      LinkedBinaryTreeNode< String > parent = new LinkedBinaryTreeNode< String >( "Parent" );
      LinkedBinaryTreeNode< String > leftChild = new LinkedBinaryTreeNode< String >( "LeftChild" );
      LinkedBinaryTreeNode< String > rightChild = new LinkedBinaryTreeNode< String >( "RightChild" );
      LinkedBinaryTreeNode< String > grandChildA = new LinkedBinaryTreeNode< String >( "GrandchildA" );
      LinkedBinaryTreeNode< String > grandChildB = new LinkedBinaryTreeNode< String >( "GrandchildB" );
      LinkedBinaryTreeNode< String > grandChildC = new LinkedBinaryTreeNode< String >( "GrandchildC" );
      LinkedBinaryTreeNode< String > greatGrandChild = new LinkedBinaryTreeNode< String >( "GreatGrandchild" );
      parent.setLeft( leftChild );
      parent.setRight( rightChild );
      leftChild.setLeft( grandChildA );
      leftChild.setRight( grandChildB );
      rightChild.setLeft( grandChildC );
      grandChildC.setRight( greatGrandChild );
      final ArrayList< BinaryTreeNode< String > > list1 = new ArrayList< BinaryTreeNode< String > >( );
      parent.traverseInorder( node -> {
         list1.add( node );
      } );
      if ( list1.size( ) != 7 ) {
         fail( String.format( "(%s).traverseInorder(...) only visited nodes: %s", parent, list1 ) );
      }
      if ( !list1.get( 3 ).equals( parent ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 3 ), 3, parent, list1 ) );
      }
      if ( !list1.get( 1 ).equals( leftChild ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 1 ), 1, leftChild, list1 ) );
      }
      if ( !list1.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 0 ), 0, grandChildA, list1 ) );
      }
      if ( !list1.get( 2 ).equals( grandChildB ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 2 ), 2, grandChildB, list1 ) );
      }
      if ( !list1.get( 6 ).equals( rightChild ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 6 ), 6, rightChild, list1 ) );
      }
      if ( !list1.get( 4 ).equals( grandChildC ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 4 ), 4, grandChildC, list1 ) );
      }
      if ( !list1.get( 5 ).equals( greatGrandChild ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", parent, list1.get( 5 ), 5, greatGrandChild, list1 ) );
      }
      final ArrayList< BinaryTreeNode< String > > list2 = new ArrayList< BinaryTreeNode< String > >( );
      leftChild.traverseInorder( node -> {
         list2.add( node );
      } );
      if ( list2.size( ) != 3 ) {
         fail( String.format( "(%s).traverseInorder(...) only visited nodes: %s", leftChild, list2 ) );
      }
      if ( !list2.get( 1 ).equals( leftChild ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 1 ), 1, leftChild, list2 ) );
      }
      if ( !list2.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 0 ), 0, grandChildA, list2 ) );
      }
      if ( !list2.get( 2 ).equals( grandChildB ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", leftChild, list2.get( 2 ), 2, grandChildB, list2 ) );
      }
      final ArrayList< BinaryTreeNode< String > > list3 = new ArrayList< BinaryTreeNode< String > >( );
      grandChildA.traverseInorder( node -> {
         list3.add( node );
      } );
      if ( list3.size( ) != 1 ) {
         fail( String.format( "(%s).traverseInorder(...) only visited nodes: %s", grandChildA, list3 ) );
      }
      if ( !list3.get( 0 ).equals( grandChildA ) ) {
         fail( String.format( "(%s).traverseInorder(...) visited %s on iteration %d, should have visited %s, visited nodes: %s", grandChildA, list3.get( 0 ), 0, grandChildA, list3 ) );
      }
   }

//   @Test
//   public void testToString( ) throws Exception {
//      LinkedBinaryTreeNode< String > node = new LinkedBinaryTreeNode< String >( "Parent" );
//      String result = node.toString( );
//      String hashcode = node.getClass( ).getName( ) + "@" + Integer.toHexString( node.hashCode( ) );
//      if ( result.equals( hashcode ) ) {
//         fail( String.format( "(%s).toString() returned the default hashcode string.", node ) );
//      }
//   }

}