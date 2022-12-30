package com.inv.invmgmt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApplicationController {

    @GetMapping("/index")
    public String home()
    {
        return "index";
    }

    @GetMapping("/widgets")
    public String widget()
    {
        return "widgets";
    }


}
