package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Category;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.repository.CategoryRepository;
import com.myeccom.backend.repository.ProductRepository;
import com.myeccom.backend.repository.UserRepository;
import com.myeccom.backend.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class ProductServiceImplementation implements ProductService{

    private UserRepository userRepository;

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    ProductServiceImplementation(UserRepository userRepository,ProductRepository productRepository, CategoryRepository categoryRepository){

        this.productRepository=productRepository;
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;

    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        Category topLevel=categoryRepository.findByName(request.getTopLevelCategory());
//        System.out.println("Toplevel-> "+request.getTopLevelCategory());

        if(topLevel
        ==null){
            Category topLevelCategory=new Category();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel=categoryRepository.save(topLevelCategory);
        }

        Category secondLevel=categoryRepository.findByNameAndParentCategory(request.getSecondLevelCategory(),topLevel.getName());

        if(secondLevel
                ==null){
            Category secondLevelCategory=new Category();
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setLevel(2);
            secondLevelCategory.setParentCategory(topLevel);
            secondLevel=categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParentCategory(request.getThirdLevelCategory(),secondLevel.getName());

        if(thirdLevel
                ==null){
            Category thirdLevelCategory=new Category();
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setLevel(3);
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevel=categoryRepository.save(thirdLevelCategory);
        }

        Product product=new Product();
//        System.out.println("------------"+request.getImageUrl());
        System.out.println(request.toString());
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setDiscountedPrice(request.getDiscountedPrice()/60);
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice()/60);
        int original_price = product.getPrice();
        int discounted_price = product.getDiscountedPrice();  // Assuming this method exists

// Correct formula to calculate the discount percent
        int discount_percent = ((original_price - discounted_price) * 100) / original_price;

        product.setDiscountPercent(discount_percent);
        product.setSizes(request.getSize());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());
//        System.out.println("Product=>"+product.toString());
        product=productRepository.save(product);
    return product;

    }

    @Override
    public String deleteProduct(long productId) throws productException {
        Product product=findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);

        return "Product Deleted Successfully";
    }

    @Override
    public Product updateProduct(long productId, Product updatedProduct) throws productException {
        Product product=findProductById(productId);
        if(updatedProduct.getQuantity()!=0){
            product.setQuantity(updatedProduct.getQuantity());
        }

        product=productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product findProductById(long id) throws productException {
       Optional<Product> optionalProduct=productRepository.findById(id);

       if(optionalProduct.isPresent()){
           return optionalProduct.get();
       }

       throw new productException("Product Not Found with Id "+id);
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);


        System.out.println("Pageable => "+pageable);
        List<Product> products=productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);

        if(colors!=null && !colors.isEmpty()){
            products=products.stream().
                                filter(p->colors.stream()
                                        .anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());

        }

        if(stock!=null)
        {
            if(stock.equals("in_stock")){
                products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if(stock.equals("out_of_stock")){
                products=products.stream().filter(p->p.getQuantity()<=0).collect(Collectors.toList());
            }
        }

        int startIndex=(int) pageable.getOffset();
        int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());

        List<Product> pageContent=products.subList(startIndex,endIndex);
//        Page<Product> filteredProducts=
        return new PageImpl<>(pageContent,pageable,products.size());
    }
}
