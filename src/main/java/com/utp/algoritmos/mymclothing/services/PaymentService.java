package com.utp.algoritmos.mymclothing.services;

import org.springframework.stereotype.Service;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentService {

    private final String IZIPAY_API_URL = "https://api.izipay.com/v1/payments"; // Reemplaza con la URL real de la API de Izipay
    private final String IZIPAY_API_KEY = "tu_api_key"; // Reemplaza con tu clave de API de Izipay

    public boolean processPayment(String cardNumber, String expiryDate, String cvv, String amount) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(IZIPAY_API_URL);
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Authorization", "Bearer " + IZIPAY_API_KEY);

            // Crear el cuerpo de la solicitud
            PaymentRequest paymentRequest = new PaymentRequest(cardNumber, expiryDate, cvv, amount);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(paymentRequest);
            request.setEntity(new StringEntity(json));

            // Enviar la solicitud y obtener la respuesta
            var response = httpClient.execute(request);
            var responseBody = EntityUtils.toString(response.getEntity());

            // Procesar la respuesta
            PaymentResponse paymentResponse = mapper.readValue(responseBody, PaymentResponse.class);
            return paymentResponse.isSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Clases internas para representar la solicitud y la respuesta
    private static class PaymentRequest {
        public String cardNumber;
        public String expiryDate;
        public String cvv;
        public String amount;

        public PaymentRequest(String cardNumber, String expiryDate, String cvv, String amount) {
            this.cardNumber = cardNumber;
            this.expiryDate = expiryDate;
            this.cvv = cvv;
            this.amount = amount;
        }
    }

    private static class PaymentResponse {
        private boolean success;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}

