package com.yonyou.annotation.elparser;

import com.yonyou.annotation.Product;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/19 15:27
 */

public class ElParser {
    public static Object parse(String key,String [] parseNames,Object [] args){
        ExpressionParser parser=new SpelExpressionParser();
        Expression expression = parser.parseExpression(key);
        EvaluationContext ctx=new StandardEvaluationContext();
        for (int i = 0; i < parseNames.length; i++) {
            ctx.setVariable(parseNames[i],args[i]);
        }
        return  expression.getValue(ctx);
    }

    public static void main(String[] args) {
        String parseNames []={"key","product"};
        Product product=new Product();
        product.setId("product");
        Map<String,String> map=new HashMap<String,String>();
        map.put("java","java");
        map.put("php","php");
        product.setMap(map);
        Object [] argss={"test",product};
        Object parse = parse("#product.map[]", parseNames, argss);
        System.out.println(parse);
    }
}
