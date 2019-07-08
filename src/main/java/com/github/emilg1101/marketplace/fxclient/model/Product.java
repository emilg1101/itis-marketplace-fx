package com.github.emilg1101.marketplace.fxclient.model;

import com.github.emilg1101.marketplace.fxclient.api.pojo.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    private Double price;

    public static Product map(ProductResponse productResponse) {
        return Product.builder()
                .id(productResponse.getId())
                .title(productResponse.getTitle())
                .description(productResponse.getDescription())
                .imageURL(productResponse.getImageURL())
                .price(productResponse.getPrice())
                .build();
    }
}
