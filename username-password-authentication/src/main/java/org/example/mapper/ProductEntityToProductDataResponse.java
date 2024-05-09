package org.example.mapper;

import org.example.api.dto.ProductDataResponse;
import org.example.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProductDataResponse implements Mapper<ProductEntity, ProductDataResponse> {
    @Override
    public ProductDataResponse map(ProductEntity object) {
        return new ProductDataResponse(
                object.getId(),
                object.getName(),
                object.getDescription()
        );
    }
}
