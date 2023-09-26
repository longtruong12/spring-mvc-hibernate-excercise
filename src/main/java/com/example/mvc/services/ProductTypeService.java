package com.example.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mvc.entity.ProductType;
import com.example.mvc.repository.BaseRepository;

import java.util.List;

@Service
public class ProductTypeService implements BaseService<ProductType> {

    private final BaseRepository<ProductType> productTypeRepository;

    @Autowired
    public ProductTypeService(BaseRepository<ProductType> productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<ProductType> getAll() {
        return productTypeRepository.getAll();
    }

    @Override
    public void save(ProductType object) {
        productTypeRepository.save(object);
    }

    @Override
    public ProductType get(int id) {
        return productTypeRepository.getById(id);
    }

    @Override
    public void delete(int id) {
        productTypeRepository.deleteById(id);
    }
}
