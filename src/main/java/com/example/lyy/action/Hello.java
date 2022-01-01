package com.example.lyy.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Hello {
    @RequestMapping("/sendToLogin")
    public String sendToLogin(){
        return "login";
    }
    @RequestMapping("/frame")
    public String frame(){
        return "frame";
    }
    @RequestMapping("/mail")
    public String mail(){
        return "Mail";
    }
    @RequestMapping("/sendToAdd")
    public String sendToAdd(){
        return "useradd";
    }
    @RequestMapping("/users")
    public String sendTousers(){
        return "userlist";
    }
    @RequestMapping("/bills")
    public String sendTobills(){
        return "billlist";
    }
    @RequestMapping("/userLogin")
    public String userLogin(){
        return "testLogin";
    }
    @RequestMapping("/sendToregister")
    public String sendToregister(){
        return "userRegister";
    }

}
