package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import org.apache.log4j.spi.LoggingEvent;
import sun.plugin2.util.PojoUtil;

public class JSONLayout {


    private static Gson gson = new Gson();

    /**
     * Format a logging event into a JSON string.
     * @param loggingEvent -- The logging event to be converted.
     * @return the JSON string.
     */
    public static String format(LoggingEvent loggingEvent){
        System.out.println(gson.toJson(loggingEvent));
        return gson.toJson(loggingEvent);
    }

}
