import javax.swing.*;

public class TreeDisplay extends JFrame {
	GuessingGame game = null;

	public void initTreeDisplay( ) {
		this.setSize ( 800,600 );
		this.setContentPane ( new TreeDisplayPanel ( game.getRoot () ) );
		this.setVisible ( true );
	}

	public TreeDisplay( String filename ) {
		game = new GuessingGame ( filename );
		game.loadTree( filename );
		initTreeDisplay ();
	}

	public TreeDisplay ( GuessingGame game ) {
		this.game = game;
		initTreeDisplay ();
	}

	public static void main ( String[] args ) {
		if ( args.length == 0 ) {
			throw new RuntimeException("You need to pass full filename of the tree file to load as a command line argument.");
		}
		TreeDisplay treeDisplay = new TreeDisplay ( args[0] );
	}
}
