package com.controller;


import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

@Slf4j
@RestController
public class loginFluxcontroller {
    @Autowired
    UserService us;
    @RequestMapping(value = "/")
    public Mono<String> index(WebRequest webRequest, HttpServletResponse response){
//        System.out.println(logincontroller.class.getClassLoader().getResource("templates/login.html").getPath());
        long lastModified = new File(loginFluxcontroller.class.getClassLoader().getResource("templates/login.html").getPath()).lastModified();
//        System.out.println(lastModified);
        if (webRequest.checkNotModified(lastModified)) {
//            System.out.println("Not Modify");
            // 2. shortcut exit - no further processing necessary
            return null;
        }

        // 3. or otherwise further request processing, actually preparing content
        response.addHeader("Cache-Control", "max-age=60");
        response.addHeader("Last-Modified",String.valueOf(lastModified));
        return Mono.create(monoSink -> monoSink.success("login"));
    }

    //    @Bean
//    public RouterFunction<ServerResponse> webFlux(@RequestParam("username") String username,
//                                                  @RequestParam("password") String password,
//                                                  Model mod, HttpSession hs) {
//        return RouterFunctions.route(RequestPredicates.GET("/home"), request -> {
//            if(us.login(username,password)[0].equals("login success"))
//            {
//                hs.setAttribute("user",us.login(username,password)[1]);
//                return Mono.create(monoSink -> monoSink.success("search"));
//            }
//            else if(us.login(username,password)[0].equals("username or password error"))
//            {
//                mod.addAttribute("msg","账号或者密码有误");
//                return Mono.just("login");
//            }
//            return Mono.just("login");
//        });
//    }
/*
    @RequestMapping(value="/user/login",method= RequestMethod.POST)
*/
    @PostMapping(value="/home")
    public Mono<String> login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model mod, HttpSession hs){


        if(us.login(username,password)[0].equals("login success"))
        {
            hs.setAttribute("user",us.login(username,password)[1]);
            return Mono.create(monoSink -> monoSink.success("search"));
        }
        else if(us.login(username,password)[0].equals("username or password error"))
        {
            mod.addAttribute("msg","账号或者密码有误");
            return Mono.create(monoSink -> monoSink.success("login"));
        }
        return Mono.create(monoSink -> monoSink.success("login"));
    }

}

//import com.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.File;
//import java.util.Map;
//
//@Controller
//@EnableAutoConfiguration
//public class logincontroller {
//    @Autowired
//    UserService us;
//    @RequestMapping(value = "/")
//    public String index(WebRequest webRequest, HttpServletResponse response){
////        System.out.println(logincontroller.class.getClassLoader().getResource("templates/login.html").getPath());
//        long lastModified = new File(logincontroller.class.getClassLoader().getResource("templates/login.html").getPath()).lastModified();
////        System.out.println(lastModified);
//        if (webRequest.checkNotModified(lastModified)) {
////            System.out.println("Not Modify");
//            // 2. shortcut exit - no further processing necessary
//            return null;
//        }
//
//        // 3. or otherwise further request processing, actually preparing content
//        response.addHeader("Cache-Control", "max-age=60");
//        response.addHeader("Last-Modified",String.valueOf(lastModified));
//        return "login";
//    }
///*
//    @RequestMapping(value="/user/login",method= RequestMethod.POST)
//*/
//    @PostMapping(value="/home")
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("password") String password,
//                        Model mod, HttpSession hs){
//
//
//        if(us.login(username,password)[0].equals("login success"))
//        {
//            hs.setAttribute("user",us.login(username,password)[1]);
//            return "search";
//        }
//        else if(us.login(username,password)[0].equals("username or password error"))
//        {
//            mod.addAttribute("msg","账号或者密码有误");
//            return "login";
//        }
//        return "login";
//    }
//}
