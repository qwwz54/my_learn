package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String index(Model model, String title) {
		System.out.println(title);
		
		String name = "张三的歌";
		model.addAttribute("name", title);
		
		List<News> list = new ArrayList<>();
		list.add(new News("这么近 那么美 周末到河北", "1.jpg"));
		list.add(new News("桃花春色暖先开，明媚谁人不看来", "2.jpg"));
		list.add(new News("阳春三月，大地回暖，万物复苏，邢台九龙峡7.5公里的观花环线，数万亩山桃花风姿绰约", "3.jpg"));
		list.add(new News("一树树桃花竞相开放，争奇斗艳，漫山遍野、沟沟坡坡，朵朵灿烂，枝枝夺目，千树妍喧，喷火蒸霞", "4.jpg"));
		list.add(new News("桃花溪流共争妍，姹紫嫣红竞相呈", "5.jpg"));
		model.addAttribute("list", list);
		
		model.addAttribute("title", title);
		return "index";
	}
	
}
