import java.io.Serializable;

public class GameEntry implements Serializable {
    private String name;
    private int score;

    public GameEntry(String name, int score){
        if(score<0) throw new IllegalArgumentException("Negative score");

        if(name==null) throw new NullPointerException("Null name");

        if(name.trim().isEmpty()) throw new IllegalArgumentException("Empty name");

        this.name = name;
        this.score = score;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    /** Retorna uma string representando o objeto
     * Formato: (<name>, <score>)
     */
    public String toString(){
        return "(" + name + ", " + score + ")";
    }

}
