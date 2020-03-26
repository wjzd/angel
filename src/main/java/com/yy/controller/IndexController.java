package com.yy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/getHeader")
    public String header(){
        return "header";
    }

    @RequestMapping("/getFooter")
    public String footer(){
        return "footer";
    }
}
