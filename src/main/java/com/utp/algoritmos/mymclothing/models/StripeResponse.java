package com.utp.algoritmos.mymclothing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StripeResponse {
    private String intentID;
    private String clientSecret;
}
