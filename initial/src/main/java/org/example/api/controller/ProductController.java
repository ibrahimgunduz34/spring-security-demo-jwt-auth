package org.example.api.controller;

import jakarta.validation.Valid;
import org.example.api.dto.ProductCreateRequest;
import org.example.api.dto.ProductDataResponse;
import org.example.mapper.Mapper;
import org.example.persistence.entity.ProductEntity;
import org.example.persistence.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Mapper<ProductCreateRequest, ProductEntity> requestMapper;
    private final Mapper<ProductEntity, ProductDataResponse> responseMapper;
    private final ProductRepository repository;

    public ProductController(
            Mapper<ProductCreateRequest, ProductEntity> requestMapper,
            Mapper<ProductEntity, ProductDataResponse> responseMapper,
            ProductRepository repository
    ) {
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDataResponse create(@Valid @RequestBody ProductCreateRequest request) {
        ProductEntity entity = requestMapper.map(request);
        ProductEntity createdEntity = repository.save(entity);
        return responseMapper.map(createdEntity);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    ProductDataResponse retrieve(@PathVariable("productId") Long productId) {
        ProductEntity entity = repository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found"));
        return responseMapper.map(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductDataResponse> list() {
        return repository.findAll()
                .stream()
                .map(responseMapper::map).collect(Collectors.toList());
    }
}
