package com.course.server;


import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {

    private static Cookie cookie;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password){

        if(username.equals("zhangsan") && password.equals("123456")){

            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功";
        }

        return "用户名或密码错误！";

    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){

        User user;

        Cookie[] cookies = request.getCookies();

        for(Cookie c : cookies){
            if(c.getName() == "login"
                    && c.getValue() == "true"
                    && u.getUserName() == "zhangsan"
                    && u.getPassWord() == "123456"
            ){
                 user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();

            }
        }
        return "参数不合法";
    }
}
