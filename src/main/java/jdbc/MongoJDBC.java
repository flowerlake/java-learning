package jdbc;


import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.logging.Filter;

public class MongoJDBC {
    /*
     * @program: java-learning
     * @description: TODO
     * @author: flowerlake
     * @create: 2019-08-22 20:51
     * @version: 1.0
     */

    public void test() {
        System.out.println("test");
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Zhihu_QA");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("problem_id_31430452");
            FindIterable<Document> dbCursor = mongoCollection.find(Filters.eq("id", "778636935"));

            // 这里获取到一个Iterable类型的对象后，需要通过iterator方法将其转换为可以迭代的迭代器，然后进行循环等操作
            MongoCursor<Document> iterator = dbCursor.iterator();
            // TODO: 查询为成功
            if (iterator.hasNext()) {
                System.out.println("search success");
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            System.out.println("connect mongo database error: " + e);
        }

    }
}

class Main {
    public static void main(String[] args) {
        MongoJDBC mongoJDBC = new MongoJDBC();
        mongoJDBC.test();
    }
}
