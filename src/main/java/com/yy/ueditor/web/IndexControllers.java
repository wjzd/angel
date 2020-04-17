package com.yy.ueditor.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControllers {
	@RequestMapping("/ueditor")
	public String ueditor(){
		return "/indexUeditor";
	}
}
