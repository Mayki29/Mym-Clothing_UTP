package com.utp.algoritmos.mymclothing.services;

import com.izipay.sdk.PaymentRequest;
import com.izipay.sdk.PaymentResponse;
import com.izipay.sdk.IzipayClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final IzipayClient client;

    public PaymentService() {
        this.client = new IzipayClient("tu_api_key", "tu_api_secret");
    }

    public boolean processPayment(String cardNumber, String expiryDate, String cvv, String amount) {
        PaymentRequest request = new PaymentRequest();
        request.setCardNumber(cardNumber);
        request.setExpiryDate(expiryDate);
        request.setCvv(cvv);
        request.setAmount(amount);

        PaymentResponse response = client.processPayment(request);
        
        // Verifica la respuesta de la API de Izipay
        return response.isSuccess();
    }
}
