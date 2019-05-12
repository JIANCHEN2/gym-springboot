package com.controller;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

@Controller
public class rigistercontroller {
    @Autowired
    UserService us1;

    @RequestMapping("/register")
    public String index(WebRequest webRequest, HttpServletResponse response) {
        long lastModified = new File(logincontroller.class.getClassLoader().getResource("templates/register.html").getPath()).lastModified();
//        System.out.println(lastModified);
        if (webRequest.checkNotModified(lastModified)) {
//            System.out.println("Not Modify");
            // 2. shortcut exit - no further processing necessary
            return null;
        }
        // 3. or otherwise further request processing, actually preparing content
        response.addHeader("Cache-Control", "max-age=60");
        response.addHeader("Last-Modified",String.valueOf(lastModified));
        return "register";
    }

    @PostMapping(value = "/new customer")
    public String register(@RequestParam("username") String username,
                           @RequestParam("pwd") String pwd,
                           Model mod, HttpSession hs
                           ) {
        if (us1.register(username, pwd).equals("Username has been registered!")) {
            mod.addAttribute("msg","账号已注册");

            return "register";
        } else
        {
            hs.setAttribute("user",us1.login(username, pwd)[1]);
            return "search";
        }

    }


}
