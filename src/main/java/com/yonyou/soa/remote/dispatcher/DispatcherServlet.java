package com.yonyou.soa.remote.dispatcher;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.soa.configbean.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/23 10:01
 */

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject requestparam=httpProcess(req,resp);
        String serviceId = requestparam.getString("serviceId");
        String methodName = requestparam.getString("methodName");
        JSONArray paramTypes = requestparam.getJSONArray("paramTypes");
        // 这个对应的方法参数
        JSONArray methodParamJa = requestparam.getJSONArray("methodParams");
        Object [] args=new Object[methodParamJa.size()];
        if (!CollectionUtils.isEmpty(methodParamJa)){
            for (int i = 0; i < methodParamJa.size(); i++) {
                args[i]=methodParamJa.get(i);
            }
        }
        ApplicationContext applicationContext = Service.getApplicationContext();
        //生产者中的服务实例，和对应的bean的id一致，消费者是配置在Reference标签中的ref
        Object serviceBean = applicationContext.getBean(serviceId);
        //获取到要调用的实例bena的方法
        Method method=getMethod(serviceBean,methodName,paramTypes);
        if (method!=null){
            try {
                Object result = method.invoke(serviceBean, args);
                PrintWriter pw= resp.getWriter();
                pw.write(result.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
             new RuntimeException("method "+methodName+" cannot be found");
        }
    }

    private Method getMethod(Object serviceBean, String methodName, JSONArray paramTypes) {
        Method[] methods = serviceBean.getClass().getMethods();
        List<Method> retMethods=new ArrayList<Method>();
        for(Method method:methods){
            if (method.getName()==methodName){
                retMethods.add(method);
            }
        }
        if (retMethods.size()<=0){
            new RuntimeException("method "+methodName+" in "+serviceBean.getClass().getName()+" cannot be find");
        }else if (retMethods.size()==1){
            return retMethods.get(0);
        }else {
            //根据方法的入参去查找调用的方法
            boolean isSameType=false;
            ruochen:for (int i=0;i<retMethods.size();i++){
                Method method=retMethods.get(i);
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length!=paramTypes.size()){
                    continue;
                }else{
                    //参数个数相同再对比参数类型
                    for (int j = 0; j < parameterTypes.length; j++) {
                        if (parameterTypes[i].toString().contains(paramTypes.getString(i))){
                            isSameType=true;
                        }else{
                            isSameType=false;
                        }
                        if (isSameType=false){
                            continue ruochen;
                        }
                    }
                    if (isSameType){
                        return method;
                    }
                }
            }
        }
        return null;
    }

    private JSONObject httpProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            ServletInputStream is = req.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuffer sb=new StringBuffer();
            String line;
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            if (sb.toString().length()<=0){
                return null;
            }else{
                return JSONObject.parseObject(sb.toString());
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
