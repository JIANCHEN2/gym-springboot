package com.controller;


import com.guava.GuavaRateLimiterService;
import com.guava.impl.GuavaRateLimiterServiceImpl;
import com.modelGym.Trainer;
import com.service.GymService;
import com.service.TrainerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.NumberUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class Searchcontroller {
    @Autowired
    TrainerService ts;
    @Autowired
    GymService gs;

    //限流器
    @Autowired
    GuavaRateLimiterServiceImpl gr;
    /*List<Gym> g1list=gs.*/
    @GetMapping("/info")
    @SneakyThrows(NoSuchElementException.class)
    public String gogogo(@RequestParam("gym") String gym,
                         @RequestParam("coach") String coach,
                         Model mod) {
        String str = gr.executeSeckill();
        System.out.println(str + "," + new Date());
        while (str == "被限制") {
            str = gr.executeSeckill();
            System.out.println(str + "," + new Date());
        }
        Object a;
        List<Trainer> glist = ts.pageQuery(1, 5000);


        if (glist == null)
            return "search";
        if (((gym == null || gym.equals("")) && (coach == null || coach.equals(""))))
            mod.addAttribute("hello", glist);
        if (!gym.equals("") && (coach == null || coach.equals(""))) {
            /*System.out.println(ts.toString());*/
            if (isNumeric(gym)) {
                a = Integer.parseInt(gym);
            } else
                a = gym;

            List<Trainer> trainerOfGym = null;

            if (ts.findTrainerOfGym(gs, a, 1, 5000) != null)
                mod.addAttribute("hello2", ts.findTrainerOfGym(gs, a, 1, 5000));
            return "search2";
        }
        if (!gym.equals("") && !coach.equals("")) {
            if (isNumeric(gym)) {
                a = Integer.parseInt(gym);
            } else
                a = gym;
            List<Trainer> glist4=null;
            if (ts.findTrainerOfGym(gs, a, 1, 5000) != null) {
                glist4 = ts.findTrainerOfGym(gs, a, 1, 5000);
            }
            LinkedList<Trainer> glist5 = new LinkedList<>();
            if (isNumeric(coach)) {
                a = Integer.parseInt(coach);
            } else
                a = coach;
            List<Trainer> glist3 = ts.query(a);
            /*mod.addAttribute("hello",glist3);*/
            for (Trainer ts1 : glist3) {
                if (glist4 != null) {
                    for (Trainer gs1 : glist4) {
                        if ((int) ts1.getId() == (int) gs1.getId())
                            glist5.add(ts1);
                        break;
                    }
                }
            }
            mod.addAttribute("hello", glist5);
        }
        if ((gym == null || gym.equals("")) && !coach.equals("")) {
            if (isNumeric(coach)) {
                a = Integer.parseInt(coach);
            } else
                a = coach;
            List<Trainer> glist3 = ts.query(a);
            mod.addAttribute("hello", glist3);
        }

        return "search";
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
