package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode {
    private int info;
    private  LLIntegerNode link = null;

    public LLIntegerNode(int i){
        this.info = i;
    }

    //getters
    public int getInfo() {
        return info;
    }

    public LLIntegerNode getLink() {
        return link;
    }

    //setters
    public void setInfo(int i){
        this.info = i;
    }

    public void setLink(LLIntegerNode n){
        this.link = n;
    }

    // TODO
}

