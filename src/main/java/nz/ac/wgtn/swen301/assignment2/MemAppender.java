package nz.ac.wgtn.swen301.assignment2;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The MemAppender is the appender for logging events and exporting each event in a .json file.
 */
public class MemAppender extends AppenderSkeleton implements MemAppenderMBean{
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


     public MemAppender(String name){
        this.name = name;
     }

     public MemAppender(){

     }


    /**
     * Return an unmodifiable list of the current logging events.
     * @return the loggingEvents list.
     */
    public List<LoggingEvent> getCurrentLogs(){
        return Collections.unmodifiableList(loggingEvents);
    }

    @Override
    public String[] getLogs() {
        PatternLayout patternLayout = new PatternLayout();
        //Set the default conversion pattern
      //  patternLayout.setConversionPattern("%r [%t] %p %c %x - %m%n"); //Default based off https://www.tutorialspoint.com/log4j/log4j_patternlayout.htm
        String[] array = new String[loggingEvents.size()];

        for (int i = 0; i < loggingEvents.size(); i++) {
            array[i] = patternLayout.format(loggingEvents.get(i));
        }


        return  array;
    }

    @Override
    public long getLogCount() {
        return loggingEvents.size();
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
    @Override
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
     * Append a logging event to the loggingEvents list.
     * @param loggingEvent -- The logging event to be inserted.
     */
    @Override
    public void append(LoggingEvent loggingEvent) {
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
