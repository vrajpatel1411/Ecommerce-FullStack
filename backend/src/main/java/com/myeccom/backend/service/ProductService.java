package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest request);

    public String deleteProduct(long productId) throws productException;

    public Product updateProduct(long productId, Product product) throws productException;

public List<Product> getProducts();

public Product findProductById(long id) throws productException;

public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);


}
