package br.com.orderpatterns.facade;

import br.com.orderpatterns.domain.Product;
import br.com.orderpatterns.exception.ResourceNotFoundException;
import br.com.orderpatterns.repository.ProductRepository;
import br.com.orderpatterns.strategy.DiscountStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static br.com.orderpatterns.facade.OrderDtos.CreateOrderRequest;
import static br.com.orderpatterns.facade.OrderDtos.OrderResponse;

@Service
public class OrderFacade {
    private final ProductRepository productRepository;
    private final List<DiscountStrategy> discountStrategies;

    public OrderFacade(ProductRepository productRepository, List<DiscountStrategy> discountStrategies) {
        this.productRepository = productRepository;
        this.discountStrategies = discountStrategies;
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest request) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderDtos.Item item : request.items()) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + item.productId()));
            product.reduceStock(item.quantity());
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
        }

        DiscountStrategy strategy = discountStrategies.stream()
                .filter(candidate -> candidate.supports(request.customerType()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhuma estratégia de desconto disponível"));

        BigDecimal discount = strategy.calculate(subtotal);
        return new OrderResponse(subtotal, discount, subtotal.subtract(discount));
    }
}
