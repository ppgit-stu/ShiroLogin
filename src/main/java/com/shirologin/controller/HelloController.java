package com.shirologin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author pengyong
 * @date 2020/5/10 - 12:56
 */

@Controller
public class HelloController {

    @RequestMapping("/index")
    public String sayHello() {
        return "index";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "/userApplication/chat";
    }

    @RequestMapping("/inside")
    public String inside() {
        return "/userApplication/inside";
    }

}
