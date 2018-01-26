package com.pattern.builder;

/**
 * @author yangwshh@yonyou.com
 * @date 2018/1/26 10:54
 */
//可以参考com.mongodb.MongoClientOptions
//ClientOptions就是配置文件类，假如现在有两个配置：name和sex，sex要求有默认值“nan”
public class ClientOptions {
    //final修饰是要求这个配置文件一旦配置不许修改
    private final String name;
    private final String sex;
    //只提供get方法而不提供set方法，因为属性一旦配置不许修改
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    //创建builder静态内部类
    public static class Builder{
        //builder类中的属性要包含ClientOptions中的所有属性
        private String name;
        private String sex;
        //提供和属性名一样的方法
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder sex(String sex){
            this.sex=sex;
            return this;
        }
        //提供默认的构造方法，在这里把ClientOptions类中默认值设置进去
        //ClientOptions.builder()的时候会调用这个构造函数
        public Builder() {
            this.sex="nan";
        }
        //提供build()方法
        public ClientOptions build(){
            //要求ClientOptions提供对应的构造函数
            return new ClientOptions(this);
        }
    }
    //所有的值就是在这里设置进去的
    public ClientOptions(Builder builder) {
        this.name=builder.name;
        this.sex=builder.sex;
    }

    public static Builder builder(){
        return new Builder();
    }
}
