package br.com.orderpatterns.facade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public final class OrderDtos {
    private OrderDtos() {
    }

    public record Item(@NotNull Long productId, @Min(1) int quantity) {
    }

    public record CreateOrderRequest(@NotEmpty List<Item> items, String customerType) {
    }

    public record OrderResponse(BigDecimal subtotal, BigDecimal discount, BigDecimal total) {
    }
}
