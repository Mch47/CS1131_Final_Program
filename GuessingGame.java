import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GuessingGame implements Game {

    boolean playable = true;

    public GuessingGame(String filename) {
    }

    /**
     * Loads a BinaryTree file that has been saved as a preorder traversal.
     * Interior nodes are prefixed by "Q:".
     * Leaf nodes are prefixed by "G:"
     * 
     * @param filename
     * @return the root node
     */
    @Override
    public BinaryTreeNode<String> loadTree(String filename) {
        FileReader file;
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("\"" + filename + "\" not found");
            playable = false;
            return null;
        }
        Scanner s = new Scanner(file);
        ArrayList<String> lines = new ArrayList<String>();

        while (s.hasNextLine()) {
            lines.add(s.nextLine());
        }

        return loadHelper(lines.iterator());
        // {
        // if(line.length()>1){

        // }
        // else{
        // System.out.println("Issue parsing file on line "+x+" : line not long
        // enough");
        // playable=false;
        // return null;
        // }
        // }
        // s.close();
        // return null;
    }

    private BinaryTreeNode<String> loadHelper(Iterator<String> lines) {
        LinkedBinaryTreeNode<String> node=null;
        if (!lines.hasNext()) {
            return null;
        }
        String line = lines.next();
        if (line.length() > 1) {
            if(line.substring(0, 2).toLowerCase().equals("q:")){

            }
        } else {
            System.out.println("Issue parsing file for the line : " + line + " : line not long enough");
            playable = false;
            return null;
        }
        return node;
    }

    @Override
    public void saveTree(String filename) {
        // TODO Auto-generated method stub

    }

    @Override
    public BinaryTreeNode<String> getRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        GuessingGame game;
        if (args.length > 0) {
            game = new GuessingGame(args[0]);
        } else {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter the filename of the tree that you want to use.");
            game = new GuessingGame(s.nextLine());
            s.close();
        }
        game.play();
    }

}
