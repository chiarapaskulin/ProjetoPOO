import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoresSerializacao {
    public static void main(String args[]){

        BestScores best = new BestScores();

        best.add(new GameEntry("Chiara", 10));
        best.add(new GameEntry("Mateus", 10));
        best.add(new GameEntry("Felipe", 9));
        best.add(new GameEntry("Maria", 8));
        best.add(new GameEntry("João", 2));
        best.add(new GameEntry("Larissa", 6));
        best.add(new GameEntry("Roberto", 7));
        best.add(new GameEntry("Rafaela", 8));
        best.add(new GameEntry("Camila", 6));
        best.add(new GameEntry("Marina", 5));
        best.add(new GameEntry("Norberto", 6));
        best.add(new GameEntry("Chiara", 9));

        //escreveArqEmJSON(best);

        //leArqEmJSON(best);

        escreveArqEmCSV(best);

    }

    public static void escreveArqEmCSV(BestScores best){
        try {
            FileWriter writeFile = new FileWriter("coleção_de_Scores.csv");

            for(int i=0; i<best.getNumScores(); i++){
                writeFile.append(best.get(i).getName());
                writeFile.append(',');
                int score = best.get(i).getScore();
                writeFile.append(Integer.toString(score));
                writeFile.append('\n');
            }

            writeFile.flush();
            writeFile.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void escreveArqEmJSON(BestScores best){
        //ESCRITA DE ARQUIVO EM JSON
        FileWriter writeFile = null;

        JSONObject object = new JSONObject();

        object.put("Scores Serialization", best.toString());

        try{
            writeFile = new FileWriter("coleção_de_Scores.json");
            writeFile.write(object.toJSONString());
            writeFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(object);
    }

    public static void leArqEmJSON(BestScores best){
        //LEITURA DE ARQUIVO EM JSON
        JSONObject objectJ;

        JSONParser parser = new JSONParser();

        String titulo;

        try {
            objectJ = (JSONObject) parser.parse(new FileReader("coleção_de_Scores.json"));

            titulo = (String) objectJ.get("Scores Serialization");

            System.out.println("Scores Serialization: " + titulo);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
