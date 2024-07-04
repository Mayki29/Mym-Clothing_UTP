package com.utp.algoritmos.mymclothing.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/process-payment")
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        // la lógica para verificar y procesar el pago
        // Por ahora, solo retornaremos un mensaje de éxito
        return "Payment processed successfully";
    }

    // Clase interna para representar la solicitud de pago
    public static class PaymentRequest {
        public String orderID;
    }
}
