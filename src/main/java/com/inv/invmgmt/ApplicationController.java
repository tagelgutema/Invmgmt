package com.inv.invmgmt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApplicationController {

    @GetMapping("/index2")
    public String testindex(){
        return "index2";
    }
    /*@GetMapping("/index")
    public String home()
    {
        return "index";
    }*/

    @GetMapping("/_layout")
    public String layoutpage()
    {
        return "_layout";
    }

   /* @GetMapping("/employees")
    public String employeePage()
    {
        return  "/employees/index";
    }*/
    @GetMapping("/Settings")
    public String settingsPage()
    {
        return  "/Settings/index";
    }

    @GetMapping("/products")
    public String productsPage()
    {
        return  "/products/index";
    }
    @GetMapping("/transactions")
    public String trasnactionsPage()
    {
        return  "/transactions/index";
    }


    @GetMapping("/offices")
    public String officeLoctionsPage()
    {
        return  "/officeLocations/index";
    }

}
