package com.myeccom.backend.service;

import com.myeccom.backend.Exception.CartItemException;
import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.CartItem;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.model.User;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public  CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long CartItemId) throws CartItemException;

    public void removeCartItemByCartId(Cart cart);
}
