package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.request.CreateProductRequest;
import com.myeccom.backend.response.ApiResponse;
import com.myeccom.backend.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
        Product product=productService.createProduct(req);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws productException {
        productService.deleteProduct(productId);

        ApiResponse res=new ApiResponse();
        res.setMessage("Product Deleted");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getALlProducts(){
        List<Product> productList=productService.getProducts();

        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product res, @PathVariable Long productId) throws productException{
        Product product=productService.updateProduct(productId,res);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req){
        System.out.print("Products are  adding");
        for (CreateProductRequest product:req){
            productService.createProduct(product);
        }
        System.out.println("Product added");
        ApiResponse res=new ApiResponse();
        res.setMessage("Products created");
        res.setStatus(true);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
