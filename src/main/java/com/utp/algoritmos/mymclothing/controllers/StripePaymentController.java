package com.utp.algoritmos.mymclothing.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.models.Venta;
import com.utp.algoritmos.mymclothing.services.StripeServiceImp;

@RestController
public class StripePaymentController {

    @Value("${stripe.api.publicKey}")
    private String PUBLISHABLE_KEY;

    private final StripeServiceImp stripeService;

    public StripePaymentController(StripeServiceImp stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("products", new ArrayList<Producto>());
        return "payment"; // Nombre de tu plantilla Thymeleaf
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam Venta venta, Model model) {
        try {
            Session session = stripeService.createCheckoutSession(venta);
            model.addAttribute("sessionId", session.getId());
            model.addAttribute("stripePublishableKey", PUBLISHABLE_KEY);
        } catch (StripeException e) {
            // Manejar la excepción adecuadamente
            model.addAttribute("error", e.getMessage());
        }
        return "payment";
    }

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
