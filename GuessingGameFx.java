import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javafx.stage.Stage;

/**
 * lab group : Mark McArdle, Cade Haas, Jeremiah Niedzielski, Owen Colburn
 * @author Mark McArdle
 * CS1131 L02
 * 
 * This is the most unoptimized code that I have ever written
 */
public class GuessingGameFx extends Application {

    private BorderPane borderPane = null;
    static GuessingGame game;
    TextArea t = new TextArea("");
    static String fn;
    static gamet gthread;

    @Override
    public void stop() {
        gthread.kill = true;

        while (!gthread.killdone) {
            // wait until the kill is done
        }
        //System.out.println(gthread.killdone);
    }

    @Override
    public void start(Stage stage) {

        borderPane = new BorderPane();

        TextArea inputText = new TextArea();

        t.setWrapText(true);

        HBox top = new HBox();

        t.setEditable(false);
        gthread = new gamet(fn);

        EventHandler<MouseEvent> noHandle = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gthread.yesNo = false;
            }
        };
        EventHandler<MouseEvent> yesHandle = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (inputText.getText().replaceAll(" ","").equals("")) {
                    gthread.yesNo = true;
                } else {

                    gthread.get = inputText.getText();
                    inputText.setText("");
                }
            }
        };

        Button no = new Button("no");
        Button yes = new Button("yes");

        no.setOnMouseClicked(noHandle);
        yes.setOnMouseClicked(yesHandle);

        borderPane.setLeft(no);
        borderPane.setRight(yes);
        borderPane.setTop(t);

        t.setMaxHeight(360);

        HBox.setHgrow(t, Priority.ALWAYS);
        top.setPadding(Insets.EMPTY);

        borderPane.setCenter(inputText);
        stage.sizeToScene();

        Scene scene = new Scene(borderPane, 800, 600);
        stage.setTitle("Guessing Game");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            stop();
        });
        new Thread(gthread).start();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            game = new GuessingGame(args[0]);
            fn = args[0];
            launch(args[0]);
        } else {

            System.out.println("enter the tree file that you want to use. ");
            Scanner s = new Scanner(System.in);
            String asdf = s.nextLine();
            s.close();
            fn = asdf;
            game = new GuessingGame(asdf);
            launch(asdf);

        }

    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------

    public class gamet implements Runnable {
        volatile boolean kill = false;
        volatile boolean killdone = false;
        boolean playable = true;
        LinkedBinaryTreeNode<String> root;
        String filename;
        volatile Boolean yesNo = null;
        volatile String get = " ";

        public gamet(String filename) {
            this.filename = filename;
            root = (LinkedBinaryTreeNode<String>) loadTree(filename);
            System.out.println("Game init");
        }
        // @Override
        // public void start(){
        // System.out.println("game started");
        // play();
        // }

        private Boolean YN() {
            while (yesNo == null) {
                // this is incredibly inefficient
                if (kill) {
                    return null;
                }
            }
            //System.out.println(yesNo);
            return yesNo;
        }

        public String getString() {
            while (get.equals(" ")) {
                // once again this is incredibly inefficient
                if (kill) {
                    return null;
                }
            }
            return get;
        }

        String toptext = " ";

        private void o(String o) {
            //System.out.println(o);

            toptext = toptext + o;
            t.setText(toptext + "\n");
            t.setScrollTop(Double.MAX_VALUE);
        }

        /**
         * Loads a BinaryTree file that has been saved as a preorder traversal.
         * Interior nodes are prefixed by "Q:".
         * Leaf nodes are prefixed by "G:"
         * 
         * @param filename
         * @return the root node
         */
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
            root = (LinkedBinaryTreeNode<String>) loadHelper(lines.iterator());
            return root;
        }

        private BinaryTreeNode<String> loadHelper(Iterator<String> lines) {
            if (!lines.hasNext()) {
                return null;
            }
            if (!playable) {
                return null; // don't want to add anything to a list that is incorrect to avoid errors
            }
            LinkedBinaryTreeNode<String> node = null;
            String line = lines.next();
            if (line.length() > 2) {
                if (line.substring(0, 2).toLowerCase().equals("q:")) {
                    node = new Question<String>(line.substring(2));
                    node.setLeft(loadHelper(lines));
                    node.setRight(loadHelper(lines));
                } else if (line.substring(0, 2).toLowerCase().equals("g:")) {
                    node = new Guess<String>(line.substring(2));
                } else {
                    System.out.println(
                            "Issue parsing file for the line : " + line + " : could not find \"q:\" or \"g:\"");
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

        public void saveTree(String filename) {
            try {
                FileWriter tmp = new FileWriter(filename);
                String toFile = "";
                ArrayList<LinkedBinaryTreeNode<String>> nodes = new ArrayList<LinkedBinaryTreeNode<String>>();
                root.traversePreorder(cheese -> {
                    nodes.add((LinkedBinaryTreeNode<String>) cheese);
                });
                int x = 0;
                while (x < nodes.size() - 1) {
                    toFile = toFile + nodes.get(x) + "\n";
                    x++;
                }
                toFile = toFile + nodes.get(x);
                tmp.write(toFile);
                tmp.close();
            } catch (IOException e) {
                o("Something went wrong with saving the tree.");
            }
        }

        public BinaryTreeNode<String> getRoot() {
            return root;
        }

        public void play() {
            if (!playable) {
                return;
            }
            o("To enter text, use the middle text area and confirm with the yes button. Use the yes and no buttons for any yes or no questions.\n");
            while (true) {
                
                o("Shall we play a game? (y/n)\n");
                
                t.setScrollTop(Double.MAX_VALUE);
                Boolean b = YN();yesNo = null;
                t.setText("");
                toptext="";
                if (kill) {
                    break;
                }
                if (b == null) {
                    continue;
                }
                if (!b) {
                    break;
                }
                LinkedBinaryTreeNode<String> current = root;
                while (!current.isLeaf()) {
                    o(current.data+"\n");
                    b = YN();yesNo = null;
                    if (kill) {
                        break;
                    }
                    if (b != null) {
                        if (b) {
                            current = (LinkedBinaryTreeNode<String>) current.getRight();
                        } else {
                            current = (LinkedBinaryTreeNode<String>) current.getLeft();
                        }
                        if (current == null) {
                            o("Something went wrong! There may be an issue with the tree structure.");
                            return;
                        }
                    } else {
                        o("Please only enter y or n.");
                    }
                }
                o("Are you thinking of : " + current.data + " ? (y/n)\n");
                b = YN();yesNo = null;
                if (kill) {
                    break;
                }
                while (b == null) {
                    o("Please only enter y or n.");
                    b = YN();yesNo = null;
                    if (kill) {
                        break;
                    }
                }
                if (kill) {
                    break;
                }
                if (b) {
                    o("I win! \n\n");
                } else {
                    o("You win!\nWhat are you thinking of? (anything or \"exit\")\n");
                    get=" ";
                    String in = getString();
                    if (kill) {
                        break;
                    }
                    if (in.toLowerCase().equals("exit")) {
                        o("\n\n\n");
                        continue;
                    }
                    o("What question separates : " + current.data + " from : " + in + " ?\n");
                    get=" ";
                    String qin = getString();
                    if (kill) {
                        break;
                    }
                    Question<String> tmp = new Question<String>(qin);
                    o("Is " + in + " correct when the answer to \"" + qin + "\" is yes? (y/n)\n");
                    b = YN();yesNo = null;
                    if (kill) {
                        break;
                    }
                    while (b == null) {
                        o("Please only enter y or n.");
                        b = YN();yesNo = null;
                        if (kill) {
                            break;
                        }
                    }
                    if (kill) {
                        break;
                    }
                    if (current.getParent() != null) {
                        if (current.getParent().getLeft() == current) {
                            current.getParent().setLeft(tmp);
                        } else {
                            current.getParent().setRight(tmp);
                        }
                    }
                    if (b) {
                        tmp.setLeft(current);
                        tmp.setRight(new Guess<String>(in));
                    } else {
                        tmp.setLeft(new Guess<String>(in));
                        tmp.setRight(current);
                    }
                    root = (LinkedBinaryTreeNode<String>) current.getRoot();
                    // root.traversePreorder(data -> {
                    // System.out.println(data);
                    // });
                    if (kill) {
                        break;
                    }
                    saveTree(filename);
                    o("\n\n\n");
                }
                if (kill) {
                    break;
                }
            }
            killdone = true;
            o("\nGame done, you can now close this window.");
            System.out.println("Killed!");

        }

        @Override
        public void run() {
            play();

        }
    }
}
