package GamePlatformer;

import Game.MainGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save {
    private static final String PATH = "files/fileworld.txt";
    public ArrayList list = new ArrayList();
    public ArrayList readlist = new ArrayList();

    public Save() {

    }

    public void save(){
        try {
            FileWriter writer = new FileWriter(PATH);
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
            }
            if (writer != null) writer.close();
        }catch (IOException e){
            System.out.print("Error...");
        }
    }
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        String c;
        while ((c = reader.readLine()) != null) {
            list.add(c);
            //int i=0;
            list.remove(true);
            System.out.println(list);
            while ((c = reader.readLine()) != null) {
                readlist.add(c);
                //i++;
            }
            //MainGame.pers.saves(true);
            System.out.println(list);
        }
    }
}
