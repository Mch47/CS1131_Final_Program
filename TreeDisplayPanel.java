import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * A panel that maintains a picture of a binary tree.
 */
public class TreeDisplayPanel extends JPanel implements MouseMotionListener, MouseListener {
   private BinaryTreeNode< ? > tree;
   private BinaryTreeNode< ? > selectedNode;
   private Point selectedPoint = null;
   private int gridwidth;
   private int gridheight;

   /**
    * Stores the pixel values for each node in the tree.
    */
   private Map< BinaryTreeNode< ? >, Point > coordinates = new HashMap< BinaryTreeNode< ? >, Point >( );

   /**
    * Constructs a panel, saving the tree and drawing parameters.
    */
   public TreeDisplayPanel( BinaryTreeNode< ? > tree, int gridwidth,
                            int gridheight ) {
      this( tree );
      this.tree = tree;
      this.gridwidth = gridwidth;
      this.gridheight = gridheight;
   }

   /**
    * Constructs a panel, saving the tree and drawing parameters.
    */
   public TreeDisplayPanel( BinaryTreeNode< ? > tree ) {
      this.tree = tree;
      this.gridwidth = 25;
      this.gridheight = 45;
      this.addMouseMotionListener( this );
      this.addMouseListener( this );
   }

   /**
    * Changes the tree rendered by this panel.
    */
   public void setTree( BinaryTreeNode< ? > root ) {
      tree = root;
      repaint( );
   }

   /**
    * Changes the tree rendered by this panel.
    */
   public void setSelected( BinaryTreeNode< ? > node ) {
      selectedNode = node;
      repaint( );
   }

   /**
    * Draws the tree in the panel.  First it computes the coordinates
    * of all the nodes with an inorder traversal, then draws them
    * with a postorder traversal.
    */
   public void paintComponent( final Graphics g ) {
      super.paintComponent( g );

      if ( tree == null ) {
         return;
      }

      tree.traverseInorder( new BinaryTreeNode.Visitor( ) {
         private int x = 3 * gridwidth;

         public void visit( BinaryTreeNode node ) {
            String data = node.getData( ).toString( );
            if ( coordinates.get( node ) == null ) {
               FontMetrics fm = g.getFontMetrics( );
               Rectangle r = fm.getStringBounds( data, g ).getBounds( );
               gridheight = Math.max( gridheight, 2 * ( int ) r.getHeight( ) + 2 );
               if ( x - r.getWidth( ) / 2 < 0 ) {
                  x = ( int ) ( r.getWidth( ) / 2 + gridwidth );
               }
               int y = gridheight * ( depth( node ) + 1 );
               int myWidth = Math.max( getWidth( ), x + 2 * gridwidth );
               int myHeight = Math.max( getWidth( ), y + 2 * gridheight );
               coordinates.put( node, new Point( x, y ) );
               x += Math.min( gridwidth, r.getWidth( ) ) + ( 1.5 * gridwidth );
               setPreferredSize( new Dimension( myWidth, myHeight ) );
            }
         }
      } );

      g.setColor( Color.LIGHT_GRAY );
      g.fillRect( 0, 0, getWidth( ), getHeight( ) );

      tree.traversePostorder( new BinaryTreeNode.Visitor( ) {
         public void visit( BinaryTreeNode node ) {
            String data = node.getData( ).toString( );
            Point center = ( Point ) coordinates.get( node );
            if ( node.getParent( ) != null ) {
               Point parentPoint = ( Point ) coordinates
                    .get( node.getParent( ) );
               g.setColor( Color.black );
               g.drawLine( center.x, center.y, parentPoint.x, parentPoint.y );
               g.setColor( Color.white );
               String label = "YES";
               if ( node.getParent( ).getLeft( ) == node ) {
                  label = "NO";
               }
               g.setFont( new Font( "Monospaced", Font.BOLD, 12 ) );
               FontMetrics fm = g.getFontMetrics( );
               g.drawString( label, ( int ) ( ( center.x + parentPoint.x ) / 2.0 ), ( int ) ( ( center.y + parentPoint.y + fm.getLineMetrics( label, g ).getHeight( ) ) / 2.0 ) );
            }
            g.setFont( new Font( "Monospaced", Font.BOLD, 12 ) );
            FontMetrics fm = g.getFontMetrics( );
            Rectangle r = fm.getStringBounds( data, g ).getBounds( );
            r.setLocation( center.x - r.width / 2, center.y - r.height / 2 );
            Color color = getNodeColor( node );
            Color textColor = ( color.getRed( ) + color.getBlue( )
                 + color.getGreen( ) < 382 )
                 ? Color.white
                 : Color.black;
            g.setColor( color );
            g.fillRect( r.x - 2, r.y - 2, r.width + 4, r.height + 4 );
            if ( node == selectedNode ) {
               g.setColor( Color.RED );
               g.drawRect( r.x - 4, r.y - 4, r.width + 8, r.height + 8 );
            }
            g.setColor( textColor );
            g.drawString( data, r.x, r.y + r.height );
         }
      } );
   }

   /**
    * Returns a color for the node.  If the node is of a class with a
    * field called "color", and that field currently contains a
    * non-null value, then that value is returned.  Otherwise
    * a default color of yellow is returned.
    */
   Color getNodeColor( BinaryTreeNode< ? > node ) {
      if ( node.isLeaf() ) {
         return Color.CYAN; //.GREEN;
      }
      return Color.yellow;
   }

   private int depth( BinaryTreeNode< ? > node ) {
      return ( node.getParent( ) == null ) ? 0 : 1 + depth( node.getParent( ) );
   }

   @Override
   public void mouseDragged( MouseEvent e ) {
      BinaryTreeNode< ? > n = selectedNode;
      if ( n != null ) {
//         System.out.println( "DRAGGED: " + n.getData( ).toString( ) );
         if ( selectedNode == null ) {
            selectedPoint = coordinates.get( n );
         }
         Point center = coordinates.get( n );
         selectedPoint = new Point( ( int ) ( center.getX( ) + ( e.getX( ) - selectedPoint.getX( ) ) ), ( int ) ( center.getY( ) + ( e.getY( ) - selectedPoint.getY( ) ) ) );
         coordinates.put( n, selectedPoint );
         this.invalidate( );
         this.repaint( );

      }
   }

   @Override
   public void mouseMoved( MouseEvent e ) {

   }

   @Override
   public void mouseClicked( MouseEvent e ) {

   }

   @Override
   public void mousePressed( MouseEvent e ) {
      selectedNode = null;
      selectedPoint = null;
      for ( BinaryTreeNode< ? > n : coordinates.keySet( ) ) {
         Point center = ( Point ) coordinates.get( n );
         String data = n.getData( ).toString( );
         Graphics g = this.getGraphics( );
         g.setFont( new Font( "Monospaced", Font.BOLD, 12 ) );
         FontMetrics fm = g.getFontMetrics( );
         Rectangle r = fm.getStringBounds( data, g ).getBounds( );
         r.setLocation( center.x - r.width / 2, center.y - r.height / 2 );
         if ( r.contains( e.getPoint( ) ) ) {
//            System.out.println( "CLICKED: " + data );
            selectedNode = n;
            selectedPoint = e.getPoint( );
            break;
         }
      }
      this.invalidate( );
      this.repaint( );
   }

   @Override
   public void mouseReleased( MouseEvent e ) {
      selectedNode = null;
      selectedPoint = null;
      this.invalidate( );
      this.repaint( );
   }

   @Override
   public void mouseEntered( MouseEvent e ) {

   }

   @Override
   public void mouseExited( MouseEvent e ) {

   }
}