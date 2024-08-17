package com.myeccom.backend.service;

import com.myeccom.backend.Exception.CartItemException;
import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.CartItem;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.CartItemRepository;
import com.myeccom.backend.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService{

    private CartItemRepository cartItemRepository;


    private UserService userService;

    private CartRepository cartRepository;


    public CartItemServiceImplementation(CartItemRepository cartItemRepository,  UserService userService
            ,CartRepository cartRepository)
    {
        this.cartItemRepository=cartItemRepository;
        this.cartRepository=cartRepository;
        this.userService=userService;
    }
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem createdCartItem=cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemid, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(cartItemid);

        User user=userService.findUserByd(userId);

        if(user.getId().equals(item.getUserId())){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        CartItem  cartItem=cartItemRepository.isCartItemExist(cart,product,size,userId);


         return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem=findCartItemById(cartItemId);

        User user=userService.findUserByd(cartItem.getUserId());

        User reqUser=userService.findUserByd(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }
        else{
            throw new UserException("You can't remove another user items");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);

        if(cartItem.isPresent()){
            return cartItem.get();
        }
        else{
            throw new CartItemException("Cart Item not found with Id "+ cartItemId);
        }

    }
}
