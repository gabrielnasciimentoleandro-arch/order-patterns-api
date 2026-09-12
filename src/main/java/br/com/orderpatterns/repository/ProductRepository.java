package br.com.orderpatterns.repository;

import br.com.orderpatterns.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
