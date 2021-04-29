package nz.ac.wgtn.swen301.assignment2.example;

import nz.ac.wgtn.swen301.assignment2.MemAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.UUID;

public class LogRunner {

    /**
     * Based on https://www.cleantutorials.com/jconsole/how-jmx-works-monitoring-your-java-application-remotely?fbclid=IwAR0InE3xP24WB2vKsL0CGVTwYClZ1Ae6pAu-H6ISE7OLaz_dyBjBxbbcZ58
     * This method connects to the server and monitors the given Mbean.
     * @param args
     * @throws MalformedObjectNameException
     * @throws InstanceAlreadyExistsException
     * @throws MBeanRegistrationException
     * @throws NotCompliantMBeanException
     */
    public static void main(String[] args) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException,
            NotCompliantMBeanException {

        MemAppender appender = new MemAppender("MemAppenderMBean:type=MemAppender");                       //Define the appender.
        MBeanServer server = ManagementFactory.getPlatformMBeanServer(); // Get an MBean Server instance.
        ObjectName name = new ObjectName(appender.getName()); // Create an object, which is the MBean.


        server.registerMBean(appender, name); // Register the MBean to the MBean Server.
        try {
            //For every second for 2 minutes, append a logging event in the appender.
            for (int i = 0; i < 120; i++) {
                appender.append(randomizeEvent());
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Randomize a logging event.
     * @return the logging event.
     */
    public static LoggingEvent randomizeEvent(){
        String message  = UUID.randomUUID().toString().replace("-","");
        double random = Math.random();
        Level level;
        if(random < 0.33 )      level = Level.INFO;
        else if(random < 0.66 ) level = Level.WARN;
        else                    level = Level.ERROR;
        return new LoggingEvent("Test", Logger.getLogger("This is an mbean logger"), level , "Message: " + message,  new Throwable());

    }
}
