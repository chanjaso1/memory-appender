package nz.ac.wgtn.swen301.assignment2;

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * JSONLayoutTest is a class that tests the JSONLayout class.
 * This class contains test cases testing different aspects of code in the class.
 */
public class JSONLayoutTest {

    public Logger logger;

    /**
     * Initialize the logger before each test.
     */
    @Before
    public void init(){
         logger = Logger.getLogger("Test Logger");
    }

    /**
     * Check that the format of a string, in a JSON format, is correct.
     */
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

    /**
     * Create 3 objects from a successfully converted JSON string. These objects must have the same values as the
     * pre-converted objects.
     */
    @Test
    public void test_sameValues(){
        LoggingEvent loggingEvent = new LoggingEvent("Test1", logger, Level.ERROR ,"This is a message",  new Throwable());
        LoggingEvent loggingEvent2 = new LoggingEvent("Test2", Logger.getLogger("Second Logger"), Level.WARN ,"This is the second message",  new Throwable());
        LoggingEvent loggingEvent3 = new LoggingEvent("Test3", Logger.getLogger("Third Logger"), Level.INFO ,"This is the third message",  new Throwable());

        JSONLayout layout = new JSONLayout();
        loggedEvent newLoggingEvent  = new Gson().fromJson(layout.format(loggingEvent), loggedEvent.class);
        loggedEvent newLoggingEvent2  = new Gson().fromJson(layout.format(loggingEvent2), loggedEvent.class);
        loggedEvent newLoggingEvent3 = new Gson().fromJson(layout.format(loggingEvent3), loggedEvent.class);



        //assert that the values are the same when the formatted JSON string is returned as an object.
        assert (loggingEvent.getMessage().toString().equals(newLoggingEvent.getMessage()));
        assert (loggingEvent.getLevel().toString().equals(newLoggingEvent.getLevel()));
        assert (loggingEvent.getLoggerName().equals(newLoggingEvent.getLogger()));
        assert (loggingEvent.getThreadName().equals(newLoggingEvent.getThread()));
        assert (loggingEvent.getTimeStamp() == newLoggingEvent.getStarttime());

        assert (loggingEvent2.getMessage().toString().equals(newLoggingEvent2.getMessage()));
        assert (loggingEvent2.getLevel().toString().equals(newLoggingEvent2.getLevel()));
        assert (loggingEvent2.getLoggerName().equals(newLoggingEvent2.getLogger()));
        assert (loggingEvent2.getThreadName().equals(newLoggingEvent2.getThread()));
        assert (loggingEvent2.getTimeStamp() == newLoggingEvent2.getStarttime());

        assert (loggingEvent3.getMessage().toString().equals(newLoggingEvent3.getMessage()));
        assert (loggingEvent3.getLevel().toString().equals(newLoggingEvent3.getLevel()));
        assert (loggingEvent3.getLoggerName().equals(newLoggingEvent3.getLogger()));
        assert (loggingEvent3.getThreadName().equals(newLoggingEvent3.getThread()));
        assert (loggingEvent3.getTimeStamp() == newLoggingEvent3.getStarttime());


    }

    /**
     * Create two logging events with different loggers and levels. Check that these two logging events are different
     * from each other.
     */
    @Test
    public void test_JSONLayout3(){
        Logger secondLogger = Logger.getLogger("Second logger");
        LoggingEvent firstEvent = new LoggingEvent("first", logger, Level.ERROR ,"This is a message",  new Throwable());
        LoggingEvent secondEvent = new LoggingEvent("second", secondLogger, Level.ERROR ,"This is a message",  new Throwable());

        assert firstEvent.getLoggerName().equals("Test Logger");
        assert secondEvent.getLoggerName().equals("Second logger");
    }


    @Test
    public void test_unusedMethods(){
        assert (!new JSONLayout().ignoresThrowable());
        new JSONLayout().activateOptions();
    }
}
