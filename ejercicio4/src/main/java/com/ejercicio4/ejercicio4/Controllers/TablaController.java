package com.ejercicio4.ejercicio4.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablaController {

    @GetMapping(value = "/tabla")
    public String generarTabla(@RequestParam String filas, @RequestParam String columnas) {

        int f = comprobar(filas);
        int c = comprobar(columnas);

        String html = "<table border='1'><tr>";

        for (int j = 1; j <= c; j++) {
            html += "<th>Columna " + j + "</th>";
        }
        html += "</tr>";

        for (int i = 1; i <= f; i++) {
            html += "<tr>";
            for (int j = 1; j <= c; j++) {
                html += "<td>Fila " + i + ", Columna " + j + "</td>";
            }
            html += "</tr>";
        }
        return html + "</table>";
    }

    private int comprobar(String valor) {
        try {
            int n = Integer.parseInt(valor);
            if (n < 1) {
                return 1;
            }
            if (n > 20) {
                return 20;
            }
            return n;
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}