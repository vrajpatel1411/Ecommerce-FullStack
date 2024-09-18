package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.User;
import com.myeccom.backend.request.AddItemRequest;
import com.myeccom.backend.response.ApiResponse;
import com.myeccom.backend.service.CartService;
import com.myeccom.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.myeccom.backend.Exception.UserException;

@RestController
@RequestMapping("/api/cart")
@Tag(name="Cart Management API", description="This api endpoints will find user cart, add item to cart, delete item from cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Operation(description = "Find Cart By User Id")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
        User user=userService.findUserProfileByJwt(jwt);
        Cart cart=cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(description="add item to cart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, productException {
        User user=userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(),req);

        ApiResponse res=new ApiResponse();
        res.setMessage("new item add to cart");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCart( @RequestHeader("Authorization") String jwt) throws UserException {
        User user=userService.findUserProfileByJwt(jwt);
        Cart cart=cartService.findUserCart(user.getId());

        cartService.deleteCart(cart);

        ApiResponse apires=new ApiResponse();

        apires.setStatus(true);
        apires.setMessage("cart deleted");
        return new ResponseEntity<>(apires,HttpStatus.OK);
    }

}
