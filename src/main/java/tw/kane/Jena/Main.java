package tw.kane.Jena;

import tw.kane.osu4j.OsuClient;

import javax.security.auth.login.LoginException;

public class Main {

    public static Logger logger = new Logger("Main");
    public static OsuClient osuClient;

    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        if(token == null) {
            logger.e("No Token Provided!");
            return;
        }

//        String mongoUrl = System.getenv("MONGO_URL");
//        if(mongoUrl == null) {
//            logger.e("No database url provided");
//            return;
//        }

        String osuToken = System.getenv("OSU_TOKEN");
        if(osuToken == null) {
            logger.e("No osu token provided");
            return;
        }
        String osuId = System.getenv("OSU_ID");
        if(osuId == null) {
            logger.e("No osu id provided");
            return;
        }
        osuClient = new OsuClient(osuId, osuToken);

        logger.i("Connecting to database...");
//        Database.init(mongoUrl);
        logger.i("Database connected...");

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

        //Shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.w("Shutting Down...");
            Bot.shutdown();
        }));
    }
}
