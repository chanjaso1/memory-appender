package nz.ac.wgtn.swen301.assignment2;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * MemAppenderTest is a class that tests the MemAppender class.
 * This class contains test cases testing different aspects of code in the class.
 */
public class MemAppenderTest {

    public Logger logger;

    /**
     * Initialize the logger before each test.
     */
    @Before
    public void init(){
        logger = Logger.getLogger("Test Logger");
    }

    /**
     * Get the discarded log count which should be 0 as none have been discarded.
     */
    @Test
    public void test_getDiscardedLogCount(){
        MemAppender memAppender =  new MemAppender();
        assert memAppender.getDiscardedLogCount() == 0;
    }

    /**
     * Get the discarded count.
     * The count should be 1 if one log has been discarded.
     */
    @Test
    public void test_getDiscardedLogCount2(){
        MemAppender memAppender =  new MemAppender();
        assert memAppender.getDiscardedLogCount() == 0 : "The discarded logs should be empty";
        for (int i = 0; i < 1001; i++) { //Add 1001 logging events
            memAppender.append(new LoggingEvent("Test", logger, Level.ERROR ,"This is logger #" +  i,  new Throwable()));
        }
        assert memAppender.loggingEvents.size() == memAppender.maxSize;
        assert memAppender.getDiscardedLogCount() == 1 : "There should be 1 discarded log";
    }

    /**
     * Get the current logs and then modify.
     * @throws UnsupportedOperationException if the list was modified
     */
    @Test
    public void test_getCurrentLogs(){
        MemAppender memAppender =  new MemAppender();
        assert memAppender.loggingEvents.size() == 0 : "The logging events should be empty";
        try {
            memAppender.getCurrentLogs().add(null);
            assert false : "The current logs cannot be modified.";
        }catch(UnsupportedOperationException e){
            //If caught, this test passes.
        }
    }

    /**
     * Check that a logging event can be exported. This means that a logging event must be properly formatted as a JSON
     * string and then written in a .json file.
     */
    @Test
    public void test_exportToJSON() {
        MemAppender memAppender =  new MemAppender();
        memAppender.append(new LoggingEvent("Test", logger, Level.ERROR ,"This is logger #1",  new Throwable()));
        memAppender.exportToJSON("testFile.json");
        assert new File("testFile.json").delete() : "The file should be deleted after the test";
    }

    /**
     * Check that multiple logging events can be exported. A variety of logging events are formatted and written in the
     * correct JSON syntax in the file.
     */
    @Test
    public void test_exportToJSON2() {
        MemAppender memAppender =  new MemAppender();

        //Add multiple logging events
        memAppender.append(new LoggingEvent("Test", logger, Level.ERROR ,"This is logger #1",  new Throwable()));
        memAppender.append(new LoggingEvent("Test", Logger.getLogger("A logger for warning"), Level.WARN ,"This is logger #2",  new Throwable()));
        memAppender.append(new LoggingEvent("Test", Logger.getLogger("A logger for information"), Level.INFO ,"This is logger #3",  new Throwable()));

        memAppender.exportToJSON("testFile");
        assert new File("testFile.json").delete() : "The file should be deleted after the test";
    }

    /**
     * Get the name of the appender, set a new one, then check that it has been set.
     */
    @Test
    public void test_getAndSetName() {
        MemAppender memAppender =  new MemAppender();
        assert memAppender.getName() == null;
        memAppender.setName("Test appender");
        assert memAppender.getName().equals("Test appender");
    }
}

