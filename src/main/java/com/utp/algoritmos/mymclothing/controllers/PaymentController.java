package com.utp.algoritmos.mymclothing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.utp.algoritmos.mymclothing.services.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process-payment")
    public ModelAndView processPayment(
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("cvv") String cvv,
            @RequestParam("amount") String amount) {
        
        // Aquí se agregará la lógica para interactuar con la API de Izipay
        boolean paymentSuccess = paymentService.processPayment(cardNumber, expiryDate, cvv, amount);

        ModelAndView mav = new ModelAndView("result");
        if(paymentSuccess) {
            mav.addObject("message", "Pago procesado correctamente");
        } else {
            mav.addObject("message", "Error en el procesamiento del pago");
        }
        return mav;
    }
}
