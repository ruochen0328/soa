package com.yonyou.dubbo.consumer;

import com.yonyou.hrcloud.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/11/27 14:37
 */
@RequestMapping(value = "/login")
@Controller
public class LoginController {
    @Autowired
    DemoService demoService;
    @RequestMapping(value = "/test/{path}")
    @ResponseBody
    public String test(@RequestParam String param, @PathVariable String path){
        return demoService.sayHello("sfdfsdfds");
    }
}
