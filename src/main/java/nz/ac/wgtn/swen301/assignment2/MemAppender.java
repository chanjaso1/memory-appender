package nz.ac.wgtn.swen301.assignment2;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The MemAppender is the appender for logging events and exporting each event in a .json file.
 */
public class MemAppender extends AppenderSkeleton {
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
            System.out.println("There was a writing error to " + fileName);
        }
    }

    /**
     * Set the name of the appender.
     * @param name -- The name for the appender.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Append a logging event to the loggingEvents list.
     * @param loggingEvent -- The logging event to be inserted.
     */
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
