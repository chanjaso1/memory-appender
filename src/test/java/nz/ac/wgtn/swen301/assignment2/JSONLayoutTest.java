package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;


public class JSONLayoutTest {

    public Logger logger;

    @Before
    public void init(){
         logger = Logger.getLogger("Test Logger");
    }


    @Test
    public void test_JSONLayout(){
        LoggingEvent loggingEvent = new LoggingEvent("Test", logger, Level.ERROR ,"This is a message",  new Throwable());
        String formattedLe = new JSONLayout().format(loggingEvent);

        assert (formattedLe.equals("{\n" +
                "  \"level\": \"ERROR\",\n" +
                "  \"logger\": \"Test Logger\",\n" +
                "  \"starttime\": \"" + loggingEvent.getTimeStamp() + "\",\n" +
                "  \"thread\": \"main\",\n" +
                "  \"message\": \"This is a message\"\n" +
                "}"));
    }

    @Test
    public void test_sameValues(){
        LoggingEvent loggingEvent = new LoggingEvent("Test2", logger, Level.ERROR ,"This is a message",  new Throwable());
        String formattedLe = new JSONLayout().format(loggingEvent);
        loggedEvent newLoggingEvent  = new Gson().fromJson(formattedLe, loggedEvent.class);

        //assert that the values are the same when the formatted JSON string is returned as an object.
        assert (loggingEvent.getMessage().toString().equals(newLoggingEvent.getMessage()));
        assert (loggingEvent.getLevel().toString().equals(newLoggingEvent.getLevel()));
        assert (loggingEvent.getLoggerName().equals(newLoggingEvent.getLogger()));
        assert (loggingEvent.getThreadName().equals(newLoggingEvent.getThread()));
        assert (loggingEvent.getTimeStamp() == newLoggingEvent.getStarttime());
    }

    @Test
    public void test_JSONLayout3(){
        Logger secondLogger = Logger.getLogger("Second logger");
        LoggingEvent firstEvent = new LoggingEvent("first", logger, Level.ERROR ,"This is a message",  new Throwable());
        LoggingEvent secondEvent = new LoggingEvent("second", secondLogger, Level.ERROR ,"This is a message",  new Throwable());

        assert firstEvent.getLoggerName().equals("Test Logger");
        assert secondEvent.getLoggerName().equals("Second logger");
    }
}
