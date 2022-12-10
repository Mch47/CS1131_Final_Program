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
    LinkedBinaryTreeNode<String> root;
    String filename;

    public GuessingGame(String filename) {
        this.filename=filename;
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
        s.close();
        root=(LinkedBinaryTreeNode<String>) loadHelper(lines.iterator());
        return root;
    }

    private BinaryTreeNode<String> loadHelper(Iterator<String> lines) {
        if (!lines.hasNext()) {
            return null;
        }
        if(!playable){
            return null; //don't want to add anything to a list that is incorrect to avoid errors
        }
        LinkedBinaryTreeNode<String> node=null;
        String line = lines.next();
        if (line.length() > 2) {
            if(line.substring(0, 2).toLowerCase().equals("q:")){
                node=new Question<String>(line.substring(2));
                node.setLeft(loadHelper(lines));
                node.setRight(loadHelper(lines));
            }
            else if(line.substring(0,2).toLowerCase().equals("g:")){
                node=new Guess<String>(line.substring(2));
            }
            else{
                System.out.println("Issue parsing file for the line : " + line + " : could not find \"q:\" or \"g:\"");
                playable = false;
                return null;
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
        return root;
    }

    @Override
    public void play() {
        Scanner s=new Scanner(System.in);
        while(true){
            o("Shall we play a game? (y/n)");
            if(YN(s)!=true){
                break;
            }
            LinkedBinaryTreeNode<String> current=root;
            while(!current.isLeaf()){
                o(current.data);
                Boolean b=YN(s);
                if(b!=null){
                    if(b){
                        current=(LinkedBinaryTreeNode<String>) current.getRight();
                    }
                    else{
                        current=(LinkedBinaryTreeNode<String>) current.getLeft();
                    }
                    if(current==null){
                        o("Something went wrong! There may be an issue with the tree structure.");
                        return;
                    }
                }
                else{
                    o("Please only enter y or n.");
                }
            }
            o("Are you thinking of : "+current.data+" ? (y/n)");
            Boolean b=YN(s);
            while(b==null){
                o("Please only enter y or n.");
                b=YN(s);
            }
            if(b){
                o("I win! \n\n\n");
            }
            else{
                o("You win!\nWhat are you thinking of? (anything or \"exit\")");
                String in=s.nextLine();
                if(in.toLowerCase().equals("exit")){
                    o("\n\n\n");
                    continue;
                }
                o("What question separates : "+current.data+" from : "+in+" ?");
                String qin=s.nextLine();
                Question<String> tmp=new Question<String>(qin);
                o("Is "+in+" correct when the answer to \""+qin+"\" is yes? (y/n)");
                b=YN(s);
                while(b==null){
                    o("Please only enter y or n.");
                    b=YN(s);
                }
                if(current.getParent().getLeft()==current){
                    current.getParent().setLeft(tmp);
                }
                else{
                    current.getParent().setRight(tmp);
                }
                if(b){
                    tmp.setLeft(current);
                    tmp.setRight(new Guess<String>(in));
                }
                else{
                    tmp.setLeft(new Guess<String>(in));
                    tmp.setRight(current);
                }


            }
            

        }
        s.close();
    }

    private static Boolean YN(Scanner s){
        String in=s.nextLine();
        if(in.toLowerCase().equals("y")){
            return true;
        }
        if(in.toLowerCase().equals("n")){
            return false;
        }
        return null;
    }

    private static void o(String o){
        System.out.println(o); //I am tired of writing System.out.println();
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
