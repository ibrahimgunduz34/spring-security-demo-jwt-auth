package org.example.mapper;

import org.example.api.dto.ProductCreateRequest;
import org.example.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateRequestToProductEntity implements Mapper<ProductCreateRequest, ProductEntity> {
    @Override
    public ProductEntity map(ProductCreateRequest object) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(object.getName());
        productEntity.setDescription(object.getDescription());

        return productEntity;
    }
}
