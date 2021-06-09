package com.ttsr.springdatademo.controller;

import com.ttsr.springdatademo.component.Cart;
import com.ttsr.springdatademo.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {

    private final Cart cart;

    @PutMapping("/add")
    public void add(@RequestParam Long id){
        cart.add(id);
    }

    @GetMapping("/show")
    public CartDto showCart(){
        return new CartDto(cart);
    }

    @DeleteMapping("/remove")
    public void removeById(@RequestParam Long id){
        cart.remove(id);
    }

    @DeleteMapping("/removeAll")
    public void removeAllFromCart(){
        cart.removeAll();
    }
}
