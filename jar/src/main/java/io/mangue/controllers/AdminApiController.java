package io.mangue.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by misael on 17/10/2015.
 */
@RestController
@RequestMapping("/admin/api")
public class AdminApiController {


    @RequestMapping("/test")
    public String test(){
        return "test";
    }

}
