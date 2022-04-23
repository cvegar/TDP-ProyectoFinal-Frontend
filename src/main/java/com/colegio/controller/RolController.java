package com.colegio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RolController {

    @RequestMapping("/consultaCrudRol")
    public String listarMatricula(){
        return "crudRol";
    }
}
