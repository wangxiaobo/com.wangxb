package com.sxt.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    @Autowired  
    private  HttpServletRequest request;
    

    @RequestMapping(value="/view/{view}")  
    public String report(@PathVariable(value = "view") String view) throws Exception{         
        return view;
    }
    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
    
    
}
