package nz.ac.wgtn.swen301.assignment2;

public class loggedEvent {

    public String message;
    public long starttime;
    public String level;
    public String logger;
    public String thread;


    public loggedEvent(){
        
    }

    public String getMessage() {
        return message;
    }

    public long getStarttime() {
        return starttime;
    }
    public String getLevel() {
        return level;
    }

    public String getLogger() {
        return logger;
    }

    public String getThread() {
        return thread;
    }
}
