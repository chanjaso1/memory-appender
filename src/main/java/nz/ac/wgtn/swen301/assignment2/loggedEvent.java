package nz.ac.wgtn.swen301.assignment2;

public class loggedEvent {

    public String message;
    public long starttime;
    public String level;
    public String logger;
    public String thread;


    public loggedEvent(String message, long timeStamp, String level, String logger, String thread) {
        this.message = message;
        this.starttime = timeStamp;
        this.level = level;
        this.logger = logger;
        this.thread = thread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long timeStamp) {
        this.starttime = timeStamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }
}
