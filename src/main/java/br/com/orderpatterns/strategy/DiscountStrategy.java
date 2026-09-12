package br.com.orderpatterns.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    boolean supports(String customerType);
    BigDecimal calculate(BigDecimal subtotal);
}
