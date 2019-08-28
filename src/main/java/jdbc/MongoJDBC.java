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

import org.apache.log4j.Logger;

public class MongoJDBC {
    /*
     * @program: java-learning
     * @description: MongoDB的Java连接方式，参考资料：https://segmentfault.com/a/1190000012705470
     * @author: flowerlake
     * @create: 2019-08-22 20:51
     * @version: 1.0
     */

    private static Logger logger = Logger.getLogger(MongoJDBC.class);

    public void query() {

        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Zhihu_QA");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("problem_id_31430452");

            logger.info("Database Connected, get collection success");
            // FindIterable<Document> dbCursor = mongoCollection.find();

            // 有条件查询，使用Filter.eq方法
            FindIterable<Document> dbCursor = mongoCollection.find(Filters.eq("url", "https://www.zhihu.com/api/v4/answers/778636935"));
            // 这里获取到一个Iterable类型的对象后，需要通过iterator方法将其转换为可以迭代的迭代器，然后进行循环等操作
            MongoCursor<Document> iterator = dbCursor.iterator();

            if (iterator.hasNext()) {
                System.out.println("[Mongo query success] " + iterator.next());
            }
            mongoClient.close();
        } catch (Exception e) {
            logger.info("Connect mongo database Error: " + e);
        }
    }

    public static void main(String[] args) {
        MongoJDBC mongoJDBC = new MongoJDBC();
        mongoJDBC.query();
    }
}