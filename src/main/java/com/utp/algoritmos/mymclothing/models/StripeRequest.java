package com.utp.algoritmos.mymclothing.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeRequest {

    private Long total;
    private String email;
    private List<Producto> producto;
}
