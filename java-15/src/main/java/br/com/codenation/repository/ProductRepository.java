package br.com.codenation.repository;

import br.com.codenation.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {


    List<Product> findAll();

    List<Product> findByName(String name);

    Optional<Product> findById(Long id);

}
