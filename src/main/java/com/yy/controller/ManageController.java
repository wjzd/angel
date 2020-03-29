package com.yy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/views")
public class ManageController {

    @RequestMapping("/getConsole")
    public String console(){
        return "/views/home/console";
    }
}
