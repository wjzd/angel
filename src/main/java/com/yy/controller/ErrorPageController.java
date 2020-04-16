package com.yy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errorPageController")
public class ErrorPageController {

    @RequestMapping("/error_{errorCode}")
    public String error(@PathVariable int errorCode){
        String responseMsg;
        switch (errorCode) {
            case 400: responseMsg = "/400.html"; break;
            case 401: responseMsg = "/401.html"; break;
            case 404: responseMsg = "/404.html"; break;
            case 500: responseMsg = "/500.html"; break;
            default: responseMsg = "/404.html"; break;
        }
        return responseMsg;
    }

}

