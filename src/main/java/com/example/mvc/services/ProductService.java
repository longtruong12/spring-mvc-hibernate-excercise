package com.example.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mvc.entity.Product;
import com.example.mvc.repository.BaseRepository;

import java.util.List;

@Service
public class ProductService implements BaseService<Product> {

    private final BaseRepository<Product> productRepository;

    @Autowired
    public ProductService(BaseRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public void save(Product object) {
        productRepository.save(object);
    }

    @Override
    public Product get(int id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
