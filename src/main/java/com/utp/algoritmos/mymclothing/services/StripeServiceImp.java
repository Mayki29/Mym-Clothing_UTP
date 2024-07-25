package com.utp.algoritmos.mymclothing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.checkout.Session;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.utp.algoritmos.mymclothing.models.DetalleVenta;
import com.utp.algoritmos.mymclothing.models.Producto;
import com.utp.algoritmos.mymclothing.models.Venta;

import jakarta.annotation.PostConstruct;

@Service
public class StripeServiceImp {

    @Value("${stripe.api.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = secretKey;
    }

    public Session createCheckoutSession(Venta venta) throws StripeException {
        SessionCreateParams.Builder builder = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://example.com/success")
                .setCancelUrl("https://example.com/cancel");

        for (DetalleVenta detalleVenta : venta.getDetallesVenta()) {
            builder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity((long) detalleVenta.getCantidad())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("pen")
                                            .setUnitAmount((long) (detalleVenta.getPrecioUnitario() * 100)) // Stripe requiere el monto en centavos
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(detalleVenta.getProducto().getNombreProducto())
                                                            .addImage("localhost:8080/imgProductos/"+detalleVenta.getProducto().getUrlImagen())
                                                            .build())
                                            .build())
                            .build());
        }

        SessionCreateParams params = builder.build();
        return Session.create(params);
    }


}
