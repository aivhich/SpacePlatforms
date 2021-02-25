package Game;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Boolean.parseBoolean;

public class Save {
    private static final String PATH = "files/fileworld.txt";
    ArrayList list = new ArrayList();
    ArrayList readlist = new ArrayList();

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
        //int i=0;
        list.remove(true);
        System.out.println(list);
        while((c=reader.readLine())!=null){
            readlist.add(c);
            //i++;
        }
        MainGame.pers.saves(true);
        System.out.println(list);
    }
}
