package com.example.demo.arquivos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArquivoController {
    @GetMapping("/arquivos")
    public String arquivos(){
        return "/arquivos/index";
    }
}
