package br.com.orderpatterns.controller;

import br.com.orderpatterns.facade.OrderDtos.CreateOrderRequest;
import br.com.orderpatterns.facade.OrderDtos.OrderResponse;
import br.com.orderpatterns.facade.OrderFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderFacade facade;

    public OrderController(OrderFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/quote")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse quote(@Valid @RequestBody CreateOrderRequest request) {
        return facade.create(request);
    }
}
