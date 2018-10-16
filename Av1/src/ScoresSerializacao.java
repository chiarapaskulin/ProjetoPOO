import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.management.MappedMXBeanType;

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

        leArqEmBin();
        escreveArqEmBin(best);

        leAqrSerializado();
        escreveArqSerializado(best);

        leArqEmCSV();
        escreveArqEmCSV(best);

        leArqEmJSON();
        escreveArqEmJSON(best);

    }

    public static void escreveArqEmBin(BestScores best){
        try{
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            String content = new String(output.toByteArray());

            content += best.toString();

            FileOutputStream out = new FileOutputStream("coleção_de_Scores.bin");
            out.write(content.getBytes());
            out.flush();
            out.close();

            System.out.println(content);

        }catch(Exception e) {
            System.out.println("Erro = " + e);
        }
    }

    public static void leArqEmBin() {
        try {
            File origin = new File("coleção_de_Scores.bin");
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(origin));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            int i = 0;

            while ((i = input.read()) != -1) {
                output.write(i);
                System.out.println( i  + " - " + ( (char) i) );
            }

            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escreveArqSerializado(BestScores best){
        try{
            FileOutputStream file = new FileOutputStream("coleção_de_Scores.arq");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(best);
            output.close( );

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void leAqrSerializado() {
        BestScores b = null;

        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("coleção_de_Scores.arq"));
            b = (BestScores) input.readObject();
            input.close();

            System.out.println(b.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    public static void leArqEmCSV() {
        String file = "coleção_de_Scores.csv";
        BufferedReader reader = null;
        String line = "";
        String divisor = ",";
        try {

            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] pontuacao = line.split(divisor);

                //System.out.println("Nome: " + pontuacao[pontuacao.length-2] + ". Pontuação: " + pontuacao[pontuacao.length-1]);
                System.out.println("(" + pontuacao[pontuacao.length-2] + ", " + pontuacao[pontuacao.length-1] + ")");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void escreveArqEmJSON(BestScores best){
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

    public static void leArqEmJSON(){
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
