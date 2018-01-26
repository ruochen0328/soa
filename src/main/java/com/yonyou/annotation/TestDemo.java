package com.yonyou.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/19 10:55
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestDemo {
    @Autowired
    private ProductService productService;
    @Test
    public void test(){
        Product product=new Product();
        product.setId("product");
        Map<String,String> map=new HashMap<String,String>();
        map.put("java","java");
        map.put("php","php");
        product.setMap(map);
        //productService.getProduct("woddddd",product);
        productService.testOther();
       /* SpelExpressionParser parser=new SpelExpressionParser();
        Expression expression = parser.parseExpression("#product.map");
        EvaluationContext ctx=new StandardEvaluationContext();
        ctx.setVariable("product",product);
        Object value = expression.getValue(ctx);
        System.out.println(value);*/
    }
}
