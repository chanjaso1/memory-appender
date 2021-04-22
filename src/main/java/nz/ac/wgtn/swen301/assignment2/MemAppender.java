package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.log4j.*;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import sun.rmi.runtime.Log;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class MemAppender extends AppenderSkeleton {
    String name;
    long maxSize = 1000;
    long discardedLogs = 0;
    JSONLayout layout = new JSONLayout();


    LinkedList<LoggingEvent> loggingEvents = new LinkedList<LoggingEvent>(){
        /**
         * Override the add method for this list. Remove the first item of the list, the oldest, if the logging list has
         * the maximum capacity.
         * @param element -- The log event.
         */
        @Override
        public void addLast(LoggingEvent element) {

            if(loggingEvents.size() >= maxSize){
                loggingEvents.removeFirst();
                discardedLogs++;
            }
            super.add(element);
        }
    };


    /**
     * Return an unmodifiable list of the current logging events.
     * @return the loggingEvents list.
     */
    public List<LoggingEvent> getCurrentLogs(){
        return Collections.unmodifiableList(loggingEvents);
    }

    /**
     * Get the number of discarded logging events.
     * @return the number of discarded events.
     */
    public long getDiscardedLogCount(){
        return discardedLogs;
    }


    /**
     * Export all logging events to a JSON file.
     * @param fileName -- The name of the JSON file.
     */
    public void exportToJSON(String fileName) {
        if(!fileName.endsWith(".json"))  fileName += ".json";

        try{

            Writer writer = new FileWriter(fileName);

            writer.write("[\n");
            for(int i = 0; i < loggingEvents.size(); i++){
                if(i < loggingEvents.size()-1)
                    writer.write(layout.format(loggingEvents.get(i)) + ",\n");
                else
                    writer.write(layout.format(loggingEvents.get(i)));
            }
            writer.write("\n]\n");
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void append(LoggingEvent loggingEvent) {
        loggingEvents.addLast(loggingEvent);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
