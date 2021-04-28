package nz.ac.wgtn.swen301.assignment2;

/**
 * The loggedEvent class is hold the converted JSON strings, loggingEvents, as a representation of the
 * loggingEvents.
 */
public class loggedEvent {

    public String message;
    public long starttime;
    public String level;
    public String logger;
    public String thread;


    public loggedEvent(){

    }

    /**
     * Get the message.
     * @return the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the start time.
     * @return the start time.
     */
    public long getStarttime() {
        return starttime;
    }

    /**
     * Get the level of the event.
     * @return the level.
     */
    public String getLevel() {
        return level;
    }

    /**
     * Get the logger.
     * @return the logger.
     */
    public String getLogger() {
        return logger;
    }

    /**
     * Get the thread.
     * @return the thread.
     */
    public String getThread() {
        return thread;
    }
}
