package ru.nsu.vorobev.task3.model;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final String res = "log4j.xml";
    private static boolean isInit = false;
    private static Logger info;
    private static boolean isLoggerEnabled = false;
    public static synchronized void init(){
        if(isInit){
            return;
        }
        byte[] buffer = new byte[1024];
        int readed = 0;
        try(InputStream config = Log.class.getResourceAsStream(res)){
            readed = config.read(buffer);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
        File file;
        try {
            file = File.createTempFile("logger",".tmp");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try(OutputStream out = new FileOutputStream(file);){
           out.write(buffer,0,readed);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        file.deleteOnExit();
        System.setProperty("log4j.configurationFile",file.getPath());
        info = LogManager.getLogger("log-info");
        isInit = true;
    }

    public static synchronized void enableLogger(){
        isLoggerEnabled = true;
    }

    public static synchronized void log(String message){
        if(!isLoggerEnabled || !isInit){
            return;
        }
        info.info(message);
    }
}
