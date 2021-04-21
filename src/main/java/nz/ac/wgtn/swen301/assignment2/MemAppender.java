package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.log4j.Layout;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

public class MemAppender {

    private JSONLayout layout = new JSONLayout();

    LinkedList<LoggingEvent> loggingEvents = new LinkedList<LoggingEvent>(){
        /**
         * Override the add method for this list. Remove the first item of the list, the oldest, if the logging list has
         * the maximum capacity.
         * @param element -- The log event.
         */
        @Override
        public void addLast(LoggingEvent element) {
            if(loggingEvents.size() > maxSize){
                loggingEvents.removeFirst();
                discardedLogs++;
            }
            super.add(element);
        }
    };
    String name;
    long maxSize = 1000;
    long discardedLogs = 0;


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
    public void exportToJSON(String fileName){
        try{

            if(!fileName.endsWith(".json"))  fileName += ".json";
            Writer writer = new FileWriter(fileName);
            new Gson().toJson(loggingEvents, writer);
            ArrayList<String> JSONStrings = new ArrayList<String>();
            for(LoggingEvent le : loggingEvents){
                JSONStrings.add(JSONLayout.format(le));
            }
            new Gson().toJson(JSONStrings, writer);

         //   System.out.println(new Gson().toJson(loggingEvents));
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
