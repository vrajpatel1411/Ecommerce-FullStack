package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.User;
import com.myeccom.backend.request.AddItemService;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemService req) throws productException;

    public Cart findUserCart(Long userId);


}
