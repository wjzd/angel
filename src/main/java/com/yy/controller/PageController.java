package com.yy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制页面跳转Controller
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("getHeader")
    public String getHeader(){
        return "header";
    }
    @RequestMapping("getFooter")
    public String getFooter(){
        return "footer";
    }
}
