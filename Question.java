/**
 * lab group : Mark McArdle, Cade Haas, Jeremiah Niedzielski, Owen Colburn
 * @author Mark McArdle
 */
public class Question<T> extends LinkedBinaryTreeNode<T>{
    public Question(T data) {
        super(data);
    }
    @Override
    public String toString(){
        return "Q:"+super.toString();
    }
}
