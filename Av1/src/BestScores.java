import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BestScores implements Scores, Serializable {

    private List<GameEntry> best;
    private int cont;

    public BestScores(){
        best = new ArrayList<>(10);
        cont = 0;
    }

    public boolean add(GameEntry e){
        if(cont==0){
            best.add(e);
            cont++;
            return true;
        }
        else if(cont<10){
            for(int i=0; i<cont; i++){
                if(e.getScore()>best.get(i).getScore()){
                    best.add(i, e);
                    cont++;
                    return true;
                }
            }
            best.add(e);
            cont++;
            return true;
        }
        else {
            int menor = best.get(0).getScore();
            int x;
            int index = 0;

            for (int i = 1; i < cont; i++) {
                x = best.get(i).getScore();

                if (x < menor) {
                    menor = x;
                    index = i;
                }
            }

            if (menor > e.getScore()) return false;

            best.remove(index);
            //cont--;

            for (int i = 0; i < cont; i++) {
                if (e.getScore() > best.get(i).getScore()) {
                    best.add(i, e);
                    //cont++;
                    return true;
                }
            }
        }
        return false;

    }

    public GameEntry get(int i){
        if((i<0 || i>10) || i>=cont) return null;

        return best.get(i);
    }

    public int getCapacity(){
        return best.size();
    }

    public int getNumScores(){
        return cont;
    }

    @Override
    public String toString(){
        String all="[";


        for(int i=0; i<cont; i++){
            all += best.get(i).toString();
            if(i!=cont-1) all += ",";
        }

        all += "]";

        return all;
    }
}