public class Question<T> extends LinkedBinaryTreeNode<T>{
    public Question(T data) {
        super(data);
    }
    @Override
    public String toString(){
        return "Q:"+super.toString();
    }
}
