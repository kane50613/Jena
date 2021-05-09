package tw.kane.Jena;

import javax.security.auth.login.LoginException;

public class Main {

    public static Logger logger = new Logger("Main");

    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        if(token == null || token.length() == 0) {
            logger.e("No Token Provided!");
            return;
        }

        try {
            logger.i("Starting Bot...");
            Bot.init(System.getenv().get("TOKEN"));
        } catch (LoginException | InterruptedException e) {
            logger.e(e);
        }
    }
}
