package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.CartItem;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.CartRepository;
import com.myeccom.backend.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {

    private CartRepository cartRepository;
private CartItemService cartItemService;

private ProductService productService;

public  CartServiceImplementation(CartRepository cartRepository,CartItemService cartItemService,ProductService productService){
    this.cartRepository=cartRepository;
    this.cartItemService=cartItemService;
    this.productService=productService;
}
    @Override
    public Cart createCart(User user) {
        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws productException {
        Cart cart=cartRepository.findByUserId(userId);
        if(cart==null){
            cart=new Cart();
        }
        Product product=productService.findProductById(req.getProductId());

        CartItem isPresent=cartItemService.isCartItemExist(cart,product,req.getSize(),userId);
        if(isPresent==null) {

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            int price=req.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());
            CartItem createdCartItem= cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            cartRepository.save(cart);

        }
        return "Item Added Successfully";
    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart cart=cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;

        for(CartItem cartItem:cart.getCartItems()){
            totalPrice+=cartItem.getPrice();
            totalDiscountedPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
        }

        cart.setTotalltem(totalItem);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-totalDiscountedPrice);

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Cart  cart){
        cartItemService.removeCartItemByCartId(cart);

//        cartRepository.delete(cart);
    }
}
