package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.locks.Condition;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import it.unibo.mvc.Configuration.Builder;

public class ControllerReader {
    private static final String PATH ="src/main/resources/config.yml"; 
    private File file = new File(PATH);
    Configuration.Builder build = new Configuration.Builder();

    public Configuration getConfiguration(){
        return build.build();
    }

    public ControllerReader(){
        int min = 0,max = 0,attempts = 0;
        try(FileReader inputFile = new FileReader(file);
            BufferedReader buff = new BufferedReader(inputFile)){
            String line;
            
            while((line = buff.readLine())!=null){
                String[] strings = line.split(":");
                switch(strings[1]){
                    case "minimum":
                        min = Integer.parseInt(strings[2]);
                    break;
                    case "maximum":
                        max = Integer.parseInt(strings[2]);
                        break;
                    case "attempts":
                        attempts = Integer.parseInt(strings[2]);
                        break;
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
