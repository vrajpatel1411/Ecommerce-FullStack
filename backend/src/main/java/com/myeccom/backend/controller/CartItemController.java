package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.CartItemException;
import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.CartItem;
import com.myeccom.backend.model.User;
import com.myeccom.backend.response.ApiResponse;
import com.myeccom.backend.service.CartItemService;
import com.myeccom.backend.service.CartService;
import com.myeccom.backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items/")
public class CartItemController {


    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;


    @Autowired
    private CartService cartService;

    @PutMapping("/{cartItemId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt, @RequestBody CartItem cartItem) throws UserException, CartItemException {
        User user=userService.findUserProfileByJwt(jwt);
        CartItem cart=cartItemService.updateCartItem(user.getId(),cartItemId,cartItem);

        Cart cart2=cartService.findUserCart(user.getId());
//        ApiResponse res=new ApiResponse();
//        res.setMessage("Cart Item Updated");
//
//        res.setStatus(true);

        return new ResponseEntity<>(cart2,HttpStatus.OK);

    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Cart> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {

        User user=userService.findUserProfileByJwt(jwt);

        cartItemService.removeCartItem(user.getId(),cartItemId);


        Cart cart2=cartService.findUserCart(user.getId());

//        ApiResponse res=new ApiResponse();
//        res.setMessage("Item Removed from Cart");
//        res.setStatus(true);

        return new ResponseEntity<>(cart2, HttpStatus.OK);

    }

}
