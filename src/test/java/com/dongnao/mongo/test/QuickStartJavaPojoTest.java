package com.dongnao.mongo.test;


import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.entity.Address;
import com.mongodb.entity.Favorites;
import com.mongodb.entity.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.addEachToSet;

//原生java驱动 Pojo的操作方式
public class QuickStartJavaPojoTest {

	private static final Logger logger = LoggerFactory.getLogger(QuickStartJavaPojoTest.class);
	
    private MongoDatabase db;
    
    private MongoCollection<User> doc;
    
    private MongoClient client;
	
	
    @Before
    public void init(){
    	//编解码器的list（编解码器就是用来吧java的数据类型和mongodb的数据类型关联起来）
    	List<CodecRegistry> codecResgistes = new ArrayList<>();
    	//编解码器的list加入默认的编解码器集合
    	codecResgistes.add(MongoClient.getDefaultCodecRegistry());
    	//生成一个pojo的编解码器
    	CodecRegistry pojoProviders = CodecRegistries.
    			fromProviders(PojoCodecProvider.builder().automatic(true).build());
    	
    	codecResgistes.add(pojoProviders);
    	//通过编解码器的list生成编解码器注册中心
    	CodecRegistry registry = CodecRegistries.fromRegistries(codecResgistes);
    	
    	//把编解码器注册中心放入MongoClientOptions,MongoClientOptions可以理解为连接池的配置文件
    	MongoClientOptions build = MongoClientOptions.builder().
    			codecRegistry(registry).build();

    	ServerAddress serverAddress = new ServerAddress("192.168.124.151",27022);

		client = new MongoClient(serverAddress, build);

    	
//    	client = new MongoClient("192.168.1.142",27022);
    	db =client.getDatabase("lison");
    	doc = db.getCollection("users",User.class);
    }

    @Test
    public void insertDemo(){
    	User user = new User();
    	user.setUsername("cang");
    	user.setCountry("USA");
    	user.setAge(20);
    	user.setLenght(1.77f);
    	user.setSalary(new BigDecimal("6265.22"));
    	Address address1 = new Address("","");
    	address1.setaCode("411222");
    	address1.setAdd("sdfsdf");
    	user.setAddress(address1);
    	Favorites favorites1 = new Favorites();
    	favorites1.setCites(Arrays.asList("东莞","东京"));
    	favorites1.setMovies(Arrays.asList("西游记","一路范德萨范德萨范德萨西"));
    	user.setFavorites(favorites1);
    	
    	
    	User user1 = new User();
    	user1.setUsername("chen");
    	user1.setCountry("China");
    	user1.setAge(30);
    	user1.setLenght(1.77f);
    	user1.setSalary(new BigDecimal("6885.22"));
    	Address address2 = new Address("","");
    	address2.setaCode("411000");
    	address2.setAdd("我的地址2");
    	user1.setAddress(address2);
    	Favorites favorites2 = new Favorites();
    	favorites2.setCites(Arrays.asList("珠海","东京"));
    	favorites2.setMovies(Arrays.asList("东游记","一路多少分付付付东"));
    	user1.setFavorites(favorites2);
    	doc.insertMany(Arrays.asList(user,user1));
    	
    }
    
    @Test
    public void testDelete(){
    	
    	//delete from users where username = ‘lison’
    	
    	DeleteResult deleteMany = doc.deleteMany(eq("username", "lison"));
    	logger.info(String.valueOf(deleteMany.getDeletedCount()));
    	
    	//delete from users where age >8 and age <25
    	DeleteResult deleteMany2 = doc.deleteMany(and(gt("age",8),lt("age",25)));
    	logger.info(String.valueOf(deleteMany2.getDeletedCount()));
    }
    
    @Test
    public void testUpdate(){
    	//update  users  set age=6 where username = 'lison'
		User user=new User();
		user.setAddress(new Address("fdsafdsa","dfsadfsa"));
    	UpdateResult updateMany = doc.updateMany(eq("username", "cang"),
    			                  new Document("$set",user));
    	logger.info(String.valueOf(updateMany.getModifiedCount()));
    	
    	//update users  set favorites.movies add "小电影2 ", "小电影3" where favorites.cites  has "东莞"
    	UpdateResult updateMany2 = doc.updateMany(eq("favorites.cites", "东莞"), 
    			                                  addEachToSet("favorites.movies", Arrays.asList( "小电影2 ", "小电影3")));
    	logger.info(String.valueOf(updateMany2.getModifiedCount()));
    }
    
    @Test
    public void testFind(){
    	
    	final List<User> ret = new ArrayList<>();
    	Block<User> printBlock = new Block<User>() {
			@Override
			public void apply(User t) {
//				logger.info();
				System.out.println(t.getUsername());
				System.out.println(t.getSalary());
				ret.add(t);
			}
    		
		};
		
    	//select * from users  where favorites.cites has "东莞"、"东京"
		FindIterable<User> find = doc.find(all("favorites.cites", Arrays.asList("东莞","东京")));
		find.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));
		ret.removeAll(ret);

    	//select * from users  where username like '%s%' and (contry= English or contry = USA)
		String regexStr = ".*s.*";
		Bson regex = regex("username", regexStr);
		Bson or = or(eq("country","English"),eq("country","USA"));
		FindIterable<User> find2 = doc.find(and(regex,or));
		find2.forEach(printBlock);
		logger.info(String.valueOf(ret.size()));

    }
}
