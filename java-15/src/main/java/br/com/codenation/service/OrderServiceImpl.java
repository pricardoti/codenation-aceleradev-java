package br.com.codenation.service;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    /**
     * Calculate the sum of all OrderItems
     */
    @Override
    public Double calculateOrderValue(List<OrderItem> items) {
        return items.stream().map(item -> {
            Product product = this.productRepository.findById(item.getProductId()).get();
            Double total = item.getQuantity() * product.getValue();
            return product.getIsSale() ? total - (total * 0.2) : total;
        }).collect(Collectors.summingDouble(Double::doubleValue));
    }

    /**
     * Map from idProduct List to Product Set
     */
    @Override
    public Set<Product> findProductsById(List<Long> ids) {
        return ids.stream()
                .map(id -> this.productRepository.findById(id).orElse(null) )
                .filter(product -> Optional.ofNullable(product).isPresent())
                .collect(Collectors.toSet());
    }

    /**
     * Calculate the sum of all Orders(List<OrderIten>)
     */
    @Override
    public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
        return orders.stream().map(item -> calculateOrderValue(item))
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    /**
     * Group products using isSale attribute as the map key
     */
    @Override
    public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
        return findProductsById(productIds).stream().collect(groupingBy(Product::getIsSale));
    }

}