package com.ejercicio5.ejercicio5.Controllers;


import com.ejercicio5.ejercicio5.Pedido;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ejercicio5Controller {

    @PostMapping("/pedidos")

    public String pedidos(@RequestBody Pedido pedido) {
        int totalProductos = 0;
        Double precioTotal = 0.0;

        for (int i = 0; i < pedido.getProductos().size(); i++) {
            int cantidad = pedido.getProductos().get(i).getCantidad();
            double precio = pedido.getProductos().get(i).getPrecioUnitario();

            totalProductos += cantidad;
            precioTotal += precio * cantidad;
        }

        return "{\"cliente\": \"" + pedido.getCliente().getNombre() + "\", "
                + "\"totalProductos\": " + totalProductos + ", "
                + "\"precioTotal\": " + precioTotal + ", "
                + "\"mensaje\": \"Pedido recibido correctamente\"}";

    }
}

// http://localhost:8080/pedidos