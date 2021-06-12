package tw.kane.Jena;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Database {
    public static MongoClient client;

    public static void init(String url) {
        if(client != null) return;

        client = MongoClients.create(url);
    }
}
