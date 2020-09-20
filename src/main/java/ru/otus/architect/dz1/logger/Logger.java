package ru.otus.architect.dz1.logger;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static volatile Logger instance;
    private static String logFile = "Logger.txt";

    private Logger(){
    };

    public static Logger getInstance() {
        Logger result = instance;
        if (result != null) {
            return result;
        }
        synchronized(Logger.class) {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }
    }

    public static void info(String message){
        try (final FileWriter logWriter = new FileWriter(Logger.logFile, true)) {
            logWriter.write(message.toCharArray());
            logWriter.close();
        }catch(IOException e){
            System.err.println("ERROR: Could not write to log file");
        }
    }

}
