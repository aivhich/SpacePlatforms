package Game;

import java.io.*;
import java.util.ArrayList;

public class Save {
    private static final String PATH = "files/fileworld.txt";
    ArrayList list = new ArrayList();
    File Filesave = new File(PATH);

    public void save() throws IOException{
        FileWriter writer = new FileWriter(PATH);
        for(int i = 0; i<list.size(); i++){
            writer.write(list.get(i)+"\n");
        }
        if(writer != null) writer.close();

    }
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        String c;
        while ((c = reader.readLine()) != null) {
            list.add(c);
        }
        deleted();
    }
    void deleted(){
        try {
            PrintWriter writer = new PrintWriter(PATH);
            writer.print("");
            writer.flush();
            writer.close();

        }catch (Exception e)
        {

        }
    }
}
