package tw.kane.Jena;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    String source;

    public Logger(String source) {
        this.source = source;
    }

    public void i(String message) {
        System.out.printf("[+][%s][%s] %s%n",
                new SimpleDateFormat("hh:mm:ss").format(new Date()),
                source,
                message
        );
    }

    public void w(String message) {
        System.out.printf("[*][%s][%s] %s%n",
                new SimpleDateFormat("hh:mm:ss").format(new Date()),
                source,
                message
        );
    }

    public void e(String message) {
        System.out.printf("[-][%s][%s] %s%n",
                new SimpleDateFormat("hh:mm:ss").format(new Date()),
                source,
                message
        );
    }

    public void e(Exception exception) {
        System.out.printf("[-][%s][%s] %s%n",
                new SimpleDateFormat("hh:mm:ss").format(new Date()),
                source,
                exception.getMessage()
        );
    }
}
