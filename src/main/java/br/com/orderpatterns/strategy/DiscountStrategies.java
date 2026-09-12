package br.com.orderpatterns.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class RegularDiscountStrategy implements DiscountStrategy {
    @Override
    public boolean supports(String customerType) {
        return customerType == null || !customerType.equalsIgnoreCase("VIP");
    }

    @Override
    public BigDecimal calculate(BigDecimal subtotal) {
        return BigDecimal.ZERO;
    }
}

@Component
class VipDiscountStrategy implements DiscountStrategy {
    @Override
    public boolean supports(String customerType) {
        return "VIP".equalsIgnoreCase(customerType);
    }

    @Override
    public BigDecimal calculate(BigDecimal subtotal) {
        return subtotal.multiply(new BigDecimal("0.10"));
    }
}
