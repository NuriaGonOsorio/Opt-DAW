package com.ejercicio6.ejercicio6.controllers;

import com.ejercicio6.ejercicio6.Formulario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class Actividad6Controller {

    private final Map<String, String> usuarios = new HashMap<>();

    public Actividad6Controller() {
        usuarios.put("ana@example.com", "246810");
        usuarios.put("luis@example.com", "clave123");
    }

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "registro";
    }

    @PostMapping("/registro")
    @ResponseBody
    public String procesarFormulario(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos,
            @RequestParam("correo") String correo,
            @RequestParam("contraseña") String contraseña
    ) {

        Formulario  formulario = new Formulario();
        formulario.setNombre(nombre);
        formulario.setApellidos(apellidos);
        formulario.setCorreo(correo);
        formulario.setContraseña(contraseña);

        if (formulario.getNombre().isEmpty() || formulario.getCorreo().isEmpty()) {
            return "Error: el nombre y el correo son obligatorios";
        }

        boolean generada = false;

        if (formulario.getContraseña().isEmpty()) {
            Random randon = new Random();
            int numero = randon.nextInt(1000000);
            formulario.setContraseña(String.format("%06d", numero));
            generada = true;
        }

        usuarios.put(formulario.getCorreo(), formulario.getContraseña());

        String respuesta = "Usuario registrado: " + formulario.getNombre() + " " + formulario.getApellidos() + "<br>"
                + "Correo: " + formulario.getCorreo() + "<br>";

        if (generada) {
            respuesta = respuesta + "Contraseña generada: " + formulario.getContraseña();
        }

        return respuesta;
    }


    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String procesarLogin(
            @RequestParam("usuario") String usuario,
            @RequestParam("contraseña") String contraseña
    ) {

        if (!usuarios.containsKey(usuario)) {
            return "Usuario no existe";
        }

        if (!usuarios.get(usuario).equals(contraseña)) {
            return "Contraseña incorrecta";
        }

        return "Inicio de sesion correcto";
    }
}
