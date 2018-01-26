package com.yonyou.annotation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/19 10:59
 */
@Service
@Mycache(key="test",cacheName = "cachename")
public class ProductServiceImpl implements ProductService {
    @Override
    @Mycache(key = "#key",cacheName = "#product.map['java']")
    public String getProduct(String key,Product product) {
        return "-----------";
    }

    @Override
    public void testOther() {
        System.out.println("other");
    }

    public static void main(String[] args) {

    }


    @Test
    public void helloWorld() {
        ExpressionParser parser = new SpelExpressionParser();
        //------------使用SpEL创建数组-----------
        //------------使用SpEL访问List集合、Map集合的元素-----------
        List<String> list=new ArrayList<String>();
        list.add("java");
        list.add("PHP");
        Map<String,Double> map=new HashMap<String,Double>();
        map.put("math", 78.8);
        map.put("chinese", 98.6);
        map.put("english", 92.2);
        // 创建一个EvaluationContext对象，作为SpEL解析变量的上下文
        EvaluationContext ctx = new StandardEvaluationContext();
        // 设置两个变量
        ctx.setVariable("list", list);
        ctx.setVariable("myMap", map);
        //修改并访问集合
        parser.parseExpression("#list[0]").setValue(ctx, "JavaEE");
        parser.parseExpression("#myMap['math']").setValue(ctx, 99.9);
        System.out.println("List集合中第一个元素为："+parser.parseExpression("#list[0]").getValue(ctx));
        System.out.println("map中修改后的value为："+parser.parseExpression("#myMap['math']").getValue(ctx));
    }
    @Test
    public void mytest(){
        SpelExpressionParser parser=new SpelExpressionParser();
        Expression expression = parser.parseExpression("#id");
        EvaluationContext ctx=new StandardEvaluationContext();
        ctx.setVariable("id","xxxx");
        Object value = expression.getValue(ctx);
        System.out.println(value);
        expression.setValue(ctx,"dddd");
        Object ddd = expression.getValue(ctx);
        System.out.println(ddd);
    }
}
