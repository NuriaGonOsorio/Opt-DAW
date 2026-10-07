package com.actividad3.actividad3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Ejercicio3Controller {

    @GetMapping("/elegir")
    public String elegir(
            @RequestParam(name = "idioma", defaultValue = "english") String idioma) {

        if (idioma.equalsIgnoreCase("spanish")) {
            return "redirect:/spanish.html";
        } else if (idioma.equalsIgnoreCase("french")) {
            return "redirect:/french.html";
        } else if (idioma.equalsIgnoreCase("german")) {
            return "redirect:/german.html";
        } else {
            return "redirect:/english.html";
        }
    }
}
