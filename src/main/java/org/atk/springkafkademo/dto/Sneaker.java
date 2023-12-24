package org.atk.springkafkademo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sneaker {

    private String id;
    private String name;
    private String brand;
    private Double price;
    private boolean isVerified;
    private boolean isAuthentic;

}
