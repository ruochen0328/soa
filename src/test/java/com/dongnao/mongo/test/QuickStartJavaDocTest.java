package com.dongnao.mongo.test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.*;

//原生java驱动 document的操作方式
public class QuickStartJavaDocTest {

	private static final Logger logger = LoggerFactory.getLogger(QuickStartJavaDocTest.class);
	
    private MongoDatabase db;
    //collection对应的是表    document对应的是表中的一条数据，如果过pojo就可以吧document换成pojo
    private MongoCollection<Document> collection;
    //内置连接池
    private MongoClient client;
	
	
    @Before
    public void init(){
    	MongoClientOptions mco=MongoClientOptions.builder().description("建造者模式").build();
		client =new MongoClient(new ServerAddress("192.168.124.151",27022),mco);
    	//client = new MongoClient("192.168.124.151",27022,mco);
    	db =client.getDatabase("lison");
		collection = db.getCollection("users");
    }
    
    @Test
    public void insertDemo(){
    	Document doc1 = new Document();
    	doc1.append("username", "cang");
    	doc1.append("country", "USA");
    	doc1.append("age", 20);
    	doc1.append("lenght", 1.77f);
    	doc1.append("salary", new BigDecimal("6565.22"));
    	
    	Map<String, String> address1 = new HashMap<String, String>();
    	address1.put("aCode", "0000");
    	address1.put("add", "xxx000");
    	doc1.append("address", address1);
    	
    	Map<String, Object> favorites1 = new HashMap<String, Object>();
    	favorites1.put("movies", Arrays.asList("aa","bb"));
    	favorites1.put("cites", Arrays.asList("东莞","东京"));
    	doc1.append("favorites", favorites1);
    	
    	Document doc2  = new Document();
    	doc2.append("username", "chen");
    	doc2.append("country", "China");
    	doc2.append("age", 30);
    	doc2.append("lenght", 1.77f);
    	doc2.append("salary", new BigDecimal("8888.22"));
    	Map<String, String> address2 = new HashMap<>();
    	address2.put("aCode", "411000");
    	address2.put("add", "我的地址2");
    	doc1.append("address", address2);
    	Map<String, Object> favorites2 = new HashMap<>();
    	favorites2.put("movies", Arrays.asList("东游记","一路向东"));
    	favorites2.put("cites", Arrays.asList("珠海","东京"));
    	doc2.append("favorites", favorites2);

		collection.insertMany(Arrays.asList(doc1,doc2));
    	
    }
    
    @Test
    public void testDelete(){
    	
    	//delete from users where username = ‘lison’
    	DeleteResult deleteMany = collection.deleteMany(eq("username", "lison"));
    	logger.info(String.valueOf(deleteMany.getDeletedCount()));
    	
    	//delete from users where age >8 and age <25
    	DeleteResult deleteMany2 = collection.deleteMany(and(gt("age",8),lt("age",25)));
    	logger.info(String.valueOf(deleteMany2.getDeletedCount()));
    }
    
    @Test
    public void testUpdate(){
    	//update  users(age,username)  set (6,"yang") where username = 'lison'
		Document document=new Document();
		document.append("username","yang");
		document.append("age",100);
    	UpdateResult updateMany = collection.updateMany(eq("username", "chen"),
    			                  new Document("$set",document));
    	logger.info(String.valueOf(updateMany.getModifiedCount()));
    	
    	//update users  set favorites.movies add "小电影2 ", "小电影3" where favorites.cites  has "东莞"
    	UpdateResult updateMany2 = collection.updateMany(eq("favorites.cites", "东莞"),
    			                                  addEachToSet("favorites.movies", Arrays.asList( "小电影2 ", "小电影3")));
    	logger.info(String.valueOf(updateMany2.getModifiedCount()));
    }
    
    @Test
    public void testFind(){
    	final List<Document> ret = new ArrayList<>();
    	Block<Document> printBlock = new Block<Document>() {
			@Override
			public void apply(Document t) {
				logger.info(t.toJson());
				ret.add(t);
			}
    		
		};   	
    	//select * from users  where favorites.cites has "东莞"、"东京"
		FindIterable<Document> find = collection.find(all("favorites.cites", Arrays.asList("东莞","东京")));
		find.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));
		ret.removeAll(ret);
    	
    	
    	//select * from users  where username like '%s%' and (contry= English or contry = USA)
		String regexStr = ".*s.*";
		Bson regex = regex("username", regexStr);
		Bson or = or(eq("country","English"),eq("country","USA"));
		FindIterable<Document> find2 = collection.find(and(regex,or));
		find2.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));

    }
    
	
	
	

}
