package com.course.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/", description = "这是我全部的get方法")
@RequestMapping("v1")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这方法可以回去cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response){

        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }
    /**
     * 要求客户端携带cookies访问
     */

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须携带cookie信息回来！";

        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true"))
                return "这是一个需要携带cookies才能访问的请求！";
        }

        return "你必须携带cookie信息回来！";
    }


    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式url：key=value&key=value
     * 我们来模拟获取商品列表
     */

    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数请求get方法一", httpMethod = "GET")
    public  Map<String,Integer> getList(@RequestParam Integer start,
                                        @RequestParam Integer end){

        Map<String, Integer> myList = new HashMap<>();

        myList.put("鞋",500);
        myList.put("上衣",150);
        myList.put("长裤",200);

        return myList;
    }

    /**
     *第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */

    @RequestMapping(value = "get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数请求get的第二种方法",httpMethod = "GET")
    public Map myGetLsit(@PathVariable Integer start,
                         @PathVariable Integer end){

        Map<String, Integer> myList = new HashMap<>();

        myList.put("鞋",500);
        myList.put("上衣",150);
        myList.put("长裤",200);

        return myList;
    }



}
