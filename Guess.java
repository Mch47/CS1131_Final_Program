public class Guess<T> extends LinkedBinaryTreeNode<T>{
    public Guess(T data) {
        super(data);
    }
    @Override
    public String toString(){
        return "G:"+super.toString();
    }
}