package com.yonyou.wether;

import com.yonyou.soa.rpc.http.HttpRequest;
import com.yonyou.wether.utils.JsoupUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/6 9:41
 */

public class WetherSpider {
    private final static String URL="https://www.baidu.com/";
    public static void main(String[] args) {
        Document docWithPC = JsoupUtils.getDocWithPC(URL);
        System.out.println(docWithPC);
    }
}
