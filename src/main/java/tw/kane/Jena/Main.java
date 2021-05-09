package tw.kane.Jena;

import javax.security.auth.login.LoginException;

public class Main {

    public static Logger logger = new Logger("Main");

    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        if(token == null) {
            logger.e("No Token Provided!");
            return;
        }

        try {
            logger.i("Starting Bot...");
            logger.i(System.getenv("PREFIX") != null ? "using prefix provided " + System.getenv("PREFIX") : "using default prefix " + Config.DEFAULT_PREFIX);
            Bot.init(
                    System.getenv().get("TOKEN"),
                    System.getenv("PREFIX") != null ? System.getenv("PREFIX") : Config.DEFAULT_PREFIX
            );
        } catch (LoginException | InterruptedException e) {
            logger.e(e);
        }
    }
}
