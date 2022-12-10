import java.util.ArrayList;
import java.util.Collections;


/**
 * lab group : Mark McArdle, Cade Haas, Jeremiah Niedzielski, Owen Colburn
 * @author Mark McArdle
 */
public class LinkedBinaryTreeNode<T> implements BinaryTreeNode<T> {

    T data;
    LinkedBinaryTreeNode<T> parent = null, left = null, right = null;

    public LinkedBinaryTreeNode(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;

    }

    @Override
    public BinaryTreeNode<T> getRoot() {
        LinkedBinaryTreeNode<T> current = this;
        while (current.parent != null) {
            current = current.parent;
        }
        return current;
    }

    @Override
    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = (LinkedBinaryTreeNode<T>) parent;

    }

    @Override
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    @Override
    public void setLeft(BinaryTreeNode<T> child) {
        this.left = (LinkedBinaryTreeNode<T>) child;
        if (child != null) {
            child.setParent(this);
        }

    }

    @Override
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    @Override
    public void setRight(BinaryTreeNode<T> child) {
        this.right = (LinkedBinaryTreeNode<T>) child;
        if (child != null) {
            child.setParent(this);
        }
    }

    @Override
    public boolean isParent() {
        if (left != null || right != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLeaf() {
        if (left == null && right == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasLeftChild() {
        return left != null;
    }

    @Override
    public boolean hasRightChild() {
        return right != null;
    }

    @Override
    public int getDepth() {
        LinkedBinaryTreeNode<T> current = this;
        int depth = 0;
        while (current.parent != null) {
            depth++;
            current = current.parent;
        }
        return depth;
    }

    @Override
    public int getHeight() {
        ArrayList<LinkedBinaryTreeNode<T>> list = new ArrayList<LinkedBinaryTreeNode<T>>();
        int size = 0;
        LinkedBinaryTreeNode<T> root = (LinkedBinaryTreeNode<T>) this.getRoot();
        if (root.hasLeftChild()) {
            list.add((LinkedBinaryTreeNode<T>) root.getLeft());
        }
        if (root.hasRightChild()) {
            list.add((LinkedBinaryTreeNode<T>) root.getRight());
        }
        while (list.size() > 0) {
            if (list.get(0) == null) {
                list.remove(0);
                continue;
            }
            int depth = list.get(0).getDepth();
            if (depth > size) {
                size = depth;
            }
            if (list.get(0).hasLeftChild()) {
                list.add((LinkedBinaryTreeNode<T>) list.get(0).getLeft());
            }
            if (list.get(0).hasRightChild()) {
                list.add((LinkedBinaryTreeNode<T>) list.get(0).getRight());
            }
            list.remove(0);
        }
        return size;
    }

    @Override
    public int size() {
        ArrayList<LinkedBinaryTreeNode<T>> list = new ArrayList<LinkedBinaryTreeNode<T>>();
        int size = 1;
        if (this.hasLeftChild()) {
            list.add((LinkedBinaryTreeNode<T>) this.getLeft());
            size++;
        }
        if (this.hasRightChild()) {
            list.add((LinkedBinaryTreeNode<T>) this.getRight());
            size++;
        }
        while (list.size() > 0) {
            if (list.get(0).hasLeftChild()) {
                list.add((LinkedBinaryTreeNode<T>) list.get(0).getLeft());
                size++;
            }
            if (list.get(0).hasRightChild()) {
                list.add((LinkedBinaryTreeNode<T>) list.get(0).getRight());
                size++;
            }
            list.remove(0);
        }
        return size;
    }

    @Override
    public void removeFromParent() {
        if (this.parent != null) {
            if (this.parent.getLeft().equals(this)) {
                this.parent.setLeft(null);
            } else {
                this.parent.setRight(null);
            }
            this.parent = null;
        }

    }

    @Override
    public ArrayList<BinaryTreeNode<T>> pathTo(BinaryTreeNode<T> descendant) {
        ArrayList<LinkedBinaryTreeNode<T>> list = new ArrayList<LinkedBinaryTreeNode<T>>();
        ArrayList<BinaryTreeNode<T>> path = new ArrayList<BinaryTreeNode<T>>();
        if (this.hasLeftChild()) {
            list.add((LinkedBinaryTreeNode<T>) this.getLeft());
        }
        if (this.hasRightChild()) {
            list.add((LinkedBinaryTreeNode<T>) this.getRight());
        }
        while (list.size() > 0) {
            if (list.get(0) == descendant) {
                path = list.get(0).pathFrom(this);
                Collections.reverse(path);
                return path;
            }
            if (list.get(0).hasLeftChild()) {
                list.add((LinkedBinaryTreeNode<T>) list.get(0).getLeft());
            }
            if (list.get(0).hasRightChild()) {
                list.add((LinkedBinaryTreeNode<T>) list.get(0).getRight());
            }
            list.remove(0);
        }
        path.clear();
        return path;
    }

    @Override
    public ArrayList<BinaryTreeNode<T>> pathFrom(BinaryTreeNode<T> ancestor) {
        ArrayList<BinaryTreeNode<T>> path = new ArrayList<BinaryTreeNode<T>>();
        LinkedBinaryTreeNode<T> current = this;
        path.add(current);
        while (current.parent != ancestor) {
            current = current.parent;
            path.add(current);
            if (current == null) {
                path.clear();
                return path;
            }
        }
        path.add(current.parent);
        return path;
    }

    @Override
    public void traversePreorder(BinaryTreeNode.Visitor visitor) { 
        
        PreOrderHelper(visitor,this);
        
        // //a non-recursive way
        // LinkedBinaryTreeNode<T> current = this;
        // ArrayList<BinaryTreeNode<T>> checked = new ArrayList<BinaryTreeNode<T>>();
        // int size = current.size();
        // while (checked.size() < size) {
        //     if (!checked.contains(current)) {
        //         visitor.visit(current);
        //         checked.add(current);
        //     }
        //     if (current.hasLeftChild() && !checked.contains(current.getLeft())) {
        //         current = (LinkedBinaryTreeNode<T>) current.getLeft();
        //     } else if (current.hasRightChild() && !checked.contains(current.getRight())) {
        //         current = (LinkedBinaryTreeNode<T>) current.getRight();
        //     } else {
        //         current = (LinkedBinaryTreeNode<T>) current.getParent();
        //     }
        // }
    }

    private void PreOrderHelper(BinaryTreeNode.Visitor visitor, LinkedBinaryTreeNode<T> current) {
        visitor.visit(current);
        if(current.hasLeftChild()){
            this.PreOrderHelper(visitor, (LinkedBinaryTreeNode<T>)current.getLeft());
        }
        if(current.hasRightChild()){
            this.PreOrderHelper(visitor, (LinkedBinaryTreeNode<T>)current.getRight());
        }
    }

    @Override
    public void traversePostorder(BinaryTreeNode.Visitor visitor) {
        PostOrderHelper(visitor, this);
    }

    private void PostOrderHelper(BinaryTreeNode.Visitor visitor, LinkedBinaryTreeNode<T> current) {
        if(current.hasLeftChild()){
            this.PostOrderHelper(visitor, (LinkedBinaryTreeNode<T>)current.getLeft());
        }
        if(current.hasRightChild()){
            this.PostOrderHelper(visitor, (LinkedBinaryTreeNode<T>)current.getRight());
        }
        visitor.visit(current);
    }

    @Override
    public void traverseInorder(BinaryTreeNode.Visitor visitor) {
        InOrderHelper(visitor, this);
    }

    private void InOrderHelper(BinaryTreeNode.Visitor visitor, LinkedBinaryTreeNode<T> current) {
        if(current.hasLeftChild()){
            this.InOrderHelper(visitor, (LinkedBinaryTreeNode<T>)current.getLeft());
        }
        visitor.visit(current);
        if(current.hasRightChild()){
            this.InOrderHelper(visitor, (LinkedBinaryTreeNode<T>)current.getRight());
        }
    }

    @Override
    public String toString(){
        return data.toString();
    }

}
