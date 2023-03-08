package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FirstController {
//    @RequestMapping("/hello")
//    String hello() {
//        return "First Spring Boot 3 Application!";
//    }
    @GetMapping("/bbj")
    public String jbb(Model model){
        String name = "张三的谷歌";
        model.addAttribute("name",name);
        List<News> list = new ArrayList<>();
        list.add(new News("1.jpg","ajjjjjjjjjjj"));
        list.add(new News("2.jpg","bjjjjjjjjjjj"));
        list.add(new News("3.jpg","cjjjjjjjjjjj"));
        list.add(new News("4.jpg","djjjjjjjjjjj"));
        list.add(new News("5.jpg","ejjjjjjjjjjj"));
        model.addAttribute("list",list);

        return "abc";

    }
//    @GetMapping("/index")
//    public String indexx(Model model){
//        String name = "张三的谷歌";
//        model.addAttribute("name",name);
//        List<News> list = new ArrayList<>();
//        list.add(new News("1.jpg","ajjjjjjjjjjj"));
//        list.add(new News("2.jpg","bjjjjjjjjjjj"));
//        list.add(new News("3.jpg","cjjjjjjjjjjj"));
//        list.add(new News("4.jpg","djjjjjjjjjjj"));
//        list.add(new News("5.jpg","ejjjjjjjjjjj"));
//        model.addAttribute("list",list);
//
//        return "index";
//
//    }
}

