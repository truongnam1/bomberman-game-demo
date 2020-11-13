package uet.oop.bomberman.sample;

import java.io.*;
import java.util.ArrayList;

public class HightScore {
    public static ArrayList<String> list = new ArrayList<String>();

    public HightScore() throws IOException {
        readFile();
    }

    public void readFile() throws IOException {
        try {
            FileReader f = new FileReader("res\\hightscore\\hightscore.txt");


            BufferedReader r = new BufferedReader(f);

            String line;
            while ((line = r.readLine()) != null){
                list.add(line);
            }
            f.close();
            r.close();
        } catch (Exception ex) {
            System.out.println("Loi doc file: "+ex);
        }
    }

    public void outToFile() throws IOException {
        FileWriter f = new FileWriter("res\\hightscore\\hightscore.txt");

        for(int i=0 ; i<list.size() ; ++i) {
            f.write(list.get(i)+"\n");
        }
        f.close();
    }
}
