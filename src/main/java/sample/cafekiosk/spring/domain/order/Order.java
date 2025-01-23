package sample.cafekiosk.spring.domain.order;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;
import sample.cafekiosk.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderProduct> orderProducts = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private int totalPrice;
    private LocalDateTime registeredDateTime;

    public Order(List<Product> products, LocalDateTime now) {
        this.orderStatus = OrderStatus.INIT;
        this.totalPrice = calculateTotalPrice(products);
        this.registeredDateTime = now;
    }

    public static Order create(List<Product> products, LocalDateTime registeredDateTime) {
        return new Order(products, registeredDateTime);
    }

    private int calculateTotalPrice(List<Product> products) {
        return products.stream().mapToInt(Product::getPrice).sum();
    }
}
