package sample.cafekiosk.spring.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingType;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponse {
    private final Long id;
    private final String productNumber;
    private final ProductType type;
    private final ProductSellingType sellingType;
    private final String name;
    private final int price;

    @Builder
    private ProductResponse(Long id, String productNumber, ProductType type, ProductSellingType sellingType, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .sellingType(product.getSellingType())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
