package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.HashMap;

public class JSONLayout extends Layout {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();



    /**
     * Format a logging event into a JSON string.
     * @param loggingEvent -- The logging event to be converted.
     * @return the JSON string.
     */
    @Override
    public  String format(LoggingEvent loggingEvent){
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("logger", loggingEvent.getLoggerName());
        map.put("level", loggingEvent.getLevel().toString());
        map.put("starttime", String.valueOf(loggingEvent.getStartTime()));
        map.put("thread", loggingEvent.getThreadName());
        map.put("message", String.valueOf(loggingEvent.getMessage().toString()));

        return gson.toJson(map);
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {

    }
}
