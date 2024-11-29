package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ControllerReader {
    // private static final String PATH =ClassLoader.getSystemResource("config.yml");
    // private File file = new File(PATH);
    Configuration.Builder build = new Configuration.Builder();

    public Configuration getConfiguration(){
        return build.build();
    }

    public ControllerReader(){
        int min = 0,max = 0,attempts = 0;
        try(InputStream in = ClassLoader.getSystemResourceAsStream("src/main/resources/config.yml");
            BufferedReader buff = new BufferedReader(new InputStreamReader(in))){
            String line;
            
            while((line = buff.readLine())!=null){
                String[] strings = line.split(":");
                switch(strings[1]){
                    case "minimum" -> min = Integer.parseInt(strings[2]);
                    case "maximum" -> max = Integer.parseInt(strings[2]);
                    case "attempts" -> attempts = Integer.parseInt(strings[2]);
                }
            }

            
            build.setMin(min);
            build.setMax(max);
            build.setAttempts(attempts);

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
